package com.daoliuhe.sell.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by CYY on 2016/12/25.
 */
@Controller
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    @RequestMapping("main")
    public ModelAndView doLogin() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("main");
        return mav;
    }
}
