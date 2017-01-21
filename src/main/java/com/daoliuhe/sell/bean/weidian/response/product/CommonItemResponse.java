package com.daoliuhe.sell.bean.weidian.response.product;


import com.daoliuhe.sell.bean.weidian.entity.Item;
import com.daoliuhe.sell.bean.weidian.response.AbstractResponse;

public class CommonItemResponse extends AbstractResponse {

  private Item result;

  public Item getResult() {
    return result;
  }

  public void setResult(Item result) {
    this.result = result;
  }

}
