package org.ferrari.exception;

import java.io.PrintStream;
import java.io.PrintWriter;


/**
 * 
   * @create.date: 2009-5-6 下午03:36:25     
   * @comment: <p>TODO</p>
   * @see: org.ferrari.exception
   * @author: yuxinrong
   * @modify.by: yuxinrong
   * @modify.date: 2009-5-6 下午03:36:25
 */
public abstract class AppRuntimeException extends RuntimeException {
    
	public AppRuntimeException() {
		super();
	
	}
    /**
     * 
     * @param message
     * @param cause
     */
	public AppRuntimeException(String message, Throwable cause) {
		super(message, cause);
	
	}
    /**
     * 
     * @param message
     */
	public AppRuntimeException(String message) {
		super(message);
		
	}

	public AppRuntimeException(Throwable cause) {
		super(cause);
	
	}

	
	public String toString() {
	
		return "异常类型： "+super.toString();
	}


	public StackTraceElement[] getStackTrace() {

		
		
		return super.getStackTrace();
	}


	public void printStackTrace() {
	
		super.printStackTrace();
	}

	
	public void printStackTrace(PrintStream s) {
	
		super.printStackTrace(s);
	}


	public void printStackTrace(PrintWriter s) {
	
		super.printStackTrace(s);
	}





	
	public Throwable getCause() {
	
		return super.getCause();
	}

	public String getMessages() {
		
		return "原因："+super.getMessage() +" 方法："+super.getStackTrace()[0].getMethodName()+" 第 "+super.getStackTrace()[0].getLineNumber()+"行";
	}
	
	public String getMessage() {
	
		return  super.getMessage();
	}
   
//	public String getNewMessage() {
		
//		StackTraceElement[] stacke = super.getStackTrace();
//		StringBuffer   messageBuffer = new StringBuffer();
//		for(StackTraceElement st : stacke)
//		{
//			messageBuffer.append(" 类名："+st.getClassName()+"  ");
//			messageBuffer.append("文件名：" +st.getFileName()+"  ");
//			
//			messageBuffer.append("方法名：" +st.getMethodName()+" ");
//			 
//			messageBuffer.append("行号：" +st.getLineNumber());
//			System.out.println("类名："+st.getClassName());
//			System.out.println("行号：" +st.getLineNumber());
//			
//			System.out.println("文件名：" +st.getFileName());
//			System.out.println("方法名名：" +st.getMethodName());
	//	}
//		return null;
	//	return  messageBuffer.toString();
//	}
	
}
