package com.daoliuhe.sell.bean.weidian.request.order;

import com.daoliuhe.sell.bean.weidian.request.AbstractRequest;
import com.daoliuhe.sell.bean.weidian.response.CommonResponse;
import com.daoliuhe.sell.exception.OpenException;
import com.daoliuhe.sell.util.JsonUtils;

import java.util.HashMap;
import java.util.Map;


/**
 * 修改订单价格<br/>
 * <a href="http://wiki.open.weidian.com/index.php?title=%E4%BF%AE%E6%94%B9%E8%AE%A2%E5%8D%95%E4%BB%B7%E6%A0%BC">查看接口文档</a>
 * */
public class VdianOrderModifyRequest extends AbstractRequest<CommonResponse> {

  private String orderId;
  private String totalItemsPrice;
  private String expressPrice;

  public VdianOrderModifyRequest(String accessToken) {
    super(accessToken);
  }

  @Override
  public String getParam() throws OpenException {
    Map<String, Object> map = new HashMap<String, Object>((int) (3 / .75f) + 1);
    map.put("order_id", this.orderId);
    map.put("total_items_price", this.totalItemsPrice);
    map.put("express_price", this.expressPrice);
    return JsonUtils.toJson(map);
  }

  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  public String getTotalItemsPrice() {
    return totalItemsPrice;
  }

  public void setTotalItemsPrice(String totalItemsPrice) {
    this.totalItemsPrice = totalItemsPrice;
  }

  public String getExpressPrice() {
    return expressPrice;
  }

  public void setExpressPrice(String expressPrice) {
    this.expressPrice = expressPrice;
  }

}
