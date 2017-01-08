package com.daoliuhe.sell.mapper;

import com.daoliuhe.sell.model.Dealers;
import com.daoliuhe.sell.model.User;

import java.util.List;

public interface DealersMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Dealers record);

    int insertSelective(Dealers record);

    Dealers selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Dealers record);

    int updateByPrimaryKey(Dealers record);

    /**
     * 分页查询
     * @param dealers
     * @return
     */
    List<User> getPageData(Dealers dealers);

    /**
     * 用户数量集合
     * @param dealers
     * @return
     */
    int getPageCount(Dealers dealers);
}