package com.daoliuhe.sell.service;

import com.daoliuhe.sell.model.Role;
import com.daoliuhe.sell.model.User;

import java.util.Map;

/**
 * Created by CYY on 2016/12/25.
 */
public interface RoleService {
    /***
     * 查询分页的用户列表
     * @param user
     * @return
     */
    Map<String, Object> getPageData(Role role);
}
