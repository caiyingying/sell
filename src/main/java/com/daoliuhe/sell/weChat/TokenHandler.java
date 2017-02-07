package com.daoliuhe.sell.weChat;

import com.daoliuhe.sell.util.Config;
import com.daoliuhe.sell.util.WeChatConstants;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;

/**
 * 访问证书
 */
@Component
public class TokenHandler {

    CacheManager manager = CacheManager.create();

    /***
     * 从微信服务器获取accessToken,并且添加到缓存中
     */
    @PostConstruct
    public void initToken() {
        Cache cache = manager.getCache("tokenCache");
        String grant_type = "client_credential";
        //从微信服务器获取token
        String accessToken = HttpKit.getAccessToken(grant_type, Config.APP_ID, Config.APP_SECRET);
        Element tokenElement = new Element(WeChatConstants.accessToken, accessToken);
        cache.put(tokenElement);
    }

    /***
     * 从缓存中获取accessToken
     * @return
     */
    public String getToke() {
        //CacheManager manager = CacheManager.create();
        String accessToken = "";
        Cache cache = manager.getCache("tokenCache");
        Element element = cache.get(WeChatConstants.accessToken);
        //缓存的不存在
        if (null == element || StringUtils.isEmpty(element.getObjectValue())) {
            String grant_type = "client_credential";
            accessToken = HttpKit.getAccessToken(grant_type,  Config.APP_ID, Config.APP_SECRET);
            Element tokenElement = new Element(WeChatConstants.accessToken, accessToken);
            cache.put(tokenElement);
        } else {
            accessToken = element.getObjectValue().toString();
        }
        return accessToken;
    }
}
