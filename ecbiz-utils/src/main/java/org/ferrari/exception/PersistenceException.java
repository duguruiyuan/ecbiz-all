package org.ferrari.exception;

/**
 * 
   * @create.date: 2009-5-7 上午09:15:02     
   * @comment: <p>TODO</p>
   * @see: org.ferrari.exception
   * @author: yuxinrong
   * @modify.by: yuxinrong
   * @modify.date: 2009-5-7 上午09:15:02
 */
public class PersistenceException extends AppRuntimeException {

	private static final long serialVersionUID = 5988736450904569992L;

	public PersistenceException() {
		super();
	}
    /**
     * 
     * @param message
     */
	public PersistenceException(String message) {
		super(message);
	}
    /**
     * 
     * @param cause
     */
	public PersistenceException(Throwable cause) {
		super(cause);
	}

	public PersistenceException(String message, Throwable cause) {
		super(message, cause);
	}

}
