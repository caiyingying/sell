package com.daoliuhe.sell.Weidian;


import com.daoliuhe.sell.Weidian.http.HttpService;
import com.daoliuhe.sell.Weidian.http.Param;
import com.daoliuhe.sell.bean.weidian.request.AbstractRequest;
import com.daoliuhe.sell.bean.weidian.response.AbstractResponse;
import com.daoliuhe.sell.exception.OpenException;

public abstract class AbstractWeidianClient {

  protected HttpService httpService;

  public abstract <T extends AbstractResponse> T executeGet(AbstractRequest<T> request) throws OpenException;

  public abstract <T extends AbstractResponse> String executeGetForString(AbstractRequest<T> request)
      throws OpenException;

  public abstract String executeGetForString(String url) throws OpenException;

  public abstract <T extends AbstractResponse> T executePost(AbstractRequest<T> request) throws OpenException;

  public abstract <T extends AbstractResponse> String executePostForString(AbstractRequest<T> request)
      throws OpenException;

  public abstract String executePostForString(String url, Param publicParam, Param bizParam) throws OpenException;

  public abstract <T extends AbstractResponse> T multipart(AbstractRequest<T> request) throws OpenException;

  public void setHttpService(HttpService httpService) {
    this.httpService = httpService;
  }
}
