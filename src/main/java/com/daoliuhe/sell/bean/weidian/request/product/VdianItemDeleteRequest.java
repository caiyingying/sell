package com.daoliuhe.sell.bean.weidian.request.product;

import com.daoliuhe.sell.bean.weidian.request.AbstractRequest;
import com.daoliuhe.sell.bean.weidian.response.CommonResponse;
import com.daoliuhe.sell.exception.OpenException;
import com.daoliuhe.sell.util.JsonUtils;

import java.util.HashMap;
import java.util.Map;


/**
 * 删除单个商品<br/>
 * <a href="http://wiki.open.weidian.com/index.php?title=%E5%88%A0%E9%99%A4%E5%8D%95%E4%B8%AA%E5%95%86%E5%93%81">查看接口文档</a>
 * */
public class VdianItemDeleteRequest extends AbstractRequest<CommonResponse> {

  private String itemId;

  public VdianItemDeleteRequest(String accessToken, String itemId) {
    super(accessToken);
    this.itemId = itemId;
  }

  @Override
  public String getParam() throws OpenException {
    Map<String, Object> map = new HashMap<String, Object>((int) (1 / .75f) + 1);
    map.put("itemid", this.itemId);
    return JsonUtils.toJson(map);
  }

  public String getItemId() {
    return itemId;
  }

  public void setItemId(String itemId) {
    this.itemId = itemId;
  }

}
