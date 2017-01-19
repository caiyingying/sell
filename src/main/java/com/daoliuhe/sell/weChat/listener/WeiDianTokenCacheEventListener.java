package com.daoliuhe.sell.weChat.listener;

import com.daoliuhe.sell.weChat.WeiDianTokenHandler;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import net.sf.ehcache.event.CacheEventListener;

/**
 * 微店的access_token
 */
public class WeiDianTokenCacheEventListener implements CacheEventListener {

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void notifyElementEvicted(Ehcache arg0, Element arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void notifyElementExpired(Ehcache arg0, Element arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void notifyElementPut(Ehcache arg0, Element arg1)
			throws CacheException {
		//提示过期，重新获取token
		WeiDianTokenHandler tokenHandler = new WeiDianTokenHandler();
		tokenHandler.initToken();

	}

	@Override
	public void notifyElementRemoved(Ehcache arg0, Element arg1)
			throws CacheException {
		// TODO Auto-generated method stub

	}

	@Override
	public void notifyElementUpdated(Ehcache arg0, Element arg1)
			throws CacheException {
		// TODO Auto-generated method stub

	}

	@Override
	public void notifyRemoveAll(Ehcache arg0) {
		// TODO Auto-generated method stub

	}

	public Object clone() throws CloneNotSupportedException {
		return null; // To change body of implemented methods use File |
						// Settings | File Templates.
	}

}
