package com.daoliuhe.sell.service;

import com.daoliuhe.sell.model.Product;

import java.util.List;
import java.util.Map;

/**
 * Created by CYY on 2016/12/25.
 */
public interface ProductService {
    /***
     * 查询分页的产品列表
     * @param product
     * @return
     */
    Map<String, Object> getPageData(Product product);

    /**
     * 查询所有的产品，作为列表项
     * @param product 产线参数
     * @return 产品集合
     */
    List<Product> getListData(Product product);

    /**
     * 根据id查询产品
     * @param id
     * @return
     */
    Product getProductById(Integer id);

    /**
     * 保存或者修改产品
     * @param product
     */
    void saveOrUpdate(Product product);

    /**
     * 更新产品
     * @param product
     */
    void updateProduct(Product product);

    /**
     * 同步产品
     */
    Map<String, Object> doSync();

}
