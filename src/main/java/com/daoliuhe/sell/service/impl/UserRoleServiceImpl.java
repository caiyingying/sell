package com.daoliuhe.sell.service.impl;

import com.daoliuhe.sell.mapper.UserMapper;
import com.daoliuhe.sell.mapper.UserRoleMapper;
import com.daoliuhe.sell.model.UserRole;
import com.daoliuhe.sell.service.UserRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    private static final Logger logger = LoggerFactory.getLogger(UserRoleServiceImpl.class);

    @Autowired
    UserRoleMapper userRoleMapper;

    @Autowired
    UserMapper userMapper;

    @Override
    public Object getRolePageData(UserRole relation) {
        logger.info("getRolePageData,relation:{}", relation);
        Map<String, Object> map = new HashMap<String, Object>();
        int total = userRoleMapper.getRolePageCount(relation);
        map.put("total", total);
        int curPage = relation.getPage();
        int rows = relation.getRows();
        int maxPage = (total + rows - 1) / rows;
        if (curPage > maxPage && maxPage > 0) {
            relation.setPage(maxPage);
        }

        map.put("rows", userRoleMapper.getRolePageData(relation));
        return map;
    }

    @Override
    public Object saveRelation(UserRole relation) {
        logger.info("saveRelation,relation:{}", relation);
        Map json = new HashMap();
        boolean success = false;
        String reason = "";
        // 用户id和角色id联合查询
        if (StringUtils.isEmpty(relation.getUserId()) ||
                StringUtils.isEmpty(relation.getRoleId())) {
            reason = "用户或角色不能为空!";
        } else if (userRoleMapper.select(relation).isEmpty()) {
            int result = userRoleMapper.insert(relation);
            if (result == 1) {
                success = true;
            } else {
                reason = "保存失败!";
            }
        } else {
            reason = "已替换!";
        }
        json.put("success", success);
        json.put("reason", reason);
        return json;
    }

    @Override
    public Object deleteForUser(String userId, String roleIds) {
        logger.info("deleteForUser,userId:{},roleIds:{}", userId, roleIds);
        Map json = new HashMap();
        boolean success = false;
        String reason = "";
        if (StringUtils.isEmpty(userId) || StringUtils.isEmpty(roleIds)) {
            reason = "参数错误!";
        } else {
            List<String> list = Arrays.asList(roleIds.split(","));
            userRoleMapper.deleteRelationForUser(userId, list);
            success = true;
        }

        json.put("success", success);
        json.put("reason", reason);
        return json;
    }

    @Override
    public Object saveBatchRole(String userId, String roleIds) {
        logger.info("saveBatchRole,userId:{},roleIds:{}",userId,roleIds);
        Map<String, Object> json = new HashMap<String, Object>();
        boolean success = false;
        String reason = "";
        if (StringUtils.isEmpty(userId) || StringUtils.isEmpty(roleIds)) {
            reason = "参数错误!";
        } else {
            List<String> roleList = new ArrayList<String>(Arrays.asList(roleIds.split(";")));
            for (String roleId : roleList) {
                UserRole relation = new UserRole();
                relation.setUserId(Integer.parseInt(userId));
                relation.setRoleId(Integer.parseInt(roleId));
                if (userRoleMapper.select(relation).isEmpty()) {
                    userRoleMapper.insert(relation);
                }
            }
            success = true;
        }
        json.put("success", success);
        json.put("reason", reason);
        return json;
    }
}
