package org.ferrari.exception;
/**
 * 
   * @create.date: 2009-5-7 上午09:38:03     
   * @comment: <p>TODO</p>
   * @see: org.ferrari.exception
   * @author: yuxinrong
   * @modify.by: yuxinrong
   * @modify.date: 2009-5-7 上午09:38:03
 */
public class AppCheckedException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AppCheckedException() {
		super();
		
	}

	public AppCheckedException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public AppCheckedException(String message) {
		super(message);
	
	}

	public AppCheckedException(Throwable cause) {
		super(cause);
		
	}

}
