package org.ferrari.logs;

import org.apache.struts.action.ActionMapping;
import org.ferrari.exception.UtilsException;
import org.ferrari.utils.CommonUtils;
/**
 * 
   * @create.date: 2009-5-11 下午02:01:34     
   * @comment: <p>TODO</p>
   * @see: org.ferrari.logs
   * @author: kevin
   * @verson: 1.0
 */
public abstract class AbstractLogger {
	/**
	 * 
	 * @comment: <p>返回ACTION中操作方法名</p>  
	 * @create.date: 2009-5-11 ( 下午02:01:19 )
	 * @author: kevin
	 * @param mapping
	 * @return
	 * @see: <h1>org.ferrari.logs.AbstractLogger.getParameter</h1>
	 * @modified.by: <if there modified by others,then write author name>
	 * @modified.date: <modified date time>
	 */
	public String getAction(ActionMapping mapping){
		/**get action parameter name*/
		String method = mapping.getParameter();
		if(method == null) throw new UtilsException("no config parameter, please checked struts-config.xml");
		String action = getActionType(method);
		return action;
	}
	/**
	 * 
	 * @comment: <p>get entity by spring context name</p>  
	 * @create.date: 2009-5-13 ( 上午11:38:52 )
	 * @author: kevin
	 * @param name
	 * @return
	 * @see: <h1>org.ferrari.logs.AbstractLogger.getBean</h1>
	 * @modified.by: <if there modified by others,then write author name>
	 * @modified.date: <modified date time>
	 */
	public Object getBean(String name){
		try{
			return CommonUtils.getBean(name);
		}catch(Exception e){
			throw new UtilsException(e);
		}
	}
	public abstract String getActionType(String type);
}
