package com.daoliuhe.sell.service.impl;

import com.daoliuhe.sell.mapper.UserMapper;
import com.daoliuhe.sell.model.User;
import com.daoliuhe.sell.service.UserService;
import com.daoliuhe.sell.util.BCrypt;
import com.daoliuhe.sell.util.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by CYY on 2016/12/25.
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    UserMapper userMapper;

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
    public void saveOrUpdate(User user) {
        logger.info("saveOrUpdate,user:{}", user);
        if (StringUtils.hasText(user.getUserPassword())) {
            String password = BCrypt.hashpw(user.getUserPassword(), BCrypt.gensalt(12));
            user.setUserPassword(password);
        }
        if (!StringUtils.isEmpty(user.getId())) {
            userMapper.updateByPrimaryKey(user);
        } else {
            userMapper.insert(user);
        }
    }

    @Override
    public boolean isExistLoginName(String loginName, String id) {
        logger.info("isExistLoginName,loginName:{}, id:{}", loginName, id);
        boolean ret = false;
        User retUser = userMapper.selectByLoginName(loginName);
        if (StringUtils.hasText(id)) {
            if (id.equals(retUser)) {
                ret = true;
            } else {
                ret = false;
            }
        } else {
            ret = null == retUser;
        }
        return ret;
    }
}
