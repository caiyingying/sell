package com.daoliuhe.sell.service;

import com.daoliuhe.sell.model.User;

import java.util.Map;

/**
 * Created by CYY on 2016/12/25.
 */
public interface UserService {
    /***
     * 查询分页的用户列表
     * @param user
     * @return
     */
    Map<String, Object> getPageData(User user);

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    User getUserById(Integer id);

    /**
     * 保存或者修改用户
     * @param user
     */
    void saveOrUpdate(User user);

    /**
     * 是否可用的登录名
     * @param loginName 登录名
     * @param id 用户id
     * @return 是否可用
     */
    boolean verifyLoginName(String loginName, String id);
}
