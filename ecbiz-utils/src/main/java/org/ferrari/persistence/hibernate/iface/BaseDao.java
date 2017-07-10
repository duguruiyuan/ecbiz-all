package org.ferrari.persistence.hibernate.iface;


import java.util.LinkedHashMap;
import java.util.List;

import org.ferrari.persistence.hibernate.util.Condition;
import org.ferrari.persistence.hibernate.util.QueryResult;

/**
 * 
   * @create.date: May 13, 2009 10:05:22 AM     
   * @comment: <p>TODO</p>
   * @see: org.ferrari.persistence.hibernate.iface
   * @author: yuxinrong
   * @modify.by: yuxinrong
   * @modify.date: May 13, 2009 10:05:22 AM
 */
public interface BaseDao {
	
	/**
	 * 
	 * @comment: <p>please description this method to do</p>  
	 * @create.date: May 13, 2009 ( 10:05:30 AM )
	 * @author: yuxinrong
	 * @param entity
	 * @see: <h1>org.ferrari.persistence.hibernate.iface.BaseDao.save</h1>
	 * @modified.by: <if there modified by others,then write author name>
	 * @modified.date: <modified date time>
	 */
	public void save(Object entity);
	/**
	 * 
	 * @comment: <p>please description this method to do</p>  
	 * @create.date: May 13, 2009 ( 10:08:09 AM )
	 * @author: yuxinrong
	 * @param hql
	 * @return
	 * @see: <h1>org.ferrari.persistence.hibernate.iface.BaseDao.delete</h1>
	 * @modified.by: <if there modified by others,then write author name>
	 * @modified.date: <modified date time>
	 */
	public int delete(String hql);
	/**
	 * 
	 * @comment: <p>please description this method to do</p>  
	 * @create.date: May 13, 2009 ( 10:11:01 AM )
	 * @author: yuxinrong
	 * @param <T>
	 * @param entity
	 * @see: <h1>org.ferrari.persistence.hibernate.iface.BaseDao.delete</h1>
	 * @modified.by: <if there modified by others,then write author name>
	 * @modified.date: <modified date time>
	 */
	public <T> void delete(Object entity);
	/**
	 * 
	 * @comment: <p>please description this method to do</p>  
	 * @create.date: May 13, 2009 ( 10:11:45 AM )
	 * @author: yuxinrong
	 * @param <T>
	 * @param entityClass
	 * @param entityId
	 * @see: <h1>org.ferrari.persistence.hibernate.iface.BaseDao.delete</h1>
	 * @modified.by: <if there modified by others,then write author name>
	 * @modified.date: <modified date time>
	 */
	public  <T> void delete(Class<T> entityClass, Object entityId);
	/**
	 * 
	 * @comment: <p>please description this method to do</p>  
	 * @create.date: May 13, 2009 ( 10:12:56 AM )
	 * @author: yuxinrong
	 * @param <T>
	 * @param entityClass
	 * @param entityIds
	 * @see: <h1>org.ferrari.persistence.hibernate.iface.BaseDao.delete</h1>
	 * @modified.by: <if there modified by others,then write author name>
	 * @modified.date: <modified date time>
	 */
	public <T> int delete(Class<T> entityClass, Object[] entityIds);
	/**
	 * 
	 * @comment: <p>please description this method to do</p>  
	 * @create.date: May 13, 2009 ( 10:16:52 AM )
	 * @author: yuxinrong
	 * @param entity
	 * @see: <h1>org.ferrari.persistence.hibernate.iface.BaseDao.update</h1>
	 * @modified.by: <if there modified by others,then write author name>
	 * @modified.date: <modified date time>
	 */
	public void update(Object entity);
	/**
	 * 
	 * @comment: <p>please description this method to do</p>  
	 * @create.date: May 13, 2009 ( 10:17:31 AM )
	 * @author: yuxinrong
	 * @param <T>
	 * @param entity
	 * @see: <h1>org.ferrari.persistence.hibernate.iface.BaseDao.saveOrUpdate</h1>
	 * @modified.by: <if there modified by others,then write author name>
	 * @modified.date: <modified date time>
	 */
	public<T> void saveOrUpdate(T entity);
	/**
	 * 
	 * @comment: <p>please description this method to do</p>  
	 * @create.date: May 13, 2009 ( 10:19:38 AM )
	 * @author: yuxinrong
	 * @param hql
	 * @see: <h1>org.ferrari.persistence.hibernate.iface.BaseDao.update</h1>
	 * @modified.by: <if there modified by others,then write author name>
	 * @modified.date: <modified date time>
	 */
	public int update(String hql);
	/**
	 * 
	 * @comment: <p>please description this method to do</p>  
	 * @create.date: May 13, 2009 ( 10:18:24 AM )
	 * @author: yuxinrong
	 * @param <T>
	 * @param entityClass
	 * @param entityId
	 * @return
	 * @see: <h1>org.ferrari.persistence.hibernate.iface.BaseDao.getEntity</h1>
	 * @modified.by: <if there modified by others,then write author name>
	 * @modified.date: <modified date time>
	 */
	public <T> T getEntity(Class<T> entityClass, Object entityId);
	/**
	 * 
	 * @comment: <p>please description this method to do</p>  
	 * @create.date: May 13, 2009 ( 10:46:14 AM )
	 * @author: yuxinrong
	 * @param <T>
	 * @param hql
	 * @return
	 * @see: <h1>org.ferrari.persistence.hibernate.iface.BaseDao.getEntity</h1>
	 * @modified.by: <if there modified by others,then write author name>
	 * @modified.date: <modified date time>
	 */
	public <T> T getEntity(String hql);
	/**
	 * 
	 * @comment: <p>please description this method to do</p>  
	 * @create.date: May 13, 2009 ( 10:42:23 AM )
	 * @author: yuxinrong
	 * @param <T>
	 * @param classname
	 * @param fieldnames
	 * @param values
	 * @return
	 * @see: <h1>org.ferrari.persistence.hibernate.iface.BaseDao.validate</h1>
	 * @modified.by: <if there modified by others,then write author name>
	 * @modified.date: <modified date time>
	 */
	public<T> boolean validate(Class<T> classname,String [] fieldnames,Object [] values);
	/**
	 * 
	 * @comment: <p>please description this method to do</p>  
	 * @create.date: May 13, 2009 ( 10:48:04 AM )
	 * @author: yuxinrong
	 * @param <T>
	 * @param hql
	 * @param startInt
	 * @param maxInt
	 * @return
	 * @see: <h1>org.ferrari.persistence.hibernate.iface.BaseDao.getList</h1>
	 * @modified.by: <if there modified by others,then write author name>
	 * @modified.date: <modified date time>
	 */
	public<T> List<T> getList(String hql,Integer startInt,Integer maxInt);
	/**
	 * 
	 * @comment: <p>please description this method to do</p>  
	 * @create.date: May 13, 2009 ( 10:48:21 AM )
	 * @author: yuxinrong
	 * @param <T>
	 * @param hql
	 * @param values
	 * @param startInt
	 * @param maxInt
	 * @return
	 * @see: <h1>org.ferrari.persistence.hibernate.iface.BaseDao.getList</h1>
	 * @modified.by: <if there modified by others,then write author name>
	 * @modified.date: <modified date time>
	 */
	public<T> List<T> getList(String hql,Object [] values,Integer startInt,Integer maxInt);
	/**
	 * 
	 * @comment: <p>please description this method to do</p>  
	 * @create.date: May 13, 2009 ( 10:52:39 AM )
	 * @author: yuxinrong
	 * @param hql
	 * @return
	 * @see: <h1>org.ferrari.persistence.hibernate.iface.BaseDao.getTotalSize</h1>
	 * @modified.by: <if there modified by others,then write author name>
	 * @modified.date: <modified date time>
	 */
	public long getTotalSize(String hql);
	/**
	 * 
	 * @comment: <p>please description this method to do</p>  
	 * @create.date: May 13, 2009 ( 10:52:46 AM )
	 * @author: yuxinrong
	 * @param hql
	 * @param values
	 * @return
	 * @see: <h1>org.ferrari.persistence.hibernate.iface.BaseDao.getTotalSize</h1>
	 * @modified.by: <if there modified by others,then write author name>
	 * @modified.date: <modified date time>
	 */
	public long getTotalSize(String hql,Object[] values);
	
	/**
	 * 
	 * @comment: <p>please description this method to do</p>  
	 * @create.date: May 13, 2009 ( 10:54:03 AM )
	 * @author: yuxinrong
	 * @param <T>
	 * @param entityClass
	 * @param firstIndex
	 * @param maxResult
	 * @return
	 * @see: <h1>org.ferrari.persistence.hibernate.iface.BaseDao.getData</h1>
	 * @modified.by: <if there modified by others,then write author name>
	 * @modified.date: <modified date time>
	 */
	public <T> QueryResult<T> getData(Class<T> entityClass,
			int firstIndex, int maxResult);
	/**
	 * 
	 * @comment: <p>please description this method to do</p>  
	 * @create.date: May 13, 2009 ( 10:54:23 AM )
	 * @author: yuxinrong
	 * @param <T>
	 * @param entityClass
	 * @param firstIndex
	 * @param maxResult
	 * @param conditions
	 * @param orderby
	 * @return
	 * @see: <h1>org.ferrari.persistence.hibernate.iface.BaseDao.getData</h1>
	 * @modified.by: <if there modified by others,then write author name>
	 * @modified.date: <modified date time>
	 */
	public <T> QueryResult<T> getData(Class<T> entityClass,
			int firstIndex, int maxResult, Condition[] conditions,
			LinkedHashMap<String, String> orderby);
	/**
	 * 
	 * @comment: <p>please description this method to do</p>  
	 * @create.date: May 13, 2009 ( 10:55:12 AM )
	 * @author: yuxinrong
	 * @param <T>
	 * @param entityClass
	 * @param firstIndex
	 * @param maxResult
	 * @param orderby
	 * @return
	 * @see: <h1>org.ferrari.persistence.hibernate.iface.BaseDao.getData</h1>
	 * @modified.by: <if there modified by others,then write author name>
	 * @modified.date: <modified date time>
	 */
	public <T> QueryResult<T> getData(Class<T> entityClass,
			int firstIndex, int maxResult, LinkedHashMap<String, String> orderby);
	/**
	 * 
	 * @comment: <p>将持久态变成游离态</p>  
	 * @create.date: Jul 23, 2009 ( 2:55:33 PM )
	 * @author: shellrong
	 * @param entity
	 * @see: <h1>org.ferrari.persistence.hibernate.iface.BaseDao.evcit</h1>
	 * @modified.by: <if there modified by others,then write author name>
	 * @modified.date: <modified date time>
	 */
	public void evcit(Object entity);
	/**
	 * 
	 * @comment: <p>清空一级缓存，即sessfactory</p>  
	 * @create.date: Jul 23, 2009 ( 2:55:38 PM )
	 * @author: shellrong
	 * @see: <h1>org.ferrari.persistence.hibernate.iface.BaseDao.clear</h1>
	 * @modified.by: <if there modified by others,then write author name>
	 * @modified.date: <modified date time>
	 */
	public void clear() ;
	/**
	 * 
	 * @comment: <p>刷新一级缓存，即sessfactory</p>  
	 * @create.date: Jul 23, 2009 ( 2:55:43 PM )
	 * @author: shellrong
	 * @see: <h1>org.ferrari.persistence.hibernate.iface.BaseDao.flush</h1>
	 * @modified.by: <if there modified by others,then write author name>
	 * @modified.date: <modified date time>
	 */
	public void flush();
	
}
