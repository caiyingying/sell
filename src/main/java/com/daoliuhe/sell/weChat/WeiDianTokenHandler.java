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
 * 微店访问证书
 */
@Component
public class WeiDianTokenHandler {

    CacheManager manager = CacheManager.create();

    /***
     * 从微信服务器获取accessToken,并且添加到缓存中
     */
    @PostConstruct
    public void initToken() {
        Cache cache = manager.getCache("weiDianTokenCache");
        String grant_type = "client_credential";
        //从微店服务器获取token
        String accessToken = HttpKit.getWeiDianAccessToken(grant_type, Config.APPKEY, Config.SECRET);
        Element tokenElement = new Element(WeChatConstants.weiDianAccessToken, accessToken);
        cache.put(tokenElement);
    }

    /***
     * 从缓存中获取accessToken
     * @return
     */
    public String getToken() {
        String accessToken = "";
        Cache cache = manager.getCache("weiDianTokenCache");
        Element element = cache.get(WeChatConstants.weiDianAccessToken);
        //缓存的不存在
        if (null == element || StringUtils.isEmpty(element.getObjectValue())) {
            String grant_type = "client_credential";
            //从微店服务器获取token
            accessToken = HttpKit.getWeiDianAccessToken(grant_type, Config.APPKEY, Config.SECRET);
            Element tokenElement = new Element(WeChatConstants.weiDianAccessToken, accessToken);
            cache.put(tokenElement);
        } else {
            accessToken = element.getObjectValue().toString();
        }

        return accessToken;
    }
}
