package com.daoliuhe.sell.service;

import com.daoliuhe.sell.model.Dealers;
import com.daoliuhe.sell.model.User;

import java.util.Map;
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
}
