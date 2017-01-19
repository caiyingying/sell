package com.daoliuhe.sell.service.impl;

import com.daoliuhe.sell.service.WeiDianService;
import com.daoliuhe.sell.weChat.WeiDianTokenHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by CYY on 2017/1/19.
 */
@Service
public class WeiDianServiceImpl implements WeiDianService {
    /**
     * access_token
     */
    @Autowired
    private WeiDianTokenHandler tokenHandler;

    @Override
    public void dispose(String xmlMsg) {

    }
}
