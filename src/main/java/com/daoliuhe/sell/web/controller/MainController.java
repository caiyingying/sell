package com.daoliuhe.sell.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by CYY on 2016/12/25.
 */
@Controller
public class MainController {
    @RequestMapping("main")
    public ModelAndView doLogin() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("main");
        return mav;
    }
}
