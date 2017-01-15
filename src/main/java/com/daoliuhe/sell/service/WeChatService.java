package com.daoliuhe.sell.service;

import com.daoliuhe.sell.model.User;
import com.daoliuhe.sell.weChat.bean.Action;
import com.daoliuhe.sell.weChat.bean.ActionInfo;
import com.daoliuhe.sell.weChat.bean.ActionReturn;

import java.util.Map;
import java.util.Set;

/**
 * Created by CYY on 2016/12/25.
 */
public interface WeChatService {

    /**
     * 永久二维码
     * @return
     */
    ActionReturn createQRLimitScene();
}
