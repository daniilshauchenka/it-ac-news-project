package by.htp.ex.service.exception;

public class ServiceException extends Exception{

	private static final long serialVersionUID = 1L;

	public ServiceException(String e) {
		super(e);
	}
	
	public ServiceException(Exception e) {
		super(e);
	}
}
