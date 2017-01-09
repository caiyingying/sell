package com.daoliuhe.sell.mapper;

import com.daoliuhe.sell.model.DealersUser;
import com.daoliuhe.sell.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DealersUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DealersUser record);

    int insertSelective(DealersUser record);

    DealersUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DealersUser record);

    int updateByPrimaryKey(DealersUser record);

    int getUserPageCount(DealersUser record);

    List<User> getUserPageData(DealersUser record);

    List<DealersUser> select(DealersUser record);

    void deleteRelationForUser(@Param("dealersId") String dealersId, @Param("userIds") List<String> userIds);
}