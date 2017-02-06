package com.daoliuhe.sell.weChat;

import java.util.PropertyResourceBundle;

import com.daoliuhe.sell.util.WeChatConstants;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 访问证书
 */
@Component
public class TokenHandler {
	//用户id
	private static String appid = null;
	//用户凭证
	private static String secret = null;

	private CacheManager manager;
	
	//静态代码块，加载配置文件
	static{
		PropertyResourceBundle prb = (PropertyResourceBundle) PropertyResourceBundle.getBundle("config");
		appid = prb.getString("appID");
		secret = prb.getString("appsecret");
	}
	
	/***
	 * 从微信服务器获取accessToken,并且添加到缓存中
	 */
	@PostConstruct
	public void initToken(){
		manager = CacheManager.create();
		Cache cache = manager.getCache("tokenCache");
		String grant_type = "client_credential";
		//从微信服务器获取token
		String accessToken = HttpKit.getAccessToken(grant_type, appid, secret);
		
		Element tokenElement = new  Element(WeChatConstants.accessToken,accessToken);
		cache.put(tokenElement);
	}
	
	/***
	 * 从缓存中获取accessToken
	 * @return
	 */
	public String getToke(){
		//CacheManager manager = CacheManager.create();
        Cache cache = manager.getCache("tokenCache");
        Element element = cache.get(WeChatConstants.accessToken);
        String accessToken = element.getObjectValue().toString();
        return accessToken;
	}
}
