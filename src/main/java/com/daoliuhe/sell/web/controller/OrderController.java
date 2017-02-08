package com.daoliuhe.sell.web.controller;

import com.daoliuhe.sell.model.OrderProduct;
import com.daoliuhe.sell.model.Product;
import com.daoliuhe.sell.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        mav.addAllObjects(orderService.getPageData(orderProduct));
        mav.addObject("entity", orderProduct);
        return mav;
    }

    @RequestMapping("/data")
    @ResponseBody
    public Object data(OrderProduct orderProduct) {
        logger.info("data,orderProduct:{}", orderProduct);
        if (!StringUtils.isEmpty(orderProduct.getPayTimeBegin())) {
            orderProduct.setPayTimeBegin(orderProduct.getPayTimeBegin() + " 00:00:00");
        }
        if (!StringUtils.isEmpty(orderProduct.getPayTimeEnd())) {
            orderProduct.setPayTimeEnd(orderProduct.getPayTimeEnd() + " 23:59:59");
        }

        return orderService.getPageData(orderProduct);
    }

    @RequestMapping("/sync")
    @ResponseBody
    public Object syncOrders() {
        logger.info("syncOrders");
        return orderService.doSync();
    }

    @RequestMapping("/doRebate")
    @ResponseBody
    public Object doRebate(List<OrderProduct> orderProducts) {
        logger.info("doRebate, orderProducts:{}", orderProducts);
        return orderService.doRebate(orderProducts);
    }
}
