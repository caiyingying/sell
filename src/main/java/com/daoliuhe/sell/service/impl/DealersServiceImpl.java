package com.daoliuhe.sell.service.impl;

import com.daoliuhe.sell.mapper.DealersMapper;
import com.daoliuhe.sell.mapper.DealersUserMapper;
import com.daoliuhe.sell.model.Dealers;
import com.daoliuhe.sell.model.DealersUser;
import com.daoliuhe.sell.model.UserRole;
import com.daoliuhe.sell.service.DealersService;
import com.daoliuhe.sell.util.BCrypt;
import com.daoliuhe.sell.util.WeChatConstants;
import com.daoliuhe.sell.weChat.HttpKit;
import com.daoliuhe.sell.weChat.TokenHandler;
import com.daoliuhe.sell.weChat.bean.Action;
import com.daoliuhe.sell.weChat.bean.ActionInfo;
import com.daoliuhe.sell.weChat.bean.ActionReturn;
import com.daoliuhe.sell.weChat.bean.Scene;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * Created by CYY on 2016/12/25.
 */
@Service
public class DealersServiceImpl implements DealersService {

    private static final Logger logger = LoggerFactory.getLogger(DealersServiceImpl.class);

    @Autowired
    DealersMapper dealersMapper;

    @Autowired
    DealersUserMapper dealersUserMapper;

    @Autowired
    TokenHandler tokenHandler;

    @Override
    public Map<String, Object> getPageData(Dealers dealers) {
        logger.info("getPageData,dealers:{}", dealers);
        Map<String, Object> map = new HashMap<String, Object>();
        int total = dealersMapper.getPageCount(dealers);
        map.put("total", total);
        int curPage = dealers.getPage();
        int rows = dealers.getRows();
        int maxPage = (total + rows - 1) / rows;
        if (curPage > maxPage && maxPage > 0) {
            dealers.setPage(maxPage);
        }
        map.put("rows", dealersMapper.getPageData(dealers));
        return map;
    }

    @Override
    public Map<String, Object> getUserPageData(DealersUser dealersUser) {
        logger.info("getUserPageData, dealersUser:{}", dealersUser);
        Map<String, Object> map = new HashMap<String, Object>();
        int total = dealersUserMapper.getUserPageCount(dealersUser);
        map.put("total", total);
        int curPage = dealersUser.getPage();
        int rows = dealersUser.getRows();
        int maxPage = (total + rows - 1) / rows;
        if (curPage > maxPage && maxPage > 0) {
            dealersUser.setPage(maxPage);
        }
        map.put("rows", dealersUserMapper.getUserPageData(dealersUser));
        return map;
    }

    @Override
    public void saveOrUpdate(Dealers dealers) {
        logger.info("saveOrUpdate,dealers:{}", dealers);
        if (!StringUtils.isEmpty(dealers.getId())) {
            dealersMapper.updateByPrimaryKey(dealers);
        } else {
            int curCode = dealersMapper.getMaxCode();
            Action action = new Action();
            Scene scene = new Scene();
            scene.setScene_id(curCode + 1);
            ActionInfo actionInfo = new ActionInfo();
            actionInfo.setScene(scene);
            action.setAction_info(actionInfo);
            action.setAction_name(WeChatConstants.QR_LIMIT_SCENE);
            ActionReturn actionReturn = HttpKit.getQRCode(tokenHandler.getToke(),action);
            dealers.setCode(curCode + 1);
            dealers.setCodeUrl(actionReturn.getUrl());
            dealers.setTicket(actionReturn.getTicket());
            //获取二维码
            dealersMapper.insert(dealers);
        }
    }

    @Override
    public Dealers getDealersById(Integer id) {
        return dealersMapper.selectByPrimaryKey(id);
    }

    @Override
    public Map<String, Object> saveBatchUser(String dealersId, String userIds) {
        logger.info("saveBatchUser(dealersId:{},userIds:{})", dealersId, userIds);
        Map<String, Object> json = new HashMap<String, Object>();
        boolean success = false;
        String reason = "";
        if (StringUtils.isEmpty(dealersId) || StringUtils.isEmpty(userIds)) {
            reason = "参数错误!";
        } else {
            List<String> userList = new ArrayList<String>(Arrays.asList(userIds.split(";")));
            for (String userId : userList) {
                DealersUser relation = new DealersUser();
                relation.setUserId(Integer.parseInt(userId));
                relation.setDealersId(Integer.parseInt(dealersId));
                if (dealersUserMapper.select(relation).isEmpty()) {
                    dealersUserMapper.insert(relation);
                }
            }
            success = true;
        }
        json.put("success", success);
        json.put("reason", reason);
        return json;
    }

    @Override
    public Object deleteForUser(String userIds, String dealersId) {
        logger.info("deleteForUser,userIds:{},dealersId:{}", userIds, dealersId);
        Map json = new HashMap();
        boolean success = false;
        String reason = "";
        if (StringUtils.isEmpty(userIds) || StringUtils.isEmpty(dealersId)) {
            reason = "参数错误!";
        } else {
            List<String> list = Arrays.asList(userIds.split(","));
            dealersUserMapper.deleteRelationForUser(dealersId, list);
            success = true;
        }

        json.put("success", success);
        json.put("reason", reason);
        return json;
    }

    @Override
    public Map<String, Object> saveRelationUser(DealersUser dealersUser) {
        logger.info("saveRelationUser,relation:{}", dealersUser);
        Map json = new HashMap();
        boolean success = false;
        String reason = "";
        // 用户id和角色id联合查询
        if (StringUtils.isEmpty(dealersUser.getUserId()) || StringUtils.isEmpty(dealersUser.getDealersId())) {
            reason = "用户或分销商不能为空!";
        } else if (dealersUserMapper.select(dealersUser).isEmpty()) {
            int result = dealersUserMapper.insert(dealersUser);
            if (result == 1) {
                success = true;
            } else {
                reason = "保存失败!";
            }
        } else {
            reason = "已替换!";
        }
        json.put("success", success);
        json.put("reason", reason);
        return json;
    }
}
