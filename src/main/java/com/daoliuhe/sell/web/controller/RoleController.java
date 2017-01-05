package com.daoliuhe.sell.web.controller;

import com.daoliuhe.sell.model.Role;
import com.daoliuhe.sell.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;

/**
 * Created by CYY on 2016/12/25.
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Resource
    RoleService roleService;

    @RequestMapping("/list")
    public ModelAndView list(Role role) throws UnsupportedEncodingException {
        logger.info("list,role:{}", role);
        ModelAndView mav = new ModelAndView("role/list");
        mav.addAllObjects(roleService.getPageData(role));
        mav.addObject("entity", role);
        return mav;
    }

    @RequestMapping("/data")
    @ResponseBody
    public Object data(Role role) {
        logger.info("data,role:{}", role);
        return roleService.getPageData(role);
    }
}
