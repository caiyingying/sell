package com.daoliuhe.sell.mapper;

import com.daoliuhe.sell.model.DealersUser;

public interface DealersUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DealersUser record);

    int insertSelective(DealersUser record);

    DealersUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DealersUser record);

    int updateByPrimaryKey(DealersUser record);
}