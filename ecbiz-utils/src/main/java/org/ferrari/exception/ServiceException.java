package org.ferrari.exception;
/**
 * 
   * @create.date: 2009-5-7 上午09:36:03     
   * @comment: <p>TODO</p>
   * @see: org.ferrari.exception
   * @author: yuxinrong
   * @modify.by: yuxinrong
   * @modify.date: 2009-5-7 上午09:36:03
 */
public class ServiceException extends AppRuntimeException{

	/**
	 * @Description
	 *
	 */
	private static final long serialVersionUID = 1L;

	public ServiceException() {
		super();

	}
    /**
     * 
     * @param message
     * @param cause
     */
	public ServiceException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public ServiceException(String message) {
		super(message);
	
	}

	public ServiceException(Throwable cause) {
		super(cause);
	
	}


	public String toString() {
	
		return super.toString();
	}

	
	public String getMessage() {
	@SuppressWarnings("unused")
	String  ss = 	super.getMessage();
	//StackTraceElement[] stacke = super.getStackTrace();
	//StringBuffer   messageBuffer = new StringBuffer();
//	for(StackTraceElement st : stacke)
//	{
//		messageBuffer.append(" 类名："+st.getClassName()+"  ");
//		messageBuffer.append("文件名：" +st.getFileName()+"  ");
//		
//		messageBuffer.append("方法名：" +st.getMethodName()+" ");
//		 
//		messageBuffer.append("行号：" +st.getLineNumber());
////		System.out.println("类名："+st.getClassName());
////		System.out.println("行号：" +st.getLineNumber());
////		
////		System.out.println("文件名：" +st.getFileName());
////		System.out.println("方法名名：" +st.getMethodName());
//	}
		return super.getMessage();
	}


	public StackTraceElement[] getStackTrace() {
		StackTraceElement[] stacke = super.getStackTrace();
		
		return  stacke;
	}


	public void printStackTrace() {
		
		super.printStackTrace();
	}
	

}
