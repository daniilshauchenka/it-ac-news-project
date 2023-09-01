package by.htp.ex.dao.pool;

public final class ConnectionPoolException extends Exception {
	

	private static final long serialVersionUID = 8997494330850650646L;

	public ConnectionPoolException() {
		super();
	}

	public ConnectionPoolException(String message) {
		super(message);
	}

	public ConnectionPoolException(Exception e) {
		super(e);
	}
	
	public ConnectionPoolException(String message, Exception e) {
		super(message, e);
	}
}
