package by.htp.ex.util.validation;

import by.htp.ex.util.validation.impl.UserDataValidationImpl;

public class ValidationProvider {

	private final static ValidationProvider instance = new ValidationProvider();
	private final static UserDataValidation userDataValidation = new UserDataValidationImpl();

	private ValidationProvider() {

	}

	public static ValidationProvider getInstance() {
		return instance;
	}

	public UserDataValidation getUserValidator() {
		return userDataValidation;
	}

}
