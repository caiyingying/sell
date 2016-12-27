package com.daoliuhe.sell.mapper;

import com.daoliuhe.sell.model.Dealers;

public interface DealersMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Dealers record);

    int insertSelective(Dealers record);

    Dealers selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Dealers record);

    int updateByPrimaryKey(Dealers record);
}