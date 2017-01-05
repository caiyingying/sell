package com.daoliuhe.sell.mapper;

import com.daoliuhe.sell.model.Role;
import com.daoliuhe.sell.model.User;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    /**
     * 分页查询
     * @param role
     * @return
     */
    List<User> getPageData(Role role);

    /**
     * 用户数量集合
     * @param role
     * @return
     */
    int getPageCount(Role role);
}