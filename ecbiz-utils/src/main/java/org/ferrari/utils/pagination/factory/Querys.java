/**     
 * @create.date: 2009-5-6 下午12:04:04
 * @author: kevin 
 * @company: 车马驿站
 * @see:org.ferrari.utils.pagination.factory 
 */
package org.ferrari.utils.pagination.factory;

import org.ferrari.utils.pagination.QueryKeys;
import org.ferrari.utils.pagination.entry.HibernateQuery;
import org.ferrari.utils.pagination.entry.IbatisQuery;
import org.ferrari.utils.pagination.entry.JdbcQuery;

/**
 * @create.date: 2009-5-6 下午12:04:04
 * @comment:
 *               <p>
 *               不同框架分页工厂类
 *               </p>
 * @see: org.ferrari.utils.pagination.factory
 * @author: kevin
 * @verson: 1.0
 */
public class Querys {

	/** 分页接口 */
	private static IQuery query = null;

	/**
	 * @comment:
	 * <p>
	 * 根据条件创建相应的对象
	 * </p>
	 * @create.date: 2009-5-6 ( 下午02:49:19 )
	 * @author: kevin
	 * @param type
	 * @return IQuery
	 * @see:<h1>org.ferrari.utils.pagination.factory.IQueryFactory.getQuery</h1>
	 * @modified.by: <if there modified by others,then write author name>
	 * @modified.date: <modified date time>
	 */
	public static IQuery getQuery(String type, Object scope) {
		if (type == null)
			return null;
		if (QueryKeys.HIBERNATE.equals(type))
			query = new HibernateQuery(scope);
		if (QueryKeys.IBATIS.equals(type))
			query = new IbatisQuery(scope);
		if (QueryKeys.JDBC.equals(type))
			query = new JdbcQuery(scope);
		return query;
	}
}
