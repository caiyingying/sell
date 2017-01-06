package com.daoliuhe.sell.service;

import com.daoliuhe.sell.model.User;

import java.util.Map;
import java.util.Set;

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
     * 根据登录名查用户
     * @param loginName 用户名
     * @return 用户
     */
    User selectByLoginName(String loginName);

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

    /**
     * 根据用户名查询对应的角色
     * @param loginName
     * @return
     */
    Set<String> selectRolesByLoginName(String loginName);

    /**
     * 根据用户名查询对应的权限
     * @param loginName
     * @return
     */
    Set<String> selectPermissionsByLoginName(String loginName);
}
