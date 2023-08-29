package by.htp.ex.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import by.htp.ex.bean.Role;
import by.htp.ex.bean.User;
import by.htp.ex.dao.IUserDAO;
import by.htp.ex.dao.exception.DaoException;
import by.htp.ex.dao.pool.ConnectionPool;
import by.htp.ex.dao.pool.ConnectionPoolException;
import by.htp.ex.util.validation.UserDataValidation;
import by.htp.ex.util.validation.ValidationProvider;

public class UserDAO implements IUserDAO {

	private final static ConnectionPool connectionPool = ConnectionPool.getInstance();
	private final static UserDataValidation validator = ValidationProvider.getInstance().getUserValidator();

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
				throw new DaoException("error.sql_problem", ex);
			}

			if (!resultSet.next()) {
				throw new DaoException("error.user_not_found");
			}

			if (!validator.isCorrectPassword(password, resultSet.getString("password"))) {
				throw new DaoException("error.incorrect_password");
			}

			User user = getUserFromResultSet(resultSet);

			return user;
		} catch (SQLException e) {
			throw new DaoException("error.sql_problem", e);
		} catch (ConnectionPoolException e) {
			throw new DaoException("error.connection_pool_problem", e);
		}
	}

	public User getUserFromResultSet(ResultSet resultSet) throws DaoException, SQLException {
		User user = new User();

		user.setId(resultSet.getInt("id"));
		user.setLogin(resultSet.getString("login"));
		user.setEmail(resultSet.getString("email"));
		user.setName(resultSet.getString("name"));
		user.setSurname(resultSet.getString("surname"));
		// user.setPassword(resultSet.getString("password"));
		String role = resultSet.getString("role");
		user.setRole(Role.valueOf(role.toUpperCase()));
		if (user.getRole() == null) {
			throw new DaoException("error.user_role_not_found");
		}
		return user;
	}

	private final static String SQL_ADDING_USER = "INSERT INTO users (login, password, email) VALUES (?, ?, ?)";
	private final static String SQL_ADDING_USER_INFO = "INSERT INTO users_info (users_id, name, surname) VALUES (?,?,?)";
	private final static String SQL_ADDING_USER_ROLE = "insert into users_has_roles (users_id, roles_id) values (?, (select id from roles where roles.name = ?))";
	private final static String USER_DEFAULT_ROLE = "USER";

	@Override
	public boolean registration(User user) throws DaoException {
		Connection connection = null;
		PreparedStatement preparedStatementAddingUser = null;
		PreparedStatement preparedStatementAddingUserInfo = null;
		PreparedStatement preparedStatementAddingUserRole = null;
		try {
			connection = connectionPool.takeConnection();
			connection.setAutoCommit(false);
			if (userExists(user, connection)) {
				throw new DaoException("error.user_exists");
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
		} catch (ConnectionPoolException | SQLException | DaoException e) {

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
				connectionPool.closeConnection(preparedStatementAddingUserInfo);
				connectionPool.closeConnection(preparedStatementAddingUserRole);
			} catch (ConnectionPoolException e) {
				throw new DaoException(e);
			}
		}

	}

	private final static String SQL_TO_CHECK_USER_EXIST = "SELECT * FROM users WHERE email = ? OR login = ? ";

	private boolean userExists(User user, Connection connection) throws SQLException {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SQL_TO_CHECK_USER_EXIST);
			preparedStatement.setString(1, user.getEmail());
			preparedStatement.setString(2, user.getLogin());
			ResultSet resultSet = preparedStatement.executeQuery();
			return resultSet.next();
		} catch (SQLException ex) {
		}
		return false;
	}

	private final static String SQL_GET_USER_LIST = "SELECT users.id as id, login,  email, users_info.name, users_info.surname, roles.name as role FROM users "
			+ "left join users_has_roles on users.id = users_has_roles.users_id left join roles on users_has_roles.roles_id = roles.id left join users_info on users.id=users_info.users_id ORDER BY id ASC  LIMIT ? OFFSET ? ";

	@Override
	public List<User> getList(int offset, int limit) throws DaoException {
		List<User> result = new ArrayList<User>();
		try (Connection connection = connectionPool.takeConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_USER_LIST)) {
			preparedStatement.setInt(1, limit);
			preparedStatement.setInt(2, offset);

			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {

				User user = new User(resultSet.getInt("id"), resultSet.getString("login"),
						Role.valueOf(resultSet.getString("role")));
				result.add(user);
			}
			return result;
		} catch (SQLException | ConnectionPoolException e) {
			throw new DaoException(e);
		}

	}

	private final static String SQL_GET_SINGLE_USER = "select login, email, users_info.name as name, users_info.surname as surname, roles.name as role from users left join users_has_roles on users.id = users_has_roles.users_id left "
			+ "join roles on users_has_roles.roles_id = roles.id left join users_info on users.id=users_info.users_id where users.id = ?";

	@Override
	public User getUserById(int id) throws DaoException {
		User user = null;
		try (Connection connection = connectionPool.takeConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_GET_SINGLE_USER)) {
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				String name = resultSet.getString("name");
				String surname = resultSet.getString("surname");
				String login = resultSet.getString("login");
				String email = resultSet.getString("email");
				Role role = Role.valueOf(resultSet.getString("role"));
				user = new User(id, login, email, name, surname, role);
			}
			if (user==null) {
				throw new DaoException("error.user_not_found");
			}
			return user;
		} catch (SQLException | ConnectionPoolException e) {
			throw new DaoException(e);
		}
	}

	private final static String SQL_GET_USERS_QUANTITY = "SELECT COUNT(*) FROM users";

	@Override
	public int getUsersQuantity() throws DaoException {
		int quantity = -1;
		try (Connection connection = connectionPool.takeConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_GET_USERS_QUANTITY)) {

			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				quantity = resultSet.getInt(1);
			}
		} catch (SQLException | ConnectionPoolException e) {
			throw new DaoException(e);
		}
		return quantity;
	}
}
