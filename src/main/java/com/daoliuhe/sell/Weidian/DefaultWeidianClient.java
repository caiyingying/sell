package com.daoliuhe.sell.Weidian;

import com.daoliuhe.sell.Weidian.http.DefaultHttpService;
import com.daoliuhe.sell.Weidian.http.HttpService;
import com.daoliuhe.sell.Weidian.http.Param;
import com.daoliuhe.sell.bean.weidian.request.AbstractRequest;
import com.daoliuhe.sell.bean.weidian.response.AbstractResponse;
import com.daoliuhe.sell.exception.OpenException;
import com.daoliuhe.sell.util.Config;
import com.daoliuhe.sell.util.JsonUtils;
import com.daoliuhe.sell.util.UrlUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;


public class DefaultWeidianClient extends AbstractWeidianClient {

  private static final Logger LOGGER = LoggerFactory.getLogger(DefaultWeidianClient.class);

  private static DefaultWeidianClient instance = new DefaultWeidianClient();

  public DefaultWeidianClient() {
    super();
  }

  public static DefaultWeidianClient getInstance() {
    return instance;
  }

  @Override
  public <T extends AbstractResponse> T executeGet(AbstractRequest<T> request) throws OpenException {
    try {
      String response = this.executeGetForString(request);
      LOGGER.debug("response:{}", response);
      return this.parse(response, request.getResponseClass());
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      throw new OpenException("WeidianClient execute failed:", e);
    }
  }

  @Override
  public <T extends AbstractResponse> String executeGetForString(AbstractRequest<T> request) throws OpenException {
    try {
      String url = this.buildUrlForGet(request);
      return this.executeGetForString(url);
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      throw new OpenException("WeidianClient execute failed:", e);
    }
  }

  @Override
  public String executeGetForString(String url) throws OpenException {
    try {
      LOGGER.debug("executeGetForString url:{}", url);
      return this.get(url);
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      throw new OpenException("WeidianClient execute failed:", e);
    }
  }

  @Override
  public <T extends AbstractResponse> T executePost(AbstractRequest<T> request) throws OpenException {
    try {
      String response = this.executePostForString(request);
      LOGGER.debug("response:{}", response);
      return this.parse(response, request.getResponseClass());
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      throw new OpenException("WeidianClient execute failed:", e);
    }
  }

  @Override
  public <T extends AbstractResponse> String executePostForString(AbstractRequest<T> request) throws OpenException {
    try {
      return this.executePostForString(Config.API_URL_FOR_POST,
          new Param(Config.PUBLIC_PARAM, request.getPublic()),
          new Param(Config.BIZ_PARAM, request.getParam()));
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      throw new OpenException("WeidianClient execute failed:", e);
    }
  }

  @Override
  public String executePostForString(String url, Param publicParam, Param bizParam) throws OpenException {
    try {
      LOGGER.debug("executePostForString url:{}", url);
      return this.post(url, publicParam, bizParam);
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      throw new OpenException("WeidianClient execute failed:", e);
    }
  }

  @Override
  public <T extends AbstractResponse> T multipart(AbstractRequest<T> request) throws OpenException {
    try {
      String url = String.format(Config.MEDIA_UPLOAD_URL_TEMPLATE, request.getAccessToken());
      LOGGER.debug("multipart url:{}", url);
      String response = this.getHttpService().multipart(url, request.getMultipartName(), request.getMultipartContent());
      LOGGER.debug("response:{}", response);
      return this.parse(response, request.getResponseClass());
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      throw new OpenException("WeidianClient execute failed:", e);
    }
  }

  public HttpService getHttpService() {
    if (httpService == null) {
      this.setHttpService(DefaultHttpService.getInstance());
    }
    return httpService;
  }

  private <T extends AbstractResponse> String buildUrlForGet(AbstractRequest<T> request) throws OpenException {
    String pub = UrlUtils.encode(request.getPublic());
    String param = request.getParam();
    param = StringUtils.isEmpty(param) ? "" : UrlUtils.encode(param);
    return String.format(Config.API_URL_TEMPLATE_FOR_GET, pub, param);
  }

    private String get(String url) throws OpenException {
    return this.getHttpService().get(url);
  }

  private String post(String url, Param... params) throws OpenException {
    return this.getHttpService().post(url, params);
  }

  private <T extends AbstractResponse> T parse(String response, Class<T> cls) throws OpenException {
    return JsonUtils.toObject(response, cls);
  }

}
