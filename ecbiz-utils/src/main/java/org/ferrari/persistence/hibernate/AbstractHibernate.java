 /**     
   * @Create: 2008-11-27 上午11:59:04     
   * @Description: 这是个抽象类，以后扩展用
   * @Company: 车马驿站
   * @author: 喻新容 
   * @Copyright: (c) 2008 by 喻新容.  
   * @version: 1.0   
   */ 
package org.ferrari.persistence.hibernate;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;

import org.ferrari.persistence.hibernate.iface.BaseDao;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


/**  
 * @Create: 2008-11-27 上午11:59:04     
 * @Description: 备用类
 * @see: com.jmyz.dao
 * @modify by: 喻新容 
 * @time: 2008-11-27 上午11:59:04
 */
public abstract class AbstractHibernate extends HibernateDaoSupport
		implements BaseDao {
	
	
	public void initialize(Object object, String methodName) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {

		String[] methodArray = methodName.split("\\.");
		Method method = null;
		Object initializeObject = object;

		if(methodArray.length == 1){
		this.getHibernateTemplate().lock(initializeObject, org.hibernate.LockMode.NONE);
		method = object.getClass().getMethod(methodArray[0], new Class[] {});
		initializeObject = method.invoke(initializeObject, new Object[] {});
		this.getHibernateTemplate().initialize(initializeObject);
		}else{
		for(int i=0;i<methodArray.length;i++){
		method = initializeObject.getClass().getMethod(methodArray[i], new Class[] {});
		initializeObject = method.invoke(initializeObject, new Object[] {});
		}
		this.getHibernateTemplate().lock(initializeObject, org.hibernate.LockMode.NONE);
		this.getHibernateTemplate().initialize(initializeObject);
		}
		}

		/**
		* 初始化POJO类
		* @author @新容
		* @param object POJO对象
		* @param methodName 方法名称数组
		* @return
		* @version 1.0
		*/
		public void initialize(Object object, String methodName[])
		throws SecurityException, NoSuchMethodException,
		IllegalArgumentException, IllegalAccessException, InvocationTargetException {

		for (int i = 0; i < methodName.length; i++) {
		String[] methodArray = methodName[i].split("\\.");
		Method method = null;
		Object initializeObject = object;

		if(methodArray.length == 1){
		this.getHibernateTemplate().lock(initializeObject, org.hibernate.LockMode.NONE);
		method = object.getClass().getMethod(methodArray[0], new Class[] {});
		initializeObject = method.invoke(initializeObject, new Object[] {});
		this.getHibernateTemplate().initialize(initializeObject);
		}else{
		for(int j=0;j<methodArray.length;j++){
		method = initializeObject.getClass().getMethod(methodArray[j], new Class[] {});
		initializeObject = method.invoke(initializeObject, new Object[] {});
		}
		this.getHibernateTemplate().lock(initializeObject, org.hibernate.LockMode.NONE);
		this.getHibernateTemplate().initialize(initializeObject);
		}
		}

		}

		/**
		* 初始化POJO类
		* @author @新容
		* @param object POJO对象
		* @return
		* @version 1.0
		*/
		public void initialize(Object object) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		this.getHibernateTemplate().lock(object, org.hibernate.LockMode.NONE);
		this.getHibernateTemplate().initialize(object);
		}

		/**
		* 初始化POJO类
		* @author @新容
		* @param collection POJO对象集合
		* @param methodName 方法名称数组
		* @return
		* @version 1.0
		*/
		@SuppressWarnings("unchecked")
		public void initialize(Collection collection, String methodName[])
		throws SecurityException, NoSuchMethodException,
		IllegalArgumentException, IllegalAccessException, InvocationTargetException {

		for(Iterator i=collection.iterator();i.hasNext();){
		Object object = i.next();
		this.initialize(object,methodName);
		}
		}

		/**
		* 初始化POJO类
		* @author @新容
		* @param collection POJO对象集合
		* @param methodName 方法名称
		* @return
		* @version 1.0
		*/
		@SuppressWarnings("unchecked")
		public void initialize(Collection collection, String methodName)
		throws SecurityException, NoSuchMethodException,
		IllegalArgumentException, IllegalAccessException, InvocationTargetException {

		for(Iterator i=collection.iterator();i.hasNext();){
		Object object = i.next();
		this.initialize(object,methodName);
		}
		}
		

}
