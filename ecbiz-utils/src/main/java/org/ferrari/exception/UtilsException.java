package org.ferrari.exception;
/**
 * 
   * @create.date: May 11, 2009 9:02:28 AM     
   * @comment: <p>TODO</p>
   * @see: org.ferrari.exception
   * @author: yuxinrong
   * @modify.by: yuxinrong
   * @modify.date: May 11, 2009 9:02:28 AM
 */
public class UtilsException extends AppRuntimeException{

	
	private static final long serialVersionUID = 1L;

	public UtilsException() {
		super();
		// TODO Auto-generated constructor stub
	}
  /**
   * 
   * @param message
   * @param cause
   */
	public UtilsException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public UtilsException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public UtilsException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
