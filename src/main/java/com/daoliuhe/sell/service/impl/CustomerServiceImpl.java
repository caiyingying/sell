package com.daoliuhe.sell.service.impl;

import com.daoliuhe.sell.mapper.CustomerMapper;
import com.daoliuhe.sell.mapper.RoleAuthoritiesMapper;
import com.daoliuhe.sell.mapper.UserMapper;
import com.daoliuhe.sell.mapper.UserRoleMapper;
import com.daoliuhe.sell.model.Authorities;
import com.daoliuhe.sell.model.Customer;
import com.daoliuhe.sell.model.Role;
import com.daoliuhe.sell.model.User;
import com.daoliuhe.sell.service.CustomerService;
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
public class CustomerServiceImpl implements CustomerService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Autowired
    CustomerMapper customerMapper;

    @Override
    public Map<String, Object> getPageData(Customer customer) {
        logger.info("getPageData,customer:{}", customer);
        Map<String, Object> map = new HashMap<String, Object>();
        int total = customerMapper.getPageCount(customer);
        map.put("total", total);
        int curPage = customer.getPage();
        int rows = customer.getRows();
        int maxPage = (total + rows - 1) / rows;
        if (curPage > maxPage && maxPage > 0) {
            customer.setPage(maxPage);
        }
        map.put("rows", customerMapper.getPageData(customer));
        return map;
    }
}
