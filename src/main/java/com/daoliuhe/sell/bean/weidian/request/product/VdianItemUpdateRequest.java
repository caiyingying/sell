package com.daoliuhe.sell.bean.weidian.request.product;


import com.daoliuhe.sell.bean.weidian.entity.Item;
import com.daoliuhe.sell.bean.weidian.request.AbstractRequest;
import com.daoliuhe.sell.bean.weidian.response.product.CommonItemResponse;
import com.daoliuhe.sell.exception.OpenException;
import com.daoliuhe.sell.util.JsonUtils;

/**
 * 更新商品信息<br/>
 * <a href="http://wiki.open.weidian.com/index.php?title=%E6%9B%B4%E6%96%B0%E5%95%86%E5%93%81%E4%BF%A1%E6%81%AF">查看接口文档</a>
 * */
public class VdianItemUpdateRequest extends AbstractRequest<CommonItemResponse> {

  private Item item;

  public VdianItemUpdateRequest(String accessToken, Item item) {
    super(accessToken);
    this.item = item;
  }

  @Override
  public String getParam() throws OpenException {
    return JsonUtils.toJson(this.item);
  }

  public Item getItem() {
    return item;
  }

  public void setItem(Item item) {
    this.item = item;
  }

}
