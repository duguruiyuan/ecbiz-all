package org.ferrari.utils.upload;

import java.util.ResourceBundle;
/**
 * 
   * @create.date: Jun 3, 2009 10:39:11 AM     
   * @comment: <p>TODO</p>
   * @see: com.jmyz.upload.util
   * @author: yuxinrong
   * @modify.by: yuxinrong
   * @modify.date: Jun 3, 2009 10:39:11 AM
 */
public class DirectoryFactory {

	/**
	 * 
	 * @comment: <p>please description this method to do</p>  
	 * @create.date: Jun 3, 2009 ( 10:39:15 AM )
	 * @author: yuxinrong
	 * @param uploadType
	 * @return
	 * @see: <h1>com.jmyz.upload.util.DirectoryFactory.ReadDirectory</h1>
	 * @modified.by: <if there modified by others,then write author name>
	 * @modified.date: <modified date time>
	 */
	public static String ReadDirectory (CallName callName)
	{
		ResourceBundle bundle = ResourceBundle.getBundle("DirectoryRules");
	    return bundle.getString(callName.toString().toLowerCase()+".dir");

		
	}
	public static String ReadRoot(){
		ResourceBundle bundle = ResourceBundle.getBundle("DirectoryRules");
	    return bundle.getString("ftpurl");
	}
	
	public static String get(String name){
		ResourceBundle bundle = ResourceBundle.getBundle("DirectoryRules");
		return bundle.getString(name);
	}
}
