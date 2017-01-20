package com.daoliuhe.sell.weChat;

import com.daoliuhe.sell.util.WeChatConstants;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.PropertyResourceBundle;

/**
 * 微店访问证书
 */
@Component
public class WeiDianTokenHandler {
	//用户key
	private static String appkey = null;
	//用户凭证
	private static String secret = null;
	
	//静态代码块，加载配置文件
	static{
		PropertyResourceBundle prb = (PropertyResourceBundle) PropertyResourceBundle.getBundle("config");
		appkey = prb.getString("appkey");
		secret = prb.getString("secret");
	}
	
	/***
	 * 从微信服务器获取accessToken,并且添加到缓存中
	 */
	@PostConstruct
	public void initToken(){
		CacheManager manager = CacheManager.create();
		Cache cache = manager.getCache("weiDianTokenCache");
		String grant_type = "client_credential";
		//从微店服务器获取token
		//String accessToken = HttpKit.getWeiDianAccessToken(grant_type, appkey, secret);
		//Element tokenElement = new  Element(WeChatConstants.weiDianAccessToken, accessToken);
		//cache.put(tokenElement);
	}
	
	/***
	 * 从缓存中获取accessToken
	 * @return
	 */
	public String getToke(){
		CacheManager manager = CacheManager.create();
        Cache cache = manager.getCache("weiDianTokenCache");
        Element element = cache.get(WeChatConstants.weiDianAccessToken);
        String accessToken = element.getObjectValue().toString();
        return accessToken;
	}
}
