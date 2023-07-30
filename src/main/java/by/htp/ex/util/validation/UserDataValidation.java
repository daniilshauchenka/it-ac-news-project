package by.htp.ex.util.validation;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface UserDataValidation {
       boolean isCorrectPassword(String enteredPassword, String dbPasswordHash) throws SQLException;
       boolean isCorrectAuthData(String login, String password);
       
}
