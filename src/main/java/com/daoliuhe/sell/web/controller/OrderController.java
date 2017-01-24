package com.daoliuhe.sell.web.controller;

import com.daoliuhe.sell.model.OrderProduct;
import com.daoliuhe.sell.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;

/**
 * 订单统计
 * Created by CYY on 2016/12/25.
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Resource
    OrderService orderService;

    @RequestMapping("/list")
    public ModelAndView list(OrderProduct orderProduct) throws UnsupportedEncodingException {
        logger.info("list,orderProduct:{}", orderProduct);
        ModelAndView mav = new ModelAndView("order/list");
        return mav;
    }

    @RequestMapping("/sync")
    @ResponseBody
    public Object syncOrders() {
        logger.info("syncOrders");
        return orderService.doSync();
    }
}
