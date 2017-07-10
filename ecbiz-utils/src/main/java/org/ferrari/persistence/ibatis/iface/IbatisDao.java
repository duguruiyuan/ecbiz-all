package org.ferrari.persistence.ibatis.iface;

import java.util.List;
import java.util.Map;

import org.ferrari.exception.PersistenceException;

public interface IbatisDao {


	   /**
	    * 
	      * @create: 2009-5-5 上午11:31:16
	      * @author: yuxinrong
	      * @comment: <p>TODO</p>
	      * @param statementName
	      * @return
	      * @throws PersistenceException
	      * @return: Object
	      * @exception: $
	      * @company: 车马驿站
	      * @see: <h1>org.ferrari.persistence.ibatis.BaseIbatis.java</h1>
	      * @copyright: (c) 2009 by kevin.  
	      * @version: 1.0
	      * @modified.by: 
	      * @modifyed.date:
	    */
		public Object findBean(String statementName) throws PersistenceException ;
		
		/**
		 * 
		   * @create: 2009-5-5 上午11:34:27
		   * @author: yuxinrong
		   * @comment: <p>TODO</p>
		   * @param statementName
		   * @param parameterObject
		   * @return
		   * @throws PersistenceException
		   * @return: Object
		   * @exception: $
		   * @company: 车马驿站
		   * @see: <h1>org.ferrari.persistence.ibatis.BaseIbatis.java</h1>
		   * @copyright: (c) 2009 by kevin.  
		   * @version: 1.0
		   * @modified.by: 
		   * @modifyed.date:
		 */
		public Object findBean(String statementName, Object parameterObject) throws PersistenceException ;
		

		/**
		 * 
		   * @create: 2009-5-5 上午11:34:43
		   * @author: yuxinrong
		   * @comment: <p>TODO</p>
		   * @param statementName
		   * @param parameterObject
		   * @param resultObject
		   * @return
		   * @throws PersistenceException
		   * @return: Object
		   * @exception: $
		   * @company: 车马驿站
		   * @see: <h1>org.ferrari.persistence.ibatis.BaseIbatis.java</h1>
		   * @copyright: (c) 2009 by kevin.  
		   * @version: 1.0
		   * @modified.by: 
		   * @modifyed.date:
		 */
		public Object findBean(String statementName, Object parameterObject, Object resultObject) throws PersistenceException ;

		/**
		 * 
		   * @create: 2009-5-5 上午11:35:15
		   * @author: yuxinrong
		   * @comment: <p>TODO</p>
		   * @param statementName
		   * @return
		   * @throws PersistenceException
		   * @return: List<?>
		   * @exception: $
		   * @company: 车马驿站
		   * @see: <h1>org.ferrari.persistence.ibatis.BaseIbatis.java</h1>
		   * @copyright: (c) 2009 by kevin.  
		   * @version: 1.0
		   * @modified.by: 
		   * @modifyed.date:
		 */
		public List<?> getList(String statementName) throws PersistenceException ;

		/**
		 * 
		   * @create: 2009-5-5 上午11:35:25
		   * @author: yuxinrong
		   * @comment: <p>TODO</p>
		   * @param statementName
		   * @param skipResults
		   * @param maxResults
		   * @return
		   * @throws PersistenceException
		   * @return: List<?>
		   * @exception: $
		   * @company: 车马驿站
		   * @see: <h1>org.ferrari.persistence.ibatis.BaseIbatis.java</h1>
		   * @copyright: (c) 2009 by kevin.  
		   * @version: 1.0
		   * @modified.by: 
		   * @modifyed.date:
		 */
		public List<?> getList(String statementName, int skipResults, int maxResults) throws PersistenceException ;

		/**
		 * 
		   * @create: 2009-5-5 上午11:35:45
		   * @author: yuxinrong
		   * @comment: <p>TODO</p>
		   * @param statementName
		   * @param parameterObject
		   * @return
		   * @throws PersistenceException
		   * @return: List<?>
		   * @exception: $
		   * @company: 车马驿站
		   * @see: <h1>org.ferrari.persistence.ibatis.BaseIbatis.java</h1>
		   * @copyright: (c) 2009 by kevin.  
		   * @version: 1.0
		   * @modified.by: 
		   * @modifyed.date:
		 */
		public List<?> getList(String statementName, Object parameterObject) throws PersistenceException ;

		/**
		 * 
		   * @create: 2009-5-5 上午11:36:03
		   * @author: yuxinrong
		   * @comment: <p>TODO</p>
		   * @param statementName
		   * @param parameterObject
		   * @param skipResults
		   * @param maxResults
		   * @return
		   * @throws PersistenceException
		   * @return: List<?>
		   * @exception: $
		   * @company: 车马驿站
		   * @see: <h1>org.ferrari.persistence.ibatis.BaseIbatis.java</h1>
		   * @copyright: (c) 2009 by kevin.  
		   * @version: 1.0
		   * @modified.by: 
		   * @modifyed.date:
		 */
		public List<?> getList(String statementName, Object parameterObject, int skipResults, int maxResults) throws PersistenceException ;
		

		/**
		 * 
		   * @create: 2009-5-5 上午11:36:23
		   * @author: yuxinrong
		   * @comment: <p>TODO</p>
		   * @param statementName
		   * @param parameterObject
		   * @param keyProperty
		   * @return
		   * @throws PersistenceException
		   * @return: Map<?,?>
		   * @exception: $
		   * @company: 车马驿站
		   * @see: <h1>org.ferrari.persistence.ibatis.BaseIbatis.java</h1>
		   * @copyright: (c) 2009 by kevin.  
		   * @version: 1.0
		   * @modified.by: 
		   * @modifyed.date:
		 */
		public Map<?,?> getMap(String statementName, Object parameterObject, String keyProperty) throws PersistenceException ;
		
		/**
		 * 
		   * @create: 2009-5-5 上午11:37:19
		   * @author: yuxinrong
		   * @comment: <p>TODO</p>
		   * @param statementName
		   * @param parameterObject
		   * @param keyProperty
		   * @param valueProperty
		   * @return
		   * @throws PersistenceException
		   * @return: Map<?,?>
		   * @exception: $
		   * @company: 车马驿站
		   * @see: <h1>org.ferrari.persistence.ibatis.BaseIbatis.java</h1>
		   * @copyright: (c) 2009 by kevin.  
		   * @version: 1.0
		   * @modified.by: 
		   * @modifyed.date:
		 */
		public Map<?,?> getMap(String statementName, Object parameterObject, String keyProperty, String valueProperty) throws PersistenceException ;
		/**
		 * 
		   * @create: 2009-5-5 上午11:37:27
		   * @author: yuxinrong
		   * @comment: <p>TODO</p>
		   * @param statement
		   * @return
		   * @throws PersistenceException
		   * @return: Object
		   * @exception: $
		   * @company: 车马驿站
		   * @see: <h1>org.ferrari.persistence.ibatis.BaseIbatis.java</h1>
		   * @copyright: (c) 2009 by kevin.  
		   * @version: 1.0
		   * @modified.by: 
		   * @modifyed.date:
		 */
		public Object save(String statement) throws PersistenceException;

		/**
		 * 
		   * @create: 2009-5-5 上午11:37:34
		   * @author: yuxinrong
		   * @comment: <p>TODO</p>
		   * @param statement
		   * @param param
		   * @return
		   * @throws PersistenceException
		   * @return: Object
		   * @exception: $
		   * @company: 车马驿站
		   * @see: <h1>org.ferrari.persistence.ibatis.BaseIbatis.java</h1>
		   * @copyright: (c) 2009 by kevin.  
		   * @version: 1.0
		   * @modified.by: 
		   * @modifyed.date:
		 */
		public Object save(String statement, Object param) throws PersistenceException ;
		/**
		 * 
		   * @create: 2009-5-5 上午11:39:24
		   * @author: yuxinrong
		   * @comment: <p>TODO</p>
		   * @param statement
		   * @return
		   * @throws PersistenceException
		   * @return: int
		   * @exception: $
		   * @company: 车马驿站
		   * @see: <h1>org.ferrari.persistence.ibatis.BaseIbatis.java</h1>
		   * @copyright: (c) 2009 by yuxinrong.  
		   * @version: 1.0
		   * @modified.by: 
		   * @modifyed.date:
		 */
		public int update(String statement) throws PersistenceException;
		/**
		 * 
		   * @create: 2009-5-5 上午11:38:33
		   * @author: yuxinrong
		   * @comment: <p>TODO</p>
		   * @param statement
		   * @param param
		   * @return
		   * @throws PersistenceException
		   * @return: int
		   * @exception: $
		   * @company: 车马驿站
		   * @see: <h1>org.ferrari.persistence.ibatis.BaseIbatis.java</h1>
		   * @copyright: (c) 2009 by yuxinrong.  
		   * @version: 1.0
		   * @modified.by: 
		   * @modifyed.date:
		 */
		public int update(String statement, Object param) throws PersistenceException ;
		/**
		 * 
		   * @create: 2009-5-5 上午11:38:42
		   * @author: yuxinrong
		   * @comment: <p>TODO</p>
		   * @param statementName
		   * @param parameterObject
		   * @param requiredRowsAffected
		   * @throws PersistenceException
		   * @return: void
		   * @exception: $
		   * @company: 车马驿站
		   * @see: <h1>org.ferrari.persistence.ibatis.BaseIbatis.java</h1>
		   * @copyright: (c) 2009 by yuxinrong.  
		   * @version: 1.0
		   * @modified.by: 
		   * @modifyed.date:
		 */
		public void update(String statementName, Object parameterObject, int requiredRowsAffected) throws PersistenceException ;

		/**
		 * 
		   * @create: 2009-5-5 上午11:38:51
		   * @author: yuxinrong
		   * @comment: <p>TODO</p>
		   * @param statement
		   * @return
		   * @throws PersistenceException
		   * @return: int
		   * @exception: $
		   * @company: 车马驿站
		   * @see: <h1>org.ferrari.persistence.ibatis.BaseIbatis.java</h1>
		   * @copyright: (c) 2009 by yuxinrong.  
		   * @version: 1.0
		   * @modified.by: 
		   * @modifyed.date:
		 */
		public int delete(String statement) throws PersistenceException ;

		/**
		 * 
		   * @create: 2009-5-5 上午11:38:59
		   * @author: yuxinrong
		   * @comment: <p>TODO</p>
		   * @param statement
		   * @param param
		   * @return
		   * @throws PersistenceException
		   * @return: int
		   * @exception: $
		   * @company: 车马驿站
		   * @see: <h1>org.ferrari.persistence.ibatis.BaseIbatis.java</h1>
		   * @copyright: (c) 2009 by yuxinrong.  
		   * @version: 1.0
		   * @modified.by: 
		   * @modifyed.date:
		 */
		public int delete(String statement, Object param) throws PersistenceException ;

		/**
		 * 
		   * @create: 2009-5-5 上午11:39:09
		   * @author: yuxinrong
		   * @comment: <p>TODO</p>
		   * @param statementName
		   * @param parameterObject
		   * @param requiredRowsAffected
		   * @throws PersistenceException
		   * @return: void
		   * @exception: $
		   * @company: 车马驿站
		   * @see: <h1>org.ferrari.persistence.ibatis.BaseIbatis.java</h1>
		   * @copyright: (c) 2009 by yuxinrong.  
		   * @version: 1.0
		   * @modified.by: 
		   * @modifyed.date:
		 */
		public void delete(String statementName, Object parameterObject, int requiredRowsAffected) throws PersistenceException ;
}
