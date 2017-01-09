package com.daoliuhe.sell.service;

import com.daoliuhe.sell.model.Dealers;
import com.daoliuhe.sell.model.DealersUser;
import com.daoliuhe.sell.model.User;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Created by CYY on 2016/12/25.
 */
public interface DealersService {
    /***
     * 查询分页的分销商列表
     * @param dealers
     * @return
     */
    Map<String, Object> getPageData(Dealers dealers);

    /**
     * 查询分销商管理的权限用户
     * @param dealersUser 分销商用户
     * @return 权限用户
     */
    Map<String, Object> getUserPageData(DealersUser dealersUser);

    /**
     * 保存或者修改分销商
     * @param dealers
     */
    void saveOrUpdate(Dealers dealers);

    /**
     * 根据id查询分销商
     * @param id
     * @return
     */
    Dealers getDealersById(Integer id);

    /**
     * 添加权限用户
     * @param dealersId 分销商id
     * @param userIds 用户idj集合
     * @return 添加结果
     */
    Map<String, Object> saveBatchUser(String dealersId, String userIds);

    /**
     * 删除分销商和用户的关联
     * @param userId
     * @param dealersIds
     * @return
     */
    Object deleteForUser(String userId, String dealersIds);

    /**
     * 保存分销商和用户的关系
     * @param dealersUser
     * @return
     */
    Map<String, Object> saveRelationUser(DealersUser dealersUser);
}
