package com.daoliuhe.sell.service;

import com.daoliuhe.sell.model.Customer;
import com.daoliuhe.sell.model.User;

import java.util.Map;
import java.util.Set;

/**
 * Created by CYY on 2016/12/25.
 */
public interface CustomerService {
    /***
     * 查询分页的客户列表
     * @param customer
     * @return
     */
    Map<String, Object> getPageData(Customer customer);

    /***
     * 更新客户关系
     * @param customer
     */
    void updateCustomerRel(Customer customer);
}
