package com.daoliuhe.sell.service;

import com.daoliuhe.sell.weChat.bean.ActionReturn;

/**
 * 微店的处理逻辑
 * Created by CYY on 2016/12/25.
 */
public interface WeiDianService {
    /**
     * 事件消息分发
     * @param xmlMsg
     */
    void dispose(String xmlMsg);

}
