package com.daoliuhe.sell.mapper;

import com.daoliuhe.sell.model.OrderProduct;
import com.daoliuhe.sell.model.Product;

import java.util.List;

public interface OrderProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderProduct record);

    int insertSelective(OrderProduct record);

    OrderProduct selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderProduct record);

    int updateByPrimaryKey(OrderProduct record);

    /**
     * 分页查询
     * @param orderProduct
     * @return
     */
    List<OrderProduct> getPageData(OrderProduct orderProduct);

    /**
     * 用户数量集合
     * @param orderProduct
     * @return
     */
    int getPageCount(OrderProduct orderProduct);
}