package com.daoliuhe.sell.web.controller;

import com.daoliuhe.sell.model.Dealers;
import com.daoliuhe.sell.service.DealersService;
import com.daoliuhe.sell.service.DealersService;
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
 * 分销商管理
 * Created by CYY on 2016/12/25.
 */
@Controller
@RequestMapping("/dealers")
public class DealersController {

    private static final Logger logger = LoggerFactory.getLogger(DealersController.class);

    @Resource
    DealersService dealersService;

    @RequestMapping("/list")
    public ModelAndView list(Dealers dealers) throws UnsupportedEncodingException {
        logger.info("list,dealers:{}",dealers);
        ModelAndView mav = new ModelAndView("dealers/list");
        mav.addAllObjects(dealersService.getPageData(dealers));
        mav.addObject("entity", dealers);
        return mav;
    }

    @RequestMapping("/data")
    @ResponseBody
    public Object data(Dealers dealers) {
        logger.info("data,dealers:{}",dealers);
        return dealersService.getPageData(dealers);
    }

    @RequestMapping("/edit")
    public ModelAndView edit(Dealers dealers) throws UnsupportedEncodingException {
        logger.info("edit,dealers:{}",dealers);
        ModelAndView mav = new ModelAndView("dealers/edit");
        if (!StringUtils.isEmpty(dealers.getId())) {
            Dealers ret = dealersService.getDealersById(dealers.getId());
            mav.addObject("dealers", ret);
        }
        return mav;
    }

    @RequestMapping("/saveOrUpdate")
    public String saveOrUpdate(Dealers dealers) {
        dealersService.saveOrUpdate(dealers);
        return "redirect:/dealers/list";
    }
}
