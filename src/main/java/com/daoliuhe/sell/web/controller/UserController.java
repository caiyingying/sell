package com.daoliuhe.sell.web.controller;

import com.daoliuhe.sell.model.User;
import com.daoliuhe.sell.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;

/**
 * Created by CYY on 2016/12/25.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    UserService userService;

    @RequestMapping("/list")
    public ModelAndView list(User user) throws UnsupportedEncodingException {
        logger.info("list,user:{}",user);
        ModelAndView mav = new ModelAndView("user/list");
        mav.addAllObjects(userService.getPageData(user));
        mav.addObject("entity", user);
        return mav;
    }

    @RequestMapping("/data")
    @ResponseBody
    public Object data(User user) {
        logger.info("data,user:{}",user);
        return userService.getPageData(user);
    }

    @RequestMapping("/edit")
    public ModelAndView edit(User user) throws UnsupportedEncodingException {
        logger.info("edit,user:{}",user);
        ModelAndView mav = new ModelAndView("user/edit");
        if (!StringUtils.isEmpty(user.getId())) {
            User ret = userService.getUserById(user.getId());
            mav.addObject("user", ret);
        }
        return mav;
    }

    @RequestMapping("/saveOrUpdate")
    public String saveOrUpdate(User user) {
        userService.saveOrUpdate(user);
        return "redirect:/user/list";
    }

    @RequestMapping("/updatePassword")
    @ResponseBody
    public Object updatePassword(User user) {
        return userService.updateUser(user);
    }


    @RequestMapping("/vLoginId")
    @ResponseBody
    public Object verifyLoginName(String loginName, String id) {
        logger.info("data,loginId:{},id:{}",loginName,id);
        return userService.verifyLoginName(loginName, id);
    }


}
