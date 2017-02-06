package com.daoliuhe.sell.weChat.listener;

import com.daoliuhe.sell.weChat.WeiDianTokenHandler;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import net.sf.ehcache.event.CacheEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 微店的access_token
 */
public class WeiDianTokenCacheEventListener implements CacheEventListener {

	private static final Logger logger = LoggerFactory.getLogger(WeiDianTokenCacheEventListener.class);

	@Override
	public void dispose() {
		logger.info("dispose");

	}

	@Override
	public void notifyElementEvicted(Ehcache arg0, Element arg1) {
		logger.info("notifyElementEvicted");

	}

	@Override
	public void notifyElementExpired(Ehcache arg0, Element arg1) {
		logger.info("notifyElementExpired");
		//提示过期，重新获取token
		WeiDianTokenHandler tokenHandler = new WeiDianTokenHandler();
		tokenHandler.initToken();
	}

	@Override
	public void notifyElementPut(Ehcache arg0, Element arg1)
			throws CacheException {
		logger.info("notifyElementPut");
		//提示过期，重新获取token
		WeiDianTokenHandler tokenHandler = new WeiDianTokenHandler();
		tokenHandler.initToken();

	}

	@Override
	public void notifyElementRemoved(Ehcache arg0, Element arg1)
			throws CacheException {
		logger.info("notifyElementRemoved");
		WeiDianTokenHandler tokenHandler = new WeiDianTokenHandler();
		tokenHandler.initToken();

	}

	@Override
	public void notifyElementUpdated(Ehcache arg0, Element arg1)
			throws CacheException {
		logger.info("notifyElementUpdated");

	}

	@Override
	public void notifyRemoveAll(Ehcache arg0) {
		logger.info("notifyRemoveAll");

	}

	public Object clone() throws CloneNotSupportedException {
		return null; // To change body of implemented methods use File |
						// Settings | File Templates.
	}

}
