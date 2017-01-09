package com.daoliuhe.sell.mapper;

import com.daoliuhe.sell.model.Product;

import java.util.List;

public interface ProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);


    /**
     * 分页查询
     * @param product
     * @return
     */
    List<Product> getPageData(Product product);

    /**
     * 用户数量集合
     * @param product
     * @return
     */
    int getPageCount(Product product);
}