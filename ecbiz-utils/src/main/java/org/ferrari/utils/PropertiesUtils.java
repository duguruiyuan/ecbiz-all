package org.ferrari.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Properties;
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
public class PropertiesUtils {
	public static String readValue(String propName, String prop){
		try{
			ResourceBundle.clearCache(Thread.currentThread().getContextClassLoader()) ;
			ResourceBundle bundle = ResourceBundle.getBundle(propName,new ResourceBundle.Control() {
			    @Override
			    public long getTimeToLive(String arg0, Locale arg1) {
			        return TTL_DONT_CACHE;
			    }
			});
			return bundle.getString(prop);
		}catch(Exception e){
			return null;
		}
	}
	public static String dynaReadValue(String propName, String prop){
		Properties propUtil = new Properties();
		InputStream in = null ;
		try {
			in = PropertiesUtils.class.getResource("/"+propName+".properties").openStream()  ;
			propUtil.load(in);
			return propUtil.getProperty(prop) ;
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				if(in != null)
					in.close() ;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null ;
	}
}
