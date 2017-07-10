package org.ferrari.exception;
/**
 * 
   * @create.date: 2009-5-7 上午09:35:52     
   * @comment: <p>TODO</p>
   * @see: org.ferrari.exception
   * @author: yuxinrong
   * @modify.by: yuxinrong
   * @modify.date: 2009-5-7 上午09:35:52
 */
public class DaoException extends AppRuntimeException{

	/**
	 * @Description
	 *
	 */
	private static final long serialVersionUID = 1L;

	public DaoException() {
		super();
		
	}

	public DaoException(String message, Throwable cause) {
		super(message, cause);

	}
    /**
     * 
     * @param message
     */
	public DaoException(String message) {
		super(message);
	
	}

	public DaoException(Throwable cause) {
		super(cause);
	
	}

}
