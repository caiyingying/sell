package com.daoliuhe.sell.service.impl;

import com.daoliuhe.sell.mapper.RoleMapper;
import com.daoliuhe.sell.model.Role;
import com.daoliuhe.sell.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by CYY on 2016/12/25.
 */
@Service
public class RoleServiceImpl implements RoleService {

    private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Autowired
    RoleMapper roleMapper;

    @Override
    public Map<String, Object> getPageData(Role role) {
        logger.info("getPageData,role:{}", role);
        Map<String, Object> map = new HashMap<String, Object>();
        int total = roleMapper.getPageCount(role);
        map.put("total", total);
        int curPage = role.getPage();
        int rows = role.getRows();
        int maxPage = (total + rows - 1) / rows;
        if (curPage > maxPage && maxPage > 0) {
            role.setPage(maxPage);
        }
        map.put("rows", roleMapper.getPageData(role));
        return map;
    }
}
