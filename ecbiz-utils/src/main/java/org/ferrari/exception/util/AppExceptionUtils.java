package org.ferrari.exception.util;
/**
 * 
   * @create.date: 2009-5-7 上午09:36:14     
   * @comment: <p>TODO</p>
   * @see: org.ferrari.exception.util
   * @author: yuxinrong
   * @modify.by: yuxinrong
   * @modify.date: 2009-5-7 上午09:36:14
 */
public class AppExceptionUtils {
	/**
	 * 
	 *@description: TODO
	 *@param message
	 *@param cause
	 *@return
	 *@author: 喻新容
	 *@date: 2009-5-5
	 */
	public static String buildMessage(String message, Throwable cause) {
		if (cause != null) {
			StringBuffer buf = new StringBuffer();
			if (message != null) {
				buf.append(message).append("; ");
			}
			buf.append("AppRuntime exception is ").append(cause);
			return buf.toString();
		}
		else {
			return message;
		}
	}

}
