package by.htp.ex.util.validation.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

import by.htp.ex.bean.User;
import by.htp.ex.util.exception.*;
import by.htp.ex.util.validation.UserDataValidation;

public class UserDataValidationImpl implements UserDataValidation {

	@Override
	public boolean isCorrectPassword(String enteredPassword, String dbPasswordHash) throws SQLException {
		return BCrypt.checkpw(enteredPassword, dbPasswordHash);

	}
}
