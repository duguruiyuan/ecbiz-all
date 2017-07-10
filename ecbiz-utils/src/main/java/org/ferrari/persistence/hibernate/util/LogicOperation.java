/**
 * 
   * @Create: 2008-11-27 下午03:27:04     
   * @Description: 封装and和or
   * @modify by: 喻新容 
   * @time: 2008-11-27 下午03:27:04
 */
package org.ferrari.persistence.hibernate.util;
/**
 * 
   * @Create: 2008-11-27 下午03:27:04     
   * @Description: 封装and和or
   * @see: com.jmyz.utils
   * @modify by: 喻新容 
   * @time: 2008-11-27 下午03:27:04
 */
public enum LogicOperation {
	/** 与 **/
	AND {public String getName(){return "and";}},
	/** 或 **/
	OR {public String getName(){return "or";}},
	/** 与 betwwen匹配的and**/
	BTAND {public String getName(){return "AND";}},
	/** 或 **/
	ORR {public String getName(){return "or)";}},
	/** 与 **/
	ANDL {public String getName(){return "and(";}},
	
	ANDR {public String getName(){return " )and ";}};
	
	public abstract String getName();
}

