package com.daoliuhe.sell.service.impl;

import com.daoliuhe.sell.mapper.RoleAuthoritiesMapper;
import com.daoliuhe.sell.mapper.UserMapper;
import com.daoliuhe.sell.mapper.UserRoleMapper;
import com.daoliuhe.sell.model.Authorities;
import com.daoliuhe.sell.model.Role;
import com.daoliuhe.sell.model.User;
import com.daoliuhe.sell.service.UserService;
import com.daoliuhe.sell.util.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * Created by CYY on 2016/12/25.
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserRoleMapper userRoleMapper;

    @Autowired
    RoleAuthoritiesMapper roleAuthoritiesMapper;

    @Override
    public Map<String, Object> getPageData(User user) {
        logger.info("getPageData,user:{}", user);
        Map<String, Object> map = new HashMap<String, Object>();
        int total = userMapper.getPageCount(user);
        map.put("total", total);
        int curPage = user.getPage();
        int rows = user.getRows();
        int maxPage = (total + rows - 1) / rows;
        if (curPage > maxPage && maxPage > 0) {
            user.setPage(maxPage);
        }
        map.put("rows", userMapper.getPageData(user));
        return map;
    }

    @Override
    public User getUserById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public User selectByLoginName(String loginName) {
        return userMapper.selectByLoginName(loginName);
    }

    @Override
    public void saveOrUpdate(User user) {
        logger.info("saveOrUpdate,user:{}", user);
        if (StringUtils.hasText(user.getUserPassword())) {
            String password = BCrypt.hashpw(user.getUserPassword(), BCrypt.gensalt(12));
            user.setUserPassword(password);
        }
        if (!StringUtils.isEmpty(user.getId())) {
            userMapper.updateByPrimaryKeySelective(user);
        } else {
            userMapper.insert(user);
        }
    }

    @Override
    public boolean verifyLoginName(String loginName, String id) {
        logger.info("verifyLoginName,loginName:{}, id:{}", loginName, id);
        boolean ret;
        User retUser = userMapper.selectByLoginName(loginName);
        if (StringUtils.hasText(id)) {
            if (null == retUser) {
                ret = true;
            } else {
                if (id.equals(String.valueOf(retUser.getId()))) {
                    ret = true;
                } else {
                    ret = false;
                }
            }
        } else {
            ret = null == retUser;
        }
        return ret;
    }

    @Override
    public Set<String> selectRolesByLoginName(String loginName) {
        logger.info("selectRolesByLoginName,loginName:{}", loginName);
        Set<String> roleSet = new HashSet<String>();
        List<Role> roleList = userRoleMapper.getRolesByLoginName(loginName);
        for (Role role : roleList) {
            roleSet.add(role.getName());
        }
        return roleSet;
    }

    @Override
    public Set<String> selectPermissionsByLoginName(String loginName) {
        logger.info("selectPermissionsByLoginName,loginName:{}", loginName);
        Set<String> permissionSet = new HashSet<String>();
        List<Authorities> list = roleAuthoritiesMapper.getAuthByLoginName(loginName);
        for (Authorities auth : list) {
            permissionSet.add(auth.getPermission());
        }
        return permissionSet;
    }

    @Override
    public void updateUser(User user) {
        logger.info("updateUser,user:{}", user);
        userMapper.updateByPrimaryKeySelective(user);
    }
}
