package org.ferrari.persistence.jdbc.iface;

import java.util.List;
/**
 * 
   * @create.date: 2009-5-7 上午09:39:31     
   * @comment: <p>TODO</p>
   * @see: org.ferrari.persistence.jdbc.iface
   * @author: yuxinrong
   * @modify.by: yuxinrong
   * @modify.date: 2009-5-7 上午09:39:31
 */
public interface JdbcDao {
	/**
	 * 
	 * @comment: <p>please description this method to do</p>  
	 * @create.date: 2009-5-7 ( 上午09:39:36 )
	 * @author: yuxinrong
	 * @param id
	 * @return
	 * @see: <h1>org.ferrari.persistence.jdbc.iface.JdbcDao.getEntity</h1>
	 * @modified.by: <if there modified by others,then write author name>
	 * @modified.date: <modified date time>
	 */
	public abstract  Object getEntity(Integer id);
	/**
	 * 
	 * @comment: <p>please description this method to do</p>  
	 * @create.date: 2009-5-7 ( 上午09:39:43 )
	 * @author: yuxinrong
	 * @param bean
	 * @see: <h1>org.ferrari.persistence.jdbc.iface.JdbcDao.update</h1>
	 * @modified.by: <if there modified by others,then write author name>
	 * @modified.date: <modified date time>
	 */
	public abstract void update(Object bean);
	/**
	 * 
	 * @comment: <p>please description this method to do</p>  
	 * @create.date: 2009-5-7 ( 上午09:39:53 )
	 * @author: yuxinrong
	 * @param bean
	 * @see: <h1>org.ferrari.persistence.jdbc.iface.JdbcDao.save</h1>
	 * @modified.by: <if there modified by others,then write author name>
	 * @modified.date: <modified date time>
	 */
	public abstract void save(Object bean);
	/**
	 * 
	 * @comment: <p>please description this method to do</p>  
	 * @create.date: 2009-5-7 ( 上午09:39:59 )
	 * @author: yuxinrong
	 * @param id
	 * @see: <h1>org.ferrari.persistence.jdbc.iface.JdbcDao.delete</h1>
	 * @modified.by: <if there modified by others,then write author name>
	 * @modified.date: <modified date time>
	 */
	public abstract void delete(Integer id);
	/**
	 * 
	 * @comment: <p>please description this method to do</p>  
	 * @create.date: 2009-5-7 ( 上午09:40:08 )
	 * @author: yuxinrong
	 * @return
	 * @see: <h1>org.ferrari.persistence.jdbc.iface.JdbcDao.getList</h1>
	 * @modified.by: <if there modified by others,then write author name>
	 * @modified.date: <modified date time>
	 */
	public abstract List<?> getList();
	/**
	 * 
	 * @comment: <p>please description this method to do</p>  
	 * @create.date: 2009-5-7 ( 上午09:40:16 )
	 * @author: yuxinrong
	 * @param name
	 * @return
	 * @see: <h1>org.ferrari.persistence.jdbc.iface.JdbcDao.getList</h1>
	 * @modified.by: <if there modified by others,then write author name>
	 * @modified.date: <modified date time>
	 */
	public abstract List<?> getList(String name);
	/**
	 * 
	 * @comment: <p>please description this method to do</p>  
	 * @create.date: 2009-5-7 ( 上午09:40:23 )
	 * @author: yuxinrong
	 * @param bean
	 * @return
	 * @see: <h1>org.ferrari.persistence.jdbc.iface.JdbcDao.getList</h1>
	 * @modified.by: <if there modified by others,then write author name>
	 * @modified.date: <modified date time>
	 */
	public abstract List<?> getList(final Object bean);
	/**
	 * 
	 * @comment: <p>please description this method to do</p>  
	 * @create.date: 2009-5-7 ( 上午09:40:32 )
	 * @author: yuxinrong
	 * @return
	 * @see: <h1>org.ferrari.persistence.jdbc.iface.JdbcDao.getCount</h1>
	 * @modified.by: <if there modified by others,then write author name>
	 * @modified.date: <modified date time>
	 */
	public abstract int getCount();
	/**
	 * 
	 * @comment: <p>please description this method to do</p>  
	 * @create.date: May 11, 2009 ( 11:10:52 AM )
	 * @author: yuxinrong
	 * @param name
	 * @param index
	 * @param enddex
	 * @return
	 * @see: <h1>org.ferrari.persistence.jdbc.iface.JdbcDao.getList</h1>
	 * @modified.by: <if there modified by others,then write author name>
	 * @modified.date: <modified date time>
	 */
	public abstract List<?> getList(String name,Integer index,Integer enddex);
}
