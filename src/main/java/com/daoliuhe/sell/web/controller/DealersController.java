package com.daoliuhe.sell.web.controller;

import com.daoliuhe.sell.model.Dealers;
import com.daoliuhe.sell.model.DealersUser;
import com.daoliuhe.sell.model.UserRole;
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
        logger.info("list,dealers:{}", dealers);
        ModelAndView mav = new ModelAndView("dealers/list");
        mav.addAllObjects(dealersService.getPageData(dealers));
        mav.addObject("entity", dealers);
        return mav;
    }

    @RequestMapping("/data")
    @ResponseBody
    public Object data(Dealers dealers) {
        logger.info("data,dealers:{}", dealers);
        return dealersService.getPageData(dealers);
    }

    @RequestMapping("/userData")
    @ResponseBody
    public Object roleData(DealersUser entity) {
        logger.info("roleData,entity:{}", entity);
        return dealersService.getUserPageData(entity);
    }

    @RequestMapping("/edit")
    public ModelAndView edit(Dealers dealers) throws UnsupportedEncodingException {
        logger.info("edit,dealers:{}", dealers);
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

    /**
     * 保存用户分销商关系
     * @param entity
     * @return
     */
    @RequestMapping("/saveRelUser")
    @ResponseBody
    public Object saveRelation(DealersUser entity) {
        logger.info("saveRelation,entity:{}",entity);
        return dealersService.saveRelationUser(entity);
    }

    @RequestMapping("/saveBatchUser")
    @ResponseBody
    public Object saveBatchUser(String dealersId, String userIds) {
        logger.info("saveBatchUser(dealersId:{}, userIds:{})", dealersId, userIds);
        return dealersService.saveBatchUser(dealersId, userIds);
    }

    /**
     * 删除分销商的权限用户
     * @param userIds
     * @param dealersId
     * @return
     */
    @RequestMapping("/deleteUsers")
    @ResponseBody
    public Object deleteForUser(String userIds, String dealersId) {
        logger.info("deleteForUser(userId:{},dealersIds:{})", userIds, dealersId);
        return dealersService.deleteForUser(userIds, dealersId);
    }

    @RequestMapping("/vPhone")
    @ResponseBody
    public Object verifyPhone(String phone, String id) {
        logger.info("verifyPhone,phone:{},id:{}",phone,id);
        return dealersService.verifyPhone(phone, id);
    }
}
