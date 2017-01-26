package com.daoliuhe.sell.service;

import com.daoliuhe.sell.model.OrderProduct;

import java.util.Map;

/**
 * Created by CYY on 2017/1/24.
 */
public interface OrderService {
    /**
     * 同步订单
     */
    Map<String, Object> doSync();

    /**
     * 分页查询
     * @param orderProduct
     * @return
     */
    Map<String, Object> getPageData(OrderProduct orderProduct);
}
