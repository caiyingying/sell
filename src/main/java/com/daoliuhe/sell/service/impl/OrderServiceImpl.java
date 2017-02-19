package com.daoliuhe.sell.service.impl;

import com.daoliuhe.sell.Weidian.DefaultWeidianClient;
import com.daoliuhe.sell.Weidian.http.Param;
import com.daoliuhe.sell.bean.weidian.response.order.VdianOrderGetResponse;
import com.daoliuhe.sell.bean.weidian.response.order.VdianOrderIdsGetResponse;
import com.daoliuhe.sell.exception.OpenException;
import com.daoliuhe.sell.mapper.CustomerMapper;
import com.daoliuhe.sell.mapper.DealersUserMapper;
import com.daoliuhe.sell.mapper.OrderProductMapper;
import com.daoliuhe.sell.mapper.SyncTimeMapper;
import com.daoliuhe.sell.model.*;
import com.daoliuhe.sell.service.OrderService;
import com.daoliuhe.sell.util.Config;
import com.daoliuhe.sell.util.Constants;
import com.daoliuhe.sell.util.JsonUtils;
import com.daoliuhe.sell.util.Utils;
import com.daoliuhe.sell.weChat.WeiDianTokenHandler;
import org.apache.poi.util.StringUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by CYY on 2017/1/24.
 */
@Service
public class OrderServiceImpl implements OrderService {

    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    WeiDianTokenHandler weiDianTokenHandler;

    @Autowired
    OrderProductMapper orderProductMapper;

    @Autowired
    SyncTimeMapper syncTimeMapper;

    @Autowired
    CustomerMapper customerMapper;

    @Autowired
    DealersUserMapper dealersUserMapper;

    @Override
    public Map<String, Object> doSync() {
        Map<String, Object> json = new HashMap<String, Object>();
        boolean success = true;
        String reason = "";
        //根据最后的查询时间查询订单，如果数据库中不存在，则查询详情，过滤其中完成的订单保存订单
        SyncTime syncTime = syncTimeMapper.selectByName(Constants.ORDER_SYNC_TIME);
        String beginDate = "";
        String endDate = "";
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE));
        Date today = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String todayStr = sdf.format(today);
        if (null == syncTime || StringUtils.isEmpty(syncTime.getSyncDate())) {
            beginDate = "2017-01-01";
            endDate = todayStr;
        } else {
            beginDate = syncTime.getSyncDate();
            endDate = todayStr;
        }
        //拆分订单到产品和订单数据
        List<String> orders = new ArrayList<String>();
        List<String> syncTimes = Utils.getInterval(beginDate, endDate);
        for (String time : syncTimes) {
            List<String> dayOrders = getVDianOrderIds(time, "0", 1, "1");
            orders.addAll(dayOrders);
        }
        if (orders.isEmpty()) {
            success = false;
            reason = "没有查询到订单.";
        } else {
            //循环查询到的订单
            for (String orderId : orders) {
                OrderProduct orderProductParam = new OrderProduct();
                orderProductParam.setOrderId(orderId);
                int count = orderProductMapper.getPageCount(orderProductParam);
                if (count == 0) {//插入
                    //查询详情
                    VdianOrderGetResponse orderGetResponse = getVDianOrder(orderId);
                    //请求成功
                    if (null != orderGetResponse && null != orderGetResponse.getStatus() && 0 == orderGetResponse.getStatus().getStatusCode()) {
                        if (null != orderGetResponse.getResult()) {
                            VdianOrderGetResponse.VdianOrderGetResult result = orderGetResponse.getResult();
                            if ("finish".equalsIgnoreCase(result.getStatus())) {
                                String payTime = result.getPayTime();
                                String userPhone = result.getUserPhone();
                                //找到分销商
                                int dealersId = 0;
                                Date payDate = Utils.pareDate(payTime);
                                if (null != payDate) {
                                    Customer customer = new Customer();
                                    customer.setEnableDate(payDate);
                                    customer.setPhone(userPhone);
                                    Customer ret = customerMapper.getDealersByTime(customer);
                                    if (null != ret && ret.getBusinessId() > 0) {
                                        dealersId = ret.getBusinessId();
                                    }
                                }
                                String orderTotalPrice = result.getPrice();
                                VdianOrderGetResponse.OrderItem[] items = result.getItems();
                                for (VdianOrderGetResponse.OrderItem item : items) {
                                    String itemId = item.getItemId();
                                    String itemName = item.getItemName();
                                    String price = item.getPrice();
                                    String quantity = item.getQuantity();
                                    String totalPrice = item.getTotalPrice();
                                    //订单商品
                                    OrderProduct orderProduct = new OrderProduct();
                                    orderProduct.setOrderId(orderId);
                                    orderProduct.setPayTime(payTime);
                                    orderProduct.setUserPhone(userPhone);
                                    orderProduct.setOrderTotalPrice(Double.parseDouble(orderTotalPrice));
                                    orderProduct.setItemId(itemId);
                                    orderProduct.setItemName(itemName);
                                    orderProduct.setPrice(Double.parseDouble(price));
                                    orderProduct.setQuantity(Integer.parseInt(quantity));
                                    orderProduct.setTotalPrice(Double.parseDouble(totalPrice));
                                    //型号
                                    orderProduct.setSkuId(item.getSkuId());
                                    orderProduct.setSkuTitle(item.getSkuTitle());
                                    //找到分销商
                                    if(dealersId > 0){
                                        orderProduct.setDealersId(dealersId);
                                    }
                                    //插入数据库
                                    orderProductMapper.insertSelective(orderProduct);
                                }
                            } else {
                                logger.info("订单{}没有完成.", orderId);
                            }
                        }
                    }
                } else {
                    logger.info("订单{}已经存在.", orderId);
                }
            }
        }

        if (null == syncTime) {
            SyncTime initTime = new SyncTime();
            initTime.setName(Constants.ORDER_SYNC_TIME);
            initTime.setSyncDate(endDate);
            syncTimeMapper.insert(initTime);
        } else {
            //保存同步日期，获取前一天，保持数据冗余，预防数据遗漏
            syncTime.setSyncDate(Utils.getBeforeDateStr(endDate));
        }
        json.put("success", success);
        json.put("reason", reason);
        return json;
    }

    @Override
    public Map<String, Object> doRebate(List<OrderProduct> orderProducts) {
        logger.info("doRebate, orderProducts:{}", orderProducts.toArray());
        Map<String, Object> json = new HashMap<String, Object>();
        boolean success = true;
        String reason = "";
        try {
            for (OrderProduct orderProduct : orderProducts) {
                orderProduct.setComfirm(Constants.ENABLE);
                orderProduct.setComfirmDate(new Date());
                orderProductMapper.updateByPrimaryKeySelective(orderProduct);
            }
        } catch (Exception e) {
            success = false;
            logger.info("Exception, e:{}", e.getMessage());
        }
        return json;
    }

    @Override
    public Map<String, Object> getPageData(OrderProduct orderProduct) {
        logger.info("getPageData,orderProduct:{}", orderProduct);
        Map<String, Object> map = new HashMap<String, Object>();
        int total = orderProductMapper.getPageCount(orderProduct);
        map.put("total", total);
        int curPage = orderProduct.getPage();
        int rows = orderProduct.getRows();
        int maxPage = (total + rows - 1) / rows;
        if (curPage > maxPage && maxPage > 0) {
            orderProduct.setPage(maxPage);
        }
        map.put("rows", orderProductMapper.getPageData(orderProduct));
        return map;
    }

    @Override
    public Map<String, Object> getAuthPageData(OrderProduct orderProduct) {
        logger.info("getPageData,orderProduct:{}", orderProduct);
        Map<String, Object> map = new HashMap<String, Object>();
        Session session = SecurityUtils.getSubject().getSession();
        User curUser = (User) session.getAttribute(Constants.USERINFO);
        if(null != curUser && curUser.getId() > 0){
            DealersUser dealersUser = new DealersUser();
            dealersUser.setUserId(curUser.getId());
            List<DealersUser> dealersUsers = dealersUserMapper.select(dealersUser);
            if(dealersUsers.isEmpty()){
                logger.info("用户{},没有分销商的权限",curUser.toString());
            } else {
                List<Integer> dealersIds = new ArrayList<Integer>();
                for(DealersUser user : dealersUsers){
                    dealersIds.add(user.getDealersId());
                }
                //设置权限分销商
                orderProduct.setDealersIdList(dealersIds);
                int total = orderProductMapper.getPageCount(orderProduct);
                map.put("total", total);
                int curPage = orderProduct.getPage();
                int rows = orderProduct.getRows();
                int maxPage = (total + rows - 1) / rows;
                if (curPage > maxPage && maxPage > 0) {
                    orderProduct.setPage(maxPage);
                }
                map.put("rows", orderProductMapper.getPageData(orderProduct));
            }
        }
        return map;
    }

    /**
     * 获取某天的订单
     *
     * @param order_date  订单日期
     * @param isweicenter 是否是微中心订单（0：全部订单，1微中心订单，2普通订单，默认是全部订单
     * @param page_num    返回页码，从1开始
     * @param group_type  1不包含微团购未成团订单，0包含，默认是1
     * @return
     * @throws OpenException
     */
    public List<String> getVDianOrderIds(String order_date, String isweicenter, Number page_num, String group_type) {
        List orders = new ArrayList();
        try {
            String token = weiDianTokenHandler.getToken();
            //请求方法体
            Map<String, Object> method = new HashMap<String, Object>();
            method.put("method", "vdian.order.ids.get");
            method.put("access_token", token);
            method.put("version", "1.1");
            method.put("format", "json");
            //请求参数
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("order_date", order_date);
            map.put("isweicenter", isweicenter);
            map.put("page_num", page_num);
            map.put("group_type", group_type);
            //请求订单
            String retStr = DefaultWeidianClient.getInstance().executePostForString(Config.API_URL_FOR_POST,
                    new Param(Config.PUBLIC_PARAM, JsonUtils.toJson(method)),
                    new Param(Config.BIZ_PARAM, JsonUtils.toJson(map)));

            //查询订单
            VdianOrderIdsGetResponse orderIdsResponse = JsonUtils.toObject(retStr, VdianOrderIdsGetResponse.class);
            if (null != orderIdsResponse) {
                int statusCode = orderIdsResponse.getStatus().getStatusCode();
                if (statusCode == 0) {
                    if (orderIdsResponse.getResult() != null && orderIdsResponse.getResult().getTotal() > 0) {
                        List<String> orderIds = orderIdsResponse.getResult().getOrderIds();
                        orders.addAll(orderIds);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("getVDianOrderIds,message:{}", e.getMessage());
        }
        return orders;
    }

    /**
     * 根据订单id获取订单详情
     *
     * @param orderId
     * @return
     */
    public VdianOrderGetResponse getVDianOrder(String orderId) {
        VdianOrderGetResponse orderResponse = null;
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("order_id", orderId);

            String token = weiDianTokenHandler.getToken();
            //请求方法体
            Map<String, Object> method = new HashMap<String, Object>();
            method.put("method", "vdian.order.get");
            method.put("access_token", token);
            method.put("version", "1.0");
            method.put("format", "json");

            String retStr = DefaultWeidianClient.getInstance().executePostForString(Config.API_URL_FOR_POST,
                    new Param(Config.PUBLIC_PARAM, JsonUtils.toJson(method)),
                    new Param(Config.BIZ_PARAM, JsonUtils.toJson(map)));
            //返回的订单详情
            orderResponse = JsonUtils.toObject(retStr, VdianOrderGetResponse.class);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("getVDianOrder,message:{}", e.getMessage());
        }
        return orderResponse;
    }
}
