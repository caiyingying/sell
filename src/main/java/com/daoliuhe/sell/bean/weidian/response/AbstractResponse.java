package com.daoliuhe.sell.bean.weidian.response;


import com.daoliuhe.sell.exception.OpenException;
import com.daoliuhe.sell.util.JsonUtils;

public abstract class AbstractResponse {

  protected Status status;

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  @Override
  public String toString() {
    try {
      return JsonUtils.toJson(this);
    } catch (OpenException e) {
      e.printStackTrace();
      return null;
    }
  }

}
