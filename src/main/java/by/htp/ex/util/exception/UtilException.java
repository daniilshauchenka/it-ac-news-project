package by.htp.ex.util.exception;

public class UtilException extends Exception {
	private static final long serialVersionUID = 8814453066415187129L;

	public UtilException() {
		super();
	}
	
	public UtilException(String message) {
		super(message);
	}
	
	public UtilException(Exception e) {
		super(e);
	}
	
	public UtilException(String message, Exception e) {
		super(message, e);
	}
}
