package org.ferrari.web.cache.memcache;

public class CacheFactory {
	public static ICache getCache(){
		return CacheManager.getInstance();
	}
}
