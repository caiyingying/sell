package com.daoliuhe.sell.mapper;

import com.daoliuhe.sell.model.RoleAuthorities;

public interface RoleAuthoritiesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RoleAuthorities record);

    int insertSelective(RoleAuthorities record);

    RoleAuthorities selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RoleAuthorities record);

    int updateByPrimaryKey(RoleAuthorities record);
}