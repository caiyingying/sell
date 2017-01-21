package com.daoliuhe.sell.bean.weidian.response.product;


import com.daoliuhe.sell.bean.weidian.entity.Sku;
import com.daoliuhe.sell.bean.weidian.response.AbstractResponse;

public class CommonSkuResponse extends AbstractResponse {

  private Sku[] result;

  public Sku[] getResult() {
    return result;
  }

  public void setResult(Sku[] result) {
    this.result = result;
  }

}
