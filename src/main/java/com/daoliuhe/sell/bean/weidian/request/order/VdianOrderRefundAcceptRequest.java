package com.daoliuhe.sell.bean.weidian.request.order;

import com.daoliuhe.sell.bean.weidian.request.AbstractRequest;
import com.daoliuhe.sell.bean.weidian.response.CommonResponse;
import com.daoliuhe.sell.exception.OpenException;
import com.daoliuhe.sell.util.JsonUtils;

import java.util.HashMap;
import java.util.Map;


/**
 * 订单退款<br/>
 * <a href="http://wiki.open.weidian.com/index.php?title=%E8%AE%A2%E5%8D%95%E9%80%80%E6%AC%BE">查看接口文档</a>
 * */
public class VdianOrderRefundAcceptRequest extends AbstractRequest<CommonResponse> {

  private String orderId;
  private String isAccept;

  public VdianOrderRefundAcceptRequest(String accessToken, String orderId, String isAccept) {
    super(accessToken);
    this.orderId = orderId;
    this.isAccept = isAccept;
  }

  @Override
  public String getParam() throws OpenException {
    Map<String, Object> map = new HashMap<String, Object>((int) (2 / .75f) + 1);
    map.put("order_id", this.orderId);
    map.put("is_accept", this.isAccept);
    return JsonUtils.toJson(map);
  }

  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  public String getIsAccept() {
    return isAccept;
  }

  public void setIsAccept(String isAccept) {
    this.isAccept = isAccept;
  }

}
