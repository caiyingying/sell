package com.daoliuhe.sell.web.controller;

import com.daoliuhe.sell.model.Customer;
import com.daoliuhe.sell.model.User;
import com.daoliuhe.sell.service.CustomerService;
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
@RequestMapping("/customer")
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Resource
    CustomerService customerService;

    @RequestMapping("/list")
    public ModelAndView list(Customer customer) throws UnsupportedEncodingException {
        logger.info("list,customer:{}",customer);
        ModelAndView mav = new ModelAndView("customer/list");
        mav.addAllObjects(customerService.getPageData(customer));
        mav.addObject("entity", customer);
        return mav;
    }

    @RequestMapping("/data")
    @ResponseBody
    public Object data(Customer customer) {
        logger.info("data,customer:{}",customer);
        return customerService.getPageData(customer);
    }
}
