package org.ferrari.utils;

import java.util.Date;

/**
 * 
   * @create.date: Jun 4, 2009 4:59:18 PM     
   * @comment: <p>TODO</p>
   * @see: com.jmyz.upload.util
   * @author: yuxinrong
   * @modify.by: yuxinrong
   * @modify.date: Jun 4, 2009 4:59:18 PM
 */
public class PrimaryKeyGenerator {
    final static PrimaryKeyGenerator primaryKeyGenerator = new PrimaryKeyGenerator();
    private static Integer generator=0;
	private PrimaryKeyGenerator() {
	
	}
	/**
	 * 
	 * @comment: <p>please description this method to do</p>  
	 * @create.date: Jun 4, 2009 ( 4:59:26 PM )
	 * @author: yuxinrong
	 * @return
	 * @see: <h1>com.jmyz.upload.util.PrimaryKeyGenerator.getPrimaryKeyGenerator</h1>
	 * @modified.by: <if there modified by others,then write author name>
	 * @modified.date: <modified date time>
	 */
    public static PrimaryKeyGenerator getPrimaryKeyGenerator()  {
    	
    	return primaryKeyGenerator;
	}
    /**
     * 
     * @comment: <p>please description this method to do</p>  
     * @create.date: Jun 4, 2009 ( 4:59:30 PM )
     * @author: yuxinrong
     * @return
     * @see: <h1>com.jmyz.upload.util.PrimaryKeyGenerator.getGenerator</h1>
     * @modified.by: <if there modified by others,then write author name>
     * @modified.date: <modified date time>
     */
    @SuppressWarnings("unused")
	public Integer getGenerator (){
    	 synchronized(primaryKeyGenerator){
    	generator=Integer.parseInt(DateUtils.getTimeRandom(new Date()))+generator+1;
    	return generator;
    	}
    
    }
	
}
