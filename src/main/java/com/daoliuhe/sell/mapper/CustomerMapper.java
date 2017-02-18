package com.daoliuhe.sell.mapper;

import com.daoliuhe.sell.model.Customer;
import com.daoliuhe.sell.model.User;

import java.util.Date;
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

    /**
     * 设置失效，有生效时间的才设置失效时间
     * @param customer
     */
    void updateDisableCustomer(Customer customer);

    /**
     * 设置生效，不存在生效时间和失效时间的才设置
     * @param customer
     */
    void updateEnableCustomer(Customer customer);

    /**
     * 根据付款时间获取当时的分销商
     * @param customer
     * @return
     */
    Customer getDealersByTime(Customer customer);
}