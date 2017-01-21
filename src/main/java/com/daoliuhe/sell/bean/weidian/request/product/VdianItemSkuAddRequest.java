package com.daoliuhe.sell.bean.weidian.request.product;

import com.daoliuhe.sell.bean.weidian.entity.Sku;
import com.daoliuhe.sell.bean.weidian.request.AbstractRequest;
import com.daoliuhe.sell.bean.weidian.response.product.CommonSkuResponse;
import com.daoliuhe.sell.exception.OpenException;
import com.daoliuhe.sell.util.JsonUtils;

import java.util.HashMap;
import java.util.Map;


/**
 * 添加商品型号<br/>
 * <a href="http://wiki.open.weidian.com/index.php?title=%E6%B7%BB%E5%8A%A0%E5%95%86%E5%93%81%E5%9E%8B%E5%8F%B7">查看接口文档</a>
 * */
public class VdianItemSkuAddRequest extends AbstractRequest<CommonSkuResponse> {

  private String itemId;
  private Sku[] skus;

  public VdianItemSkuAddRequest(String accessToken, String itemId, Sku[] skus) {
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

  public Sku[] getSkus() {
    return skus;
  }

  public void setSkus(Sku[] skus) {
    this.skus = skus;
  }

}
