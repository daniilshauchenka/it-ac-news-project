package by.tr.conspect.dao.impl;

import java.util.Map;

import by.tr.conspect.bean.User;
import by.tr.conspect.util.Constants;
import by.tr.conspect.dao.IUserAccountDao;
import by.tr.conspect.dao.DaoException;
import java.sql.*;

public class SQLUserDao implements IUserAccountDao {

	static {
		try {
			Class.forName(Constants.DB_DRIVER_PATH);
			
		} catch (ClassNotFoundException ex) {
			throw new ExceptionInInitializerError(ex.getMessage());
		}
	}

	private Connection initConnection() throws DaoException {
		Connection connection = null;

		try {
			connection = DriverManager.getConnection(
					Constants.DB_URL + "user=" + Constants.DB_USERNAME + "&password=" + Constants.DB_PASSWORD);
			System.out.println("CONNECTED TO DATABASE");
		} catch (SQLException ex) {
			throw new DaoException(ex);
		}

		return connection;
	}

	private void closeConnection(Connection connection) throws DaoException {
		try {
			connection.close();
		} catch (SQLException ex) {
			throw new DaoException(ex);
		}
	}
	
	

	


	@Override
	public boolean registration(User newUser) throws DaoException {
		boolean isComplete = false;
		try {
			Connection connection;
			if ((connection = initConnection()) != null) {
				System.out.println("CONNECTED");
				String queryCheckIfUserExists = "SELECT login from training_database_news.users WHERE id=?";
				
				String queryAddNewUser = "INSERT INTO training_database_news.users (login, password) VALUES (? , ?)";
						
		//		String queryAddingUserPersonalInfo = "INSERT INTO training-database-news.user_details (users_id, name, surname, reg_date) VALUES (?,?,?,?)\";";
				// добавим юзера в таблицу users. Statement.RETURN_GENERATED_KEYS означает, что мы хотим получить сгенерированный id для нового юзера
				
				PreparedStatement preparedStatement = connection.prepareStatement(queryAddNewUser, Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setString(1, newUser.getLogin());
				preparedStatement.setString(2, newUser.getPassword());
		
				int affectedRows = preparedStatement.executeUpdate();
				System.out.println("affected rows = " + affectedRows);
				
				if(affectedRows == 0) {
					//// добавления не произошло
				}
				  try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
			            if (generatedKeys.next()) {
			                newUser.setId(generatedKeys.getInt(1));
			            }
			            else {
			                throw new SQLException("Creating user failed, no ID obtained.");
			            }
			        }
					System.out.println(newUser.toString());
					
				preparedStatement.close();
				closeConnection(connection);
				isComplete = true;
				
			} else
				throw new DaoException();

		} catch (SQLException | DaoException ex) {
			throw new DaoException(ex);
		}
		return isComplete;
	}

	@Override
	public boolean authorization(User newUser) {
		// TODO Auto-generated method stub
		return false;
	}

}
