package com.daoliuhe.sell.mapper;

import com.daoliuhe.sell.model.Customer;
import com.daoliuhe.sell.model.User;

import java.util.List;

public interface CustomerMapper {
    int deleteByPrimaryKey(Integer id);

    int deleteByWechat(String wechat);

    int insert(Customer record);

    int insertSelective(Customer record);

    Customer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Customer record);

    int updateByPrimaryKey(Customer record);


    /**
     * 分页查询
     * @param customer
     * @return
     */
    List<Customer> getPageData(Customer customer);

    /**
     * 用户数量集合
     * @param customer
     * @return
     */
    int getPageCount(Customer customer);
}