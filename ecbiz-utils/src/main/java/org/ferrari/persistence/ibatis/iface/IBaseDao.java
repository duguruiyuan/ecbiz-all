 /**     
   * @create.date: Jul 22, 2010 10:06:28 AM
   * @author: yuliang 
   * @company: 车马驿站
   * @see:org.ferrari.persistence.ibatis.iface 
   */ 
package org.ferrari.persistence.ibatis.iface;

import java.io.Serializable;
import java.util.List;

import org.ferrari.exception.DaoException;




 /**  
 * @create.date: Jul 22, 2010 10:06:28 AM     
 * @comment: <p>TODO</p>
 * @see: org.ferrari.persistence.ibatis.iface
 * @author: yuliang
 * @modify.by: yuliang
 * @modify.date: Jul 22, 2010 10:06:28 AM
 */
public interface IBaseDao<T, ID extends Serializable> {


	/**
	 * 
	 * @comment: <p>please description this method to do</p>  
	 * @create.date: Jul 22, 2010 ( 10:09:56 AM )
	 * @author: yuliang
	 * @return:
	 */
	public List<?> query(T entity, int min, int max) throws DaoException;
	
	/**
	 * 
	 * @comment: <p>please description this method to do</p>  
	 * @create.date: Jul 22, 2010 ( 10:22:58 AM )
	 * @author: yuliang
	 * @return:
	 */
	public List<?> query(T entity) throws DaoException;
	
	/**
	 * 
	 * @comment: <p>please description this method to do</p>  
	 * @create.date: Jul 22, 2010 ( 10:11:32 AM )
	 * @author: yuliang
	 * @return:
	 */
	public void delete(ID id) throws DaoException;
	
	/**
	 * 
	 * @comment: <p>please description this method to do</p>  
	 * @create.date: Jul 22, 2010 ( 10:12:02 AM )
	 * @author: yuliang
	 * @return:
	 */
	public void batchDelete(String ids) throws DaoException;
	
	/**
	 * 
	 * @comment: <p>please description this method to do</p>  
	 * @create.date: Jul 22, 2010 ( 10:12:35 AM )
	 * @author: yuliang
	 * @return:
	 */
	public void update(T entity) throws DaoException;
	
	/**
	 * 
	 * @comment: <p>please description this method to do</p>  
	 * @create.date: Jul 22, 2010 ( 10:13:06 AM )
	 * @author: yuliang
	 * @return:
	 */
	public void save(T entity) throws DaoException;
	
	/**
	 * 
	 * @comment: <p>please description this method to do</p>  
	 * @create.date: Jul 22, 2010 ( 10:14:00 AM )
	 * @author: yuliang
	 * @return:
	 */
	public T getBean(ID id) throws DaoException;
	
	/**
	 * 
	 * @comment: <p>please description this method to do</p>  
	 * @create.date: Jul 22, 2010 ( 10:14:35 AM )
	 * @author: yuliang
	 * @return:
	 */
	public Integer count(T entity) throws DaoException;
}
