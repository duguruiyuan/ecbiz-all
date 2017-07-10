package org.ferrari.exception;


/**
 * 
   * @create.date: May 27, 2009 7:56:04 PM     
   * @comment: <p>TODO</p>
   * @see: org.ferrari.exception
   * @author: yuxinrong
   * @modify.by: yuxinrong
   * @modify.date: May 27, 2009 7:56:04 PM
 */
public class ValidateException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ValidateException() {
		super();
		
	}

	public ValidateException(String message, Throwable cause) {
		super(message, cause);
	
	}

	public ValidateException(String message) {
		super(message);
	
	}

	public ValidateException(Throwable cause) {
		super(cause);
		
	}



}
