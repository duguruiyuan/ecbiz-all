package org.ferrari.web.cache;

import org.ferrari.exception.ServiceException;
import org.ferrari.exception.UtilsException;

 /**
   * 
   * @create.date: 2009-5-11 上午09:02:21     
   * @comment: <p>cache interface to implements cache manager</p>
   * @see: org.ferrari.web.cache
   * @author: kevin
   * @verson: 1.0
   */
public interface CacheEntry {
	/**
	 * 
	 * @comment: <p>refresh cache when cache data be update by user</p>  
	 * @create.date: 2009-5-11 ( 上午09:03:05 )
	 * @author: kevin
	 * @throws ServiceException
	 * @see: <h1>org.ferrari.web.cache.CacheEntry.refresh</h1>
	 * @modified.by: <if there modified by others,then write author name>
	 * @modified.date: <modified date time>
	 */
	public void refresh() throws UtilsException;

}
