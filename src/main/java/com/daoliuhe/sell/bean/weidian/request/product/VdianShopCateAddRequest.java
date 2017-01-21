package com.daoliuhe.sell.bean.weidian.request.product;

import com.daoliuhe.sell.bean.weidian.entity.Cate;
import com.daoliuhe.sell.bean.weidian.request.AbstractRequest;
import com.daoliuhe.sell.bean.weidian.response.CommonResponse;
import com.daoliuhe.sell.exception.OpenException;
import com.daoliuhe.sell.util.JsonUtils;

import java.util.HashMap;
import java.util.Map;


/**
 * 新增商品分类<br/>
 * <a href="http://wiki.open.weidian.com/index.php?title=%E6%96%B0%E5%A2%9E%E5%95%86%E5%93%81%E5%88%86%E7%B1%BB">查看接口文档</a>
 * */
public class VdianShopCateAddRequest extends AbstractRequest<CommonResponse> {

  private Cate[] cates;

  public VdianShopCateAddRequest(String accessToken, Cate[] cates) {
    super(accessToken);
    this.cates = cates;
  }

  @Override
  public String getParam() throws OpenException {
    Map<String, Object> map = new HashMap<String, Object>((int) (1 / .75f) + 1);
    map.put("cates", this.cates);
    return JsonUtils.toJson(map);
  }

  public Cate[] getCates() {
    return cates;
  }

  public void setCates(Cate[] cates) {
    this.cates = cates;
  }

}
