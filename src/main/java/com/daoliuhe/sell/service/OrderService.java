package com.daoliuhe.sell.service;

import java.util.Map;

/**
 * Created by CYY on 2017/1/24.
 */
public interface OrderService {
    /**
     * 同步订单
     */
    Map<String, Object> doSync();
}
