/**
 * 
   * @Create: 2008-11-27 下午03:19:09     
   * @Description: 封装一些属性和属性值的类
   * @modify by: 喻新容 
   * @time: 2008-11-27 下午03:19:09
 */
package org.ferrari.persistence.hibernate.util;
/**
 * 
   * @Create: 2008-11-27 下午03:19:09     
   * @Description: 里面主要装一些常用的查询语句,如and,or,like
   * @see: com.jmyz.utils
   * @modify by: 喻新容 
   * @time: 2008-11-27 下午03:19:09
 */
public  class Condition {
	/**
	 * 实体的属性名
	 */
	private String enityPropertyName;
	/** 关系运算符 **/
	private RelationOperation relation;
	/** 逻辑运算符 **/
	private LogicOperation logic;
	/** 是逻辑非( not )运算符 **/
	private Boolean logicNot;
	/** 查询值 **/
	private Object queryValue;
	/** 查询数组 **/
	private Object[] queryValues;
	
	/** 组装in **/
	private String  queryIn;
    
	
	
	/**
	 * 
	 * @comment: <p>please description this method to do</p>  
	 * @create.date: 2009-5-7 ( 上午09:37:33 )
	 * @author: yuxinrong
	 * @return
	 * @see: <h1>org.ferrari.persistence.hibernate.util.Condition.getQueryIn</h1>
	 * @modified.by: <if there modified by others,then write author name>
	 * @modified.date: <modified date time>
	 */
	public String getQueryIn() {
	
		return queryIn;
	}
	/**
	 * 
	 * @comment: <p>please description this method to do</p>  
	 * @create.date: 2009-5-7 ( 上午09:38:41 )
	 * @author: yuxinrong
	 * @param queryValues
	 * @see: <h1>org.ferrari.persistence.hibernate.util.Condition.setQueryIn</h1>
	 * @modified.by: <if there modified by others,then write author name>
	 * @modified.date: <modified date time>
	 */
	public void setQueryIn(Object[] queryValues) {
		StringBuffer  sBuffer = new StringBuffer();
		
		sBuffer.append("( ");
		for(int i=0;i<queryValues.length;i++)
		{
			sBuffer.append("?,");
		}
		sBuffer.deleteCharAt(sBuffer.length()-1);
		sBuffer.append(" ) ");
		this.queryIn = sBuffer.toString();
	}
	public Object[] getQueryValues() {
		
		return queryValues;
	}
	public void setQueryValues(Object[] queryValues) {
		this.queryValues = queryValues;
	}
	public Object getQueryValue() {
		return queryValue;
	}
	public void setQueryValue(Object queryValue) {
		this.queryValue = queryValue;
	}
	public Boolean getLogicNot() {
		return logicNot;
	}
	public void setLogicNot(Boolean logicNot) {
		this.logicNot = logicNot;
	}
	
	public String getEnityPropertyName() {
		return enityPropertyName;
	}
	public void setEnityPropertyName(String enityPropertyName) {
		this.enityPropertyName = enityPropertyName;
	}
	public RelationOperation getRelation() {
		return relation;
	}
	public void setRelation(RelationOperation relation) {
		this.relation = relation;
	}
	public LogicOperation getLogic() {
		return logic;
	}
	public void setLogic(LogicOperation logic) {
		this.logic = logic;
	}
	public Condition(String enityPropertyName, RelationOperation relation, LogicOperation logic,Object queryValue) {
		this.enityPropertyName = enityPropertyName;
		this.relation = relation;
		this.logic = logic;
		
		this.queryValue = queryValue;
		
	}
	
	public Condition(String enityPropertyName, RelationOperation relation, LogicOperation logic,Object queryValue,Object[] queryValues) {
		this.enityPropertyName = enityPropertyName;
		this.relation = relation;
		this.logic = logic;

	
		if(queryValues!=null)
		{
			this.setQueryValues(queryValues);
			this.setQueryIn(queryValues);
			this.queryValue ="in的默认值";
			
			
		}
	}
	
	
}
