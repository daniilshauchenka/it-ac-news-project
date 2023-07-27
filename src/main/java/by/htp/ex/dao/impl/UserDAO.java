package by.htp.ex.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import by.htp.ex.bean.Role;
import by.htp.ex.bean.User;
import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.IUserDAO;
import by.htp.ex.dao.pool.ConnectionPool;
import by.htp.ex.dao.pool.ConnectionPoolException;
import by.htp.ex.util.validation.UserDataValidation;
import by.htp.ex.util.validation.ValidationProvider;

public class UserDAO implements IUserDAO {

	private final static ConnectionPool connectionPool = ConnectionPool.getInstance();
	private final static UserDataValidation validator = ValidationProvider.getInstance().getUserValidator();
	private final static String ru_PARAM = "ru";
	private final static String RU_PARAM = "RU";
	private final static String en_PARAM = "en";
	private final static String US_PARAM = "US";
	private final static String USER_INFO_RU_PARAM = "users_info_ru";
	private final static String USER_INFO_EN_PARAM = "users_info_en";

	// private final static String SQL_TO_AUTH_USER = "SELECT * FROM users WHERE
	// login = ?";
//	private final static String SQL_TO_AUTH_USER = "select * from users left join users_has_roles on users.id = users_has_roles.users_id where login = ?";
	private final static String SQL_TO_AUTH_USER = "select users.id, login, password, email, users_info.name, users_info.surname, roles.name as role from users left join users_has_roles on users.id = users_has_roles.users_id left "
			+ "join roles on users_has_roles.roles_id = roles.id left join users_info on users.id=users_info.users_id where login = ?";

	@Override
	public User authorization(String login, String password) throws DaoException {
		try (Connection connection = connectionPool.takeConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_TO_AUTH_USER)) {
		
			preparedStatement.setString(1, login);
		
			ResultSet resultSet = null;
			try {
				resultSet = preparedStatement.executeQuery();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			
			if (!resultSet.next()) {
	
				throw new DaoException("User doesn't exist");
			}

			if (!validator.isCorrectPassword(password, resultSet.getString("password"))) {
				throw new DaoException("Incorrect password!");
			}

			User user = getUserFromResultSet(resultSet);
			System.out.println(user.toString());
			// user.setLocale(null);
			return user;
		} catch (SQLException e) {
			throw new DaoException("Error with SQL", e);
		} catch (ConnectionPoolException e) {
			throw new DaoException("Error with Connection Pool", e);
		}
	}

	public User getUserFromResultSet(ResultSet resultSet) throws DaoException, SQLException {
		User user = new User();
	
		user.setId(resultSet.getInt("id"));
		user.setLogin(resultSet.getString("login"));
		user.setEmail(resultSet.getString("email"));
		user.setName(resultSet.getString("name"));
		user.setSurname(resultSet.getString("surname"));
	//	user.setPassword(resultSet.getString("password"));
		String role = resultSet.getString("role");
		user.setRole(Role.valueOf(role.toUpperCase()));
		if (user.getRole() == null) {
			throw new DaoException("Users role not found!");
		}
		return user;
	}
	
	public User getUserCredentials(User user) {
		return user;
		
	}

	private final static String SQL_ADDING_USER = "INSERT INTO users (login, password, email) VALUES (?, ?, ?)";
	private final static String SQL_ADDING_LOCALE = "INSERT INTO locales (users_id, language, country) VALUES (?,?,?)";
	private final static String SQL_ADDING_USER_INFO = "INSERT INTO users_info (users_id, name, surname) VALUES (?,?,?)";
	private final static String SQL_ADDING_USER_ROLE = "insert into users_has_roles (users_id, roles_id) values (?, (select id from roles where roles.name = ?))";
	private final static String USER_DEFAULT_ROLE = "USER";

	@Override
	public boolean registration(User user) throws DaoException {
		//ln(user);
		boolean result = false;
		Connection connection = null;
		PreparedStatement preparedStatementAddingUser = null;
		// PreparedStatement preparedStatementAddingLocale = null;
		PreparedStatement preparedStatementAddingUserInfo = null;
		PreparedStatement preparedStatementAddingUserRole = null;
		try {
			connection = connectionPool.takeConnection();
			connection.setAutoCommit(false);
			if (userExists(user, connection)) {
				throw new DaoException("User exists!!!");

			}

			preparedStatementAddingUser = connection.prepareStatement(SQL_ADDING_USER, Statement.RETURN_GENERATED_KEYS);
			preparedStatementAddingUser.setString(1, user.getLogin());
			preparedStatementAddingUser.setString(2, user.getPassword());
			preparedStatementAddingUser.setString(3, user.getEmail());
			preparedStatementAddingUser.executeUpdate();
			user.setPassword(null);
			ResultSet resultSet = preparedStatementAddingUser.getGeneratedKeys();
			resultSet.next();
			int userId = resultSet.getInt(1);
			connection.commit();
			user.setId(userId);
			preparedStatementAddingUserRole = connection.prepareStatement(SQL_ADDING_USER_ROLE);
			preparedStatementAddingUserRole.setInt(1, user.getId());
			preparedStatementAddingUserRole.setString(2, USER_DEFAULT_ROLE);
			preparedStatementAddingUserRole.executeUpdate();
			user.setRole(Role.valueOf(USER_DEFAULT_ROLE));

			preparedStatementAddingUserInfo = connection.prepareStatement(SQL_ADDING_USER_INFO);
			preparedStatementAddingUserInfo.setInt(1, user.getId());
			preparedStatementAddingUserInfo.setString(2, user.getName());
			preparedStatementAddingUserInfo.setString(3, user.getSurname());

			preparedStatementAddingUserInfo.executeUpdate();

			connection.setAutoCommit(true);
			return true;
		} catch (ConnectionPoolException | SQLException |DaoException e) {

			if (connection != null) {
				try {
					connection.rollback();
					if (!connection.getAutoCommit()) {
						connection.setAutoCommit(true);
					}
				} catch (SQLException ex) {
					throw new DaoException(ex);
				}
			}
			throw new DaoException(e);
		} finally {
			try {
				connectionPool.closeConnection(connection, preparedStatementAddingUser);
				// connectionPool.closeConnection(preparedStatementAddLocal);
				connectionPool.closeConnection(preparedStatementAddingUserInfo);
				connectionPool.closeConnection(preparedStatementAddingUserRole);
				//ln("connections closed");
			} catch (ConnectionPoolException e) {
				throw new DaoException(e);
			}
		}

	}

	private final static String SQL_TO_CHECK_USER_EXIST = "SELECT * FROM users WHERE email = ? OR login = ? ";

	private boolean userExists(User user, Connection connection) throws SQLException {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SQL_TO_CHECK_USER_EXIST);
			//ln("checking if user exists");

			preparedStatement.setString(1, user.getEmail());
			preparedStatement.setString(2, user.getLogin());
			// preparedStatement.setInt(3, user.getId());
			ResultSet resultSet = preparedStatement.executeQuery();
			//ln("checked");

			return resultSet.next();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return false;
	}
}
