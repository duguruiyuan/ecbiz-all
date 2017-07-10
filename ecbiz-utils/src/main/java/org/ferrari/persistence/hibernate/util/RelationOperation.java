/**
 * 
   * @Create: 2008-11-27 下午03:30:35     
   * @Description: 这个类将关系运算符封装成对象
   * @modify by: 喻新容 
   * @time: 2008-11-27 下午03:30:35
 */
package org.ferrari.persistence.hibernate.util;
/**
 * 
   * @Create: 2008-11-27 下午03:30:35     
   * @Description: 这个类将关系运算符封装成对象
   * @see: com.jmyz.utils
   * @modify by: 喻新容 
   * @time: 2008-11-27 下午03:30:35
 */
public enum RelationOperation {
	/** 等于 **/
	EQ {public String getName(){return "=";}},
	/** 不等于 **/
	NE {public String getName(){return "!=";}},
	/** 小于 **/
	LT {public String getName(){return "<";}},
	/** 大于 **/
	GT {public String getName(){return ">";}},
	/** 小于等于 **/
	LE {public String getName(){return "<=";}},
	/** 大于等于 **/
	GE {public String getName(){return ">=";}},
	/** between **/
	BT {public String getName(){return "BETWEEN";}},
	/** between **/
	IN {public String getName(){return "in";}},
	/** between **/
	IS {public String getName(){return "is null";}},
	
	ISNOT {public String getName(){return "is not null";}},
	
	/** 模糊查询 %查询值%**/
	LIKE {public String getName(){return "like";}},
	//%查询值
	LEFTLIKE {public String getName(){return "like";}},
	NOT_LEFTLIKE {public String getName(){return "not like";}},
	////查询值%
	RIGHTLIKE {public String getName(){return "like";}},
	NOT_RIGHTLIKE {public String getName(){return "not like";}};
	
	public abstract String getName();
}

