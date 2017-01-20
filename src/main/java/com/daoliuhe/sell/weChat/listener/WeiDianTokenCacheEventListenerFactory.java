package com.daoliuhe.sell.weChat.listener;

import net.sf.ehcache.event.CacheEventListener;
import net.sf.ehcache.event.CacheEventListenerFactory;

import java.util.Properties;

public class WeiDianTokenCacheEventListenerFactory extends CacheEventListenerFactory {

    @Override
    public CacheEventListener createCacheEventListener(Properties arg0) {
        return new WeiDianTokenCacheEventListener();
    }

}
