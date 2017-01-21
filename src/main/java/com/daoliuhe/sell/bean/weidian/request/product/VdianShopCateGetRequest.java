package com.daoliuhe.sell.bean.weidian.request.product;


import com.daoliuhe.sell.bean.weidian.request.AbstractRequest;
import com.daoliuhe.sell.bean.weidian.response.product.VdianShopCateGetResponse;
import com.daoliuhe.sell.exception.OpenException;

/**
 * 获取商品分类<br/>
 * <a href="http://wiki.open.weidian.com/index.php?title=%E8%8E%B7%E5%8F%96%E5%95%86%E5%93%81%E5%88%86%E7%B1%BB">查看接口文档</a>
 * */
public class VdianShopCateGetRequest extends AbstractRequest<VdianShopCateGetResponse> {

  public VdianShopCateGetRequest(String accessToken) {
    super(accessToken);
  }

  @Override
  public String getParam() throws OpenException {
    return null;
  }

}
