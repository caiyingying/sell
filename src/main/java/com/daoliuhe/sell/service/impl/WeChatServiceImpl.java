package com.daoliuhe.sell.service.impl;

import com.daoliuhe.sell.service.WeChatService;
import com.daoliuhe.sell.weChat.HttpKit;
import com.daoliuhe.sell.weChat.TokenHandler;
import com.daoliuhe.sell.weChat.bean.Action;
import com.daoliuhe.sell.weChat.bean.ActionReturn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by CYY on 2017/1/15.
 */
@Service
public class WeChatServiceImpl implements WeChatService{
    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(WeChatServiceImpl.class);
    /**
     * access_token
     */
    @Autowired
    private TokenHandler tokenHandler;

    @Override
    public ActionReturn createQRLimitScene() {
        Action action = new Action();
        //TODO
        return HttpKit.getQRCode(tokenHandler.getToke(),action);
    }
}
