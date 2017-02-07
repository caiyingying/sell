package com.daoliuhe.sell.mapper;

import com.daoliuhe.sell.model.SyncTime;

public interface SyncTimeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SyncTime record);

    int insertSelective(SyncTime record);

    SyncTime selectByPrimaryKey(Integer id);

    SyncTime selectByName(String name);

    int updateByPrimaryKeySelective(SyncTime record);

    int updateByPrimaryKey(SyncTime record);

    int updateTimeByName(SyncTime record);
}