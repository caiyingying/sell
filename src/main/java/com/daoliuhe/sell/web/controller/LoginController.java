package com.daoliuhe.sell.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.xml.ws.RequestWrapper;

/**
 * Created by CYY on 2016/12/25.
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @RequestMapping
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("login");
        return mav;
    }

    @RequestMapping("doLogin")
    public ModelAndView doLogin() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("main");
        return mav;
    }
}
