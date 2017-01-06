package com.daoliuhe.sell.mapper;

import com.daoliuhe.sell.model.Role;
import com.daoliuhe.sell.model.UserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    UserRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);

    int getRolePageCount(UserRole record);

    List<Role> getRolePageData(UserRole record);

    /**
     * 根据工号查询角色
     * @param loginName
     * @return
     */
    List<Role> getRolesByLoginName(@Param("loginName")String loginName);

    List<UserRole> select(UserRole record);

    int deleteRelationForUser(@Param("userId") String userId, @Param("roleIds") List<String> roleIds);
}