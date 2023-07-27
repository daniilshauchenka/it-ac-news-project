package by.tr.conspect.service;

public class ServiceException extends Exception{
	private static final long serialVersionUID = 7072665062264690928L;

	
	public ServiceException() {
		super();
	}
	
	public ServiceException(String message) {
		super(message);
	}
	
	public ServiceException(Exception e) {
		super(e);
	}
	
	public ServiceException(String message, Exception e) {
		super(message, e);
	}
	
}
