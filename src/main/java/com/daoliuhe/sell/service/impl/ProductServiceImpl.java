package com.daoliuhe.sell.service.impl;

import com.daoliuhe.sell.Weidian.DefaultWeidianClient;
import com.daoliuhe.sell.Weidian.http.Param;
import com.daoliuhe.sell.bean.weidian.entity.Item;
import com.daoliuhe.sell.bean.weidian.entity.Sku;
import com.daoliuhe.sell.bean.weidian.response.Status;
import com.daoliuhe.sell.bean.weidian.response.product.VdianItemListGetResponse;
import com.daoliuhe.sell.mapper.ProductMapper;
import com.daoliuhe.sell.model.Product;
import com.daoliuhe.sell.service.ProductService;
import com.daoliuhe.sell.util.Config;
import com.daoliuhe.sell.util.JsonUtils;
import com.daoliuhe.sell.weChat.WeiDianTokenHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by CYY on 2016/12/25.
 */
@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    ProductMapper productMapper;

    @Autowired
    WeiDianTokenHandler weiDianTokenHandler;

    @Override
    public Map<String, Object> getPageData(Product product) {
        logger.info("getPageData,product:{}", product);
        Map<String, Object> map = new HashMap<String, Object>();
        int total = productMapper.getPageCount(product);
        map.put("total", total);
        int curPage = product.getPage();
        int rows = product.getRows();
        int maxPage = (total + rows - 1) / rows;
        if (curPage > maxPage && maxPage > 0) {
            product.setPage(maxPage);
        }
        map.put("rows", productMapper.getPageData(product));
        return map;
    }

    @Override
    public List<Product> getListData(Product product) {
        logger.info("getListData,product:{}", product);
        int total = productMapper.getPageCount(product);
        product.setPage(1);
        product.setRows(total);
        return productMapper.getPageData(product);
    }

    @Override
    public Product getProductById(Integer id) {
        return productMapper.selectByPrimaryKey(id);
    }

    @Override
    public void saveOrUpdate(Product product) {
        logger.info("saveOrUpdate,product:{}", product);
        if (!StringUtils.isEmpty(product.getId())) {
            productMapper.updateByPrimaryKeySelective(product);
        } else {
            productMapper.insert(product);
        }
    }

    @Override
    public void updateProduct(Product product) {
        logger.info("updateProduct,product:{}", product);
        productMapper.updateByPrimaryKeySelective(product);
    }

    @Override
    public Map<String, Object> doSync() {
        logger.info("doSync");
        Map<String, Object> json = new HashMap<String, Object>();
        boolean success = false;
        String reason = "";
        int pageNum = 1;
        int pageSize = 30;
        int total = 0;
        int itemNum = 0;
        try {
            String token = weiDianTokenHandler.getToken();
            Map<String, Object> method = new HashMap<String, Object>();
            method.put("method", "vdian.item.list.get");
            method.put("access_token", token);
            method.put("version", "1.0");
            method.put("format", "json");

            do {
                Map<String, Object> param = new HashMap<String, Object>();
                param.put("page_num", pageNum);
                param.put("page_size", pageSize);
                param.put("status", 4);

                String vdianItemListGet = DefaultWeidianClient.getInstance().executePostForString(Config.API_URL_FOR_POST,
                        new Param(Config.PUBLIC_PARAM, JsonUtils.toJson(method)),
                        new Param(Config.BIZ_PARAM, JsonUtils.toJson(param)));
                //查询所有的商品
                //VdianItemListGetResponse itemList = itemList = JsonPluginsUtil.jsonToBean(vdianItemListGet, VdianItemListGetResponse.class);
                VdianItemListGetResponse itemList = JsonUtils.toObject(vdianItemListGet, VdianItemListGetResponse.class);
                if (null != itemList) {
                    Status status = itemList.getStatus();
                    if (null != status && status.getStatusCode() == 0) {
                        VdianItemListGetResponse.VdianItemListGetResult itemListRet = itemList.getResult();
                        if (null != itemListRet) {
                            itemNum = itemListRet.getItemNum();
                            Item[] items = itemListRet.getItems();
                            if (items.length == 0) {
                                success = false;
                                reason = "没有查询到商品.";
                            } else {
                                for (Item item : items) {
                                    Sku[] skus = item.getSkus();
                                    if (skus.length == 0) {
                                        Product product = new Product();
                                        product.setProductId(item.getItemId());
                                        product.setSkuId("0");
                                        int count = productMapper.getPageCount(product);
                                        product.setSkuTitle("");
                                        product.setProductName(item.getItemName());
                                        product.setProductPrice(Double.parseDouble(item.getPrice()));
                                        //存在就更新，不存在就添加
                                        if (count == 0) {
                                            productMapper.insertSelective(product);
                                        } else {
                                            productMapper.updateByProductIdSelective(product);
                                        }
                                    } else {
                                        for (Sku sku : skus) {
                                            Product product = new Product();
                                            product.setProductId(item.getItemId());
                                            product.setSkuId(sku.getId());
                                            int count = productMapper.getPageCount(product);
                                            product.setSkuTitle(sku.getTitle());
                                            product.setProductName(item.getItemName());
                                            product.setProductPrice(Double.parseDouble(item.getPrice()));
                                            //存在就更新，不存在就添加
                                            if (count == 0) {
                                                productMapper.insertSelective(product);
                                            } else {
                                                productMapper.updateByProductIdSelective(product);
                                            }
                                        }
                                    }
                                }
                            }
                        } else {
                            success = false;
                            reason = "没有查询到商品.";
                        }
                        success = true;
                        pageNum = pageNum + pageSize;
                    } else {
                        success = false;
                        reason = status.getStatusReason();
                    }
                }
            } while (itemNum == pageSize && success);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("doSync, message:{}", e.getMessage());
            success = false;
            reason = "报错了，请联系管理员.";
        }
        json.put("success", success);
        json.put("reason", reason);
        return json;
    }
}
