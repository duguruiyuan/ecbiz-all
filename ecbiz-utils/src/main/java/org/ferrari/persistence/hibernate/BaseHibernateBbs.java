package org.ferrari.persistence.hibernate;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.ferrari.exception.PersistenceException;
import org.ferrari.persistence.hibernate.iface.BaseDao;
import org.ferrari.persistence.hibernate.util.Condition;
import org.ferrari.persistence.hibernate.util.QueryResult;
import org.ferrari.persistence.hibernate.util.QueryUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;
/**
 * 
   * @create.date: Jul 4, 2009 11:49:06 AM     
   * @comment: <p>TODO</p>
   * @see: org.ferrari.persistence.hibernate
   * @author: shellrong
   * @modify.by: shellrong
   * @modify.date: Jul 4, 2009 11:49:06 AM
 */
@Transactional
public class BaseHibernateBbs implements BaseDao {
	@Resource(name="sessionFactoryBbs")
	private SessionFactory sessionFactory;
	
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}


	public BaseHibernateBbs() {

	}


	public int delete(String hql) {
		Session  session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		
		return query.executeUpdate();
	}

	
	public <T> void delete(Object entity) {
	
       this.sessionFactory.getCurrentSession().delete(entity);
	}


	public <T> void delete(Class<T> entityClass, Object entityId) {
		
	Session  session = this.sessionFactory.getCurrentSession();
	session.delete(session.get(entityClass, (Serializable) entityId));
	
	}


	public <T> int delete(Class<T> entityClass, Object[] entityIds) {
	 
		Session  session = this.sessionFactory.getCurrentSession();
		String classname = entityClass.getSimpleName();
		StringBuffer sb = new StringBuffer();
		sb.append("(");
		for(int i=0;i<entityIds.length;i++)
		{		
			sb.append(entityIds[i]+",");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append(")");
		String hql = "  delete from  "+classname+" o "+"where o.id in "+sb;
		Query query = session.createQuery(hql);
		
		return query.executeUpdate();
	}


	public <T> QueryResult<T> getData(Class<T> entityClass, int firstIndex,
			int maxResult) {
	
		return this.getData(entityClass, firstIndex, maxResult,null,null);
	}


	@SuppressWarnings("unchecked")
	public <T> QueryResult<T> getData(Class<T> entityClass, int firstIndex,
			int maxResult, Condition[] conditions,
			LinkedHashMap<String, String> orderby) {
		
		try {
			
			Session  session = this.sessionFactory.getCurrentSession();
			QueryResult<T> qr = new QueryResult<T>();
			
			
			String hql = "select o from " + entityClass.getSimpleName() + " o "
					+ QueryUtils.buildDianQL(conditions) + buildOrderbyQL(orderby);
	
			Query query = session.createQuery(hql);
			QueryUtils.setQueryDianByCondition(0, query, conditions);
			
			if (firstIndex != -1 && maxResult != -1)
				query.setFirstResult(firstIndex).setMaxResults(maxResult);
			
			qr.setResult(query.list());
			
			query = session.createQuery(
							"select count(o) from "+entityClass.getSimpleName()
									+ " o" + QueryUtils.buildDianQL(conditions));
			QueryUtils.setQueryDianByCondition(0, query, conditions);
			
			qr.setRecordnum((Long) query.uniqueResult());
			
			return qr;
		} catch (PersistenceException e) {
			throw new PersistenceException(e);
		}
		

	}


	public <T> QueryResult<T> getData(Class<T> entityClass, int firstIndex,
			int maxResult, LinkedHashMap<String, String> orderby) {
	
		return  this.getData(entityClass, firstIndex, maxResult,null, orderby);
	}

	
	@SuppressWarnings("unchecked")
	public <T> T getEntity(Class<T> entityClass, Object entityId) {

		return (T) this.getSessionFactory().getCurrentSession().get(entityClass, (Serializable)entityId);
	}

	
	@SuppressWarnings("unchecked")
	public <T> T getEntity(String hql) {
		Session  session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		
		return (T)query.uniqueResult();
	}


	
	
	@SuppressWarnings("unchecked")
	public <T> List<T> getList(String hql, Integer startInt, Integer maxInt) {
	
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
	
		if (startInt != -1 && maxInt != -1) {
			query.setFirstResult(startInt).setMaxResults(maxInt);
		}
	
		
		return (List<T>)query.list();
	}

	
	@SuppressWarnings("unchecked")
	public <T> List<T> getList(String hql, Object[] values, Integer startInt,
			Integer maxInt) {
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		
		if(values != null){
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
				
			}
		}
		
		if (startInt != -1 && maxInt != -1) {
			query.setFirstResult(startInt).setMaxResults(maxInt);
		}
	
		
		return (List<T>)query.list();
	}

	
	public long getTotalSize(String hql) {
		Session  session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		
		return  (Long) query.uniqueResult();
	}


	public long getTotalSize(String hql, Object[] values) {
		Session  session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		if(values != null){
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
				
			}
		}
		return  (Long) query.uniqueResult();
	}

	public void save(Object entity) {
	
		 this.sessionFactory.getCurrentSession().persist(entity);
	}


	public <T> void saveOrUpdate(T entity) {
		 this.sessionFactory.getCurrentSession().saveOrUpdate(entity);

	}


	public void update(Object entity) {
	
		 this.sessionFactory.getCurrentSession().merge(entity);
	}


	public int update(String hql) {
		Session  session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		
		return query.executeUpdate();

	}


	public <T> boolean validate(Class<T> classname, String[] fieldnames,
			Object[] values) {
		String bean = classname.getSimpleName();
		StringBuffer mycondition = new StringBuffer();
		for(int i=0;i<fieldnames.length;i++)
		{	
		 mycondition.append(" o."+fieldnames[i]+"=? and ");
		}
		int start =mycondition.toString().length()-4;
		mycondition.delete(start,start+4);
		String hql = "from "+bean+" o " +" where " + mycondition.toString();
		long mysize = this.getTotalSize("select count(o) "+hql, values);
		return mysize>0;
		
	}
	
	protected static String buildOrderbyQL(LinkedHashMap<String, String> orderby) {
		StringBuffer ql = new StringBuffer("");
		if (orderby != null && orderby.size() > 0) {
			ql.append(" order by ");
			for (String key : orderby.keySet()) {
				ql.append("o." + key + " " + orderby.get(key) + ",");
			}
			ql.deleteCharAt(ql.length() - 1);
		}
		return ql.toString();
	}
	public void evcit(Object entity) {
		
		 this.sessionFactory.getCurrentSession().evict(entity);
	}
	public void clear() {
		this.sessionFactory.getCurrentSession().clear();
	}
	public void flush() {
		this.sessionFactory.getCurrentSession().flush();
	}
	
}
