package com.daoliuhe.sell.bean.weidian.request.product;

import com.daoliuhe.sell.bean.weidian.request.AbstractRequest;
import com.daoliuhe.sell.bean.weidian.response.product.CommonSkuResponse;
import com.daoliuhe.sell.exception.OpenException;
import com.daoliuhe.sell.util.JsonUtils;

import java.util.HashMap;
import java.util.Map;


/**
 * 删除商品型号<br/>
 * <a href="http://wiki.open.weidian.com/index.php?title=%E5%88%A0%E9%99%A4%E5%95%86%E5%93%81%E5%9E%8B%E5%8F%B7">查看接口文档</a>
 * */
public class VdianItemSkuDeleteRequest extends AbstractRequest<CommonSkuResponse> {

  private String itemId;
  private String[] skus;

  public VdianItemSkuDeleteRequest(String accessToken, String itemId, String[] skus) {
    super(accessToken);
    this.itemId = itemId;
    this.skus = skus;
  }

  @Override
  public String getParam() throws OpenException {
    Map<String, Object> map = new HashMap<String, Object>((int) (2 / .75f) + 1);
    map.put("itemid", this.itemId);
    map.put("skus", skus);
    return JsonUtils.toJson(map);
  }

  public String getItemId() {
    return itemId;
  }

  public void setItemId(String itemId) {
    this.itemId = itemId;
  }

  public String[] getSkus() {
    return skus;
  }

  public void setSkus(String[] skus) {
    this.skus = skus;
  }

}
