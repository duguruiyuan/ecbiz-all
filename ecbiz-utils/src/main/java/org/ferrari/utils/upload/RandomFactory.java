package org.ferrari.utils.upload;

import java.util.Date;
import java.util.Random;

import org.ferrari.utils.DateUtils;
/**
 * 
   * @create.date: May 25, 2009 10:00:19 AM     
   * @comment: <p>TODO</p>
   * @see: com.jmyz.upload.util
   * @author: yuxinrong
   * @modify.by: yuxinrong
   * @modify.date: May 25, 2009 10:00:19 AM
 */
public class RandomFactory {
	/**
	 * 
	 * @comment: <p>please description this method to do</p>  
	 * @create.date: Jun 3, 2009 ( 10:46:58 AM )
	 * @author: yuxinrong
	 * @return
	 * @see: <h1>com.jmyz.upload.util.RandomFactory.getRandomCount</h1>
	 * @modified.by: <if there modified by others,then write author name>
	 * @modified.date: <modified date time>
	 */
public static String  getRandomCount(){
	Random random =new Random();
	int k = random.nextInt();
    int j = Math.abs(k % 1000000)+1000000; 
	Date date = new Date();
	String 	uu = DateUtils.getYmdhms(date)+j;
	return uu;
}
}
