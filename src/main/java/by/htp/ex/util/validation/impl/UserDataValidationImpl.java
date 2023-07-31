package by.htp.ex.util.validation.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;
import by.htp.ex.bean.User;
import by.htp.ex.exception.*;
import by.htp.ex.util.validation.UserDataValidation;

public class UserDataValidationImpl implements UserDataValidation {

	
	@Override
	public boolean isCorrectPassword(String enteredPassword, String dbPasswordHash) throws SQLException {
		return BCrypt.checkpw(enteredPassword, dbPasswordHash);

	}

	@Override
	public boolean isCorrectAuthData(String login, String password) {
		boolean result = true;
		if (login.length() <= 2) {
			result = false;
		}
	
	
		return result;
	}
}
