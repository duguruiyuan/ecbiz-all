/**
 * 
   * @Create: 2008-11-27 下午03:28:12     
   * @Description:封装了查询的list和总记录数
   * @modify by: 喻新容 
   * @time: 2008-11-27 下午03:28:12
 */
package org.ferrari.persistence.hibernate.util;

import java.util.List;
/**
 * 
   * @Create: 2008-11-27 下午03:28:12     
   * @Description:封装了查询的list和总记录数
   * @see: com.jmyz.utils
   * @modify by: 喻新容 
   * @time: 2008-11-27 下午03:28:12
 */
public class QueryResult<T> {
	//结果集
	private List<T> result;
	//记录数
	private long recordnum;
	public List<T> getResult() {
		return result;
	}
	public void setResult(List<T> result) {
		this.result = result;
	}
	
	public long getRecordnum() {
		return recordnum;
	}
	public void setRecordnum(long recordnum) {
		this.recordnum = recordnum;
	}
	
}
