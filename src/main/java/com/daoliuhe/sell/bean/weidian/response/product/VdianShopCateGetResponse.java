package com.daoliuhe.sell.bean.weidian.response.product;


import com.daoliuhe.sell.bean.weidian.entity.Cate;
import com.daoliuhe.sell.bean.weidian.response.AbstractResponse;

public class VdianShopCateGetResponse extends AbstractResponse {

  private Cate[] result;

  public Cate[] getResult() {
    return result;
  }

  public void setResult(Cate[] result) {
    this.result = result;
  }

}
