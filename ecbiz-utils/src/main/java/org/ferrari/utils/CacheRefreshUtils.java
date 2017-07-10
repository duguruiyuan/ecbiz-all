package org.ferrari.utils;

import org.ferrari.web.cache.CacheEntry;
import org.ferrari.web.cache.CacheManager;
/**
 * 刷新缓存
 * @author kevin
 * @date 2010-02-03
 */
public class CacheRefreshUtils {
	public static void refresh(String cacheName){
		 CacheEntry entry = CacheManager.lookup(cacheName);
		 if(entry != null) {
			 entry.refresh();
		 }
	}
}
