 /**     
   * @create.date: 2009-5-11 上午10:57:28
   * @author: kevin 
   * @company: 车马驿站
   * @see:org.ferrari.utils.logs 
   */ 
package org.ferrari.logs;



 /**  
 * @create.date: 2009-5-11 上午10:57:28     
 * @comment: <p>TODO</p>
 * @see: org.ferrari.utils.logs
 * @author: kevin
 * @verson: 1.0
 */
public interface ILogger {
	public String getContent();
	public String getAction();
	public void execute();
}
