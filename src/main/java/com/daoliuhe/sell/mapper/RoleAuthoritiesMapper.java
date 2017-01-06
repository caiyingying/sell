package com.daoliuhe.sell.mapper;

import com.daoliuhe.sell.model.Authorities;
import com.daoliuhe.sell.model.RoleAuthorities;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleAuthoritiesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RoleAuthorities record);

    int insertSelective(RoleAuthorities record);

    RoleAuthorities selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RoleAuthorities record);

    int updateByPrimaryKey(RoleAuthorities record);

    /**
     * 根据工号查询权限
     * @param loginId
     * @return
     */
    List<Authorities> getAuthByLoginId(@Param("loginId")String loginId);
}