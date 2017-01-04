package com.daoliuhe.sell.mapper;

import com.daoliuhe.sell.model.User;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    /**
     * 根据登录名称查询用户
     * @param loginName
     * @return
     */
    User selectByLoginName(String loginName);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 分页查询
     * @param user
     * @return
     */
    List<User> getPageData(User user);

    /**
     * 用户数量集合
     * @param user
     * @return
     */
    int getPageCount(User user);
}