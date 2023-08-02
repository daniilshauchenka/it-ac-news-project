package by.htp.ex.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.htp.ex.bean.News;
import by.htp.ex.bean.User;
import by.htp.ex.dao.INewsDAO;
import by.htp.ex.dao.pool.ConnectionPool;
import by.htp.ex.dao.pool.ConnectionPoolException;
import by.htp.ex.exception.DaoException;
import by.htp.ex.exception.NewsDAOException;

public class NewsDAO implements INewsDAO {
	private final static ConnectionPool connectionPool = ConnectionPool.getInstance();
	
//	private final static String SQL_GET_LASTEST_NEWS = "SELECT * FROM news where is_deleted = 0 ORDER BY date_created DESC LIMIT ?";

	private final static String SQL_GET_LASTEST_NEWS = "SELECT news.id as id, news.is_deleted as is_deleted, "
			+ "news.title as title, news.brief as brief, news.content as content, news.date_created as date_created, "
			+ "news.image_path as image_path, users.id as author_id, users_info.name as author_name, users_info.surname as author_surname, "
			+ "users.login as author_login from news left join users on news.author_id = users.id left join "
			+ "users_info on users.id=users_info.users_id where news.is_deleted = 0 ORDER BY news.date_created "
			+ "DESC LIMIT ?";

	@Override
	public List<News> getLatestList(int count) throws DaoException {
		List<News> result = new ArrayList<News>();

		try (Connection connection = connectionPool.takeConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_LASTEST_NEWS)){
			preparedStatement.setInt(1, count);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()){
				result.add(getFullNewsFromResultSet(resultSet));
			}
		
		}catch (SQLException | ConnectionPoolException e){
			throw new DaoException(e);
		}
		return result;
	}
	


	
	private News getFullNewsFromResultSet(ResultSet resultSet) throws SQLException, DaoException {
		News news = new News();
		news.setIdNews(resultSet.getInt("id"));
		news.setBriefNews(resultSet.getString("brief"));
		news.setContent(resultSet.getString("content"));
		news.setNewsDate(resultSet.getTimestamp("date_created").toString());
		news.setTitle(resultSet.getString("title"));
		news.setImagePath(resultSet.getString("image_path"));
		
		try {
			news.setAuthorId(Integer.parseInt(resultSet.getString("author_id")));	
			User user = new User();
			user.setId(news.getAuthorId());
			user.setLogin(resultSet.getString("author_login"));
			user.setName(resultSet.getString("author_name"));
			user.setSurname(resultSet.getString("author_surname"));
			news.setAuthor(user);
		}catch (IllegalArgumentException e) {
			throw new DaoException("Author not found");
		}
		
		
			return news;
	}
	
//	private News getBriefNewsFromResultSet(ResultSet resultSet) throws SQLException {
//		News news = new News();
//		news.setIdNews(resultSet.getInt("id"));
//		news.setBriefNews(resultSet.getString("brief"));
//		news.setNewsDate(resultSet.getDate("date_created").toString());
//		news.setTitle(resultSet.getString("title"));
//			System.out.println(news);
//		return news;
//	}
	
	
//	private final static String SQL_GET_SINGLE_NEWS = "SELECT * FROM news WHERE id = ?";
	
	private final static String SQL_GET_SINGLE_NEWS = "SELECT news.id as id, news.is_deleted as is_deleted, "
			+ "news.title as title, news.brief as brief, news.content as content, news.date_created as date_created, "
			+ "news.image_path as image_path, users.id as author_id, users_info.name as author_name, users_info.surname as author_surname, "
			+ "users.login as author_login from news left join users on news.author_id = users.id left join "
			+ "users_info on users.id=users_info.users_id where news.id = ?";

	@Override
	public News fetchById(int id) throws DaoException {
		News news = null;
		try(Connection connection = connectionPool.takeConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_GET_SINGLE_NEWS)){
				statement.setInt(1, id);
				ResultSet resultSet = statement.executeQuery();

				if (resultSet.next()) {
					news = getFullNewsFromResultSet(resultSet);
				}
			}
			catch (SQLException |ConnectionPoolException e){
				throw new DaoException(e);
			}

			return news;
	}

	private final static String SQL_ADD_NEWS = "INSERT INTO news (title, brief, content, image_path, author_id, date_created) VALUES (?, ?, ?, ?, ?, NOW())";

	@Override
	public void addNews(News news) throws DaoException {
		Connection connection = null;
		PreparedStatement preparedStatementAddingNews = null;
		try {
			connection = connectionPool.takeConnection();
			connection.setAutoCommit(false);
			preparedStatementAddingNews = connection.prepareStatement(SQL_ADD_NEWS);
			preparedStatementAddingNews.setString(1, news.getTitle());
			preparedStatementAddingNews.setString(2, news.getBriefNews());
			preparedStatementAddingNews.setString(3, news.getContent());
			preparedStatementAddingNews.setString(4, news.getImagePath());
			preparedStatementAddingNews.setInt(5, news.getAuthorId());
			preparedStatementAddingNews.executeUpdate();
			connection.setAutoCommit(true);
		} catch (ConnectionPoolException | SQLException  e) {
			e.printStackTrace();
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
				connectionPool.closeConnection(connection, preparedStatementAddingNews);
			} catch (ConnectionPoolException e) {
				throw new DaoException(e);
			}
		}
	}

	private final static String SQL_UPDATE_NEWS = "UPDATE news SET title=?, brief=?, content=?, image_path=?  WHERE id=?";

	@Override
	public void updateNews(int id, News news) throws DaoException {
		Connection connection = null;
		PreparedStatement preparedStatementDeleteNews = null;
		try {
			connection = connectionPool.takeConnection();
			connection.setAutoCommit(false);
			preparedStatementDeleteNews = connection.prepareStatement(SQL_UPDATE_NEWS);
			preparedStatementDeleteNews.setString(1, news.getTitle());
			preparedStatementDeleteNews.setString(2, news.getBriefNews());
			preparedStatementDeleteNews.setString(3, news.getContent());
			preparedStatementDeleteNews.setString(4, news.getImagePath());
			preparedStatementDeleteNews.setInt(5, id);
			preparedStatementDeleteNews.executeUpdate();
			connection.setAutoCommit(true);
		} catch (ConnectionPoolException | SQLException  e) {
			e.printStackTrace();
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
				connectionPool.closeConnection(connection, preparedStatementDeleteNews);
			} catch (ConnectionPoolException e) {
				throw new DaoException(e);
			}
		}
	}
	

	private final static String SQL_DELETE_NEWS = "UPDATE news SET is_deleted=1 WHERE id=?";

	@Override
	public void deleteNews(int id) throws DaoException {
		Connection connection = null;
		PreparedStatement preparedStatementDeleteNews = null;
		try {
			connection = connectionPool.takeConnection();
			connection.setAutoCommit(false);
			preparedStatementDeleteNews = connection.prepareStatement(SQL_DELETE_NEWS);
			preparedStatementDeleteNews.setInt(1, id);
			preparedStatementDeleteNews.executeUpdate();
			connection.setAutoCommit(true);
		} catch (ConnectionPoolException | SQLException  e) {
			e.printStackTrace();
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
				connectionPool.closeConnection(connection, preparedStatementDeleteNews);
			} catch (ConnectionPoolException e) {
				throw new DaoException(e);
			}
		}
	}

	private final static String SQL_RECOVER_NEWS = "UPDATE news SET is_deleted=0 WHERE id=?";

	@Override
	public void recoverNews(int id) throws DaoException {
		Connection connection = null;
		PreparedStatement preparedStatementDeleteNews = null;
		try {
			connection = connectionPool.takeConnection();
			connection.setAutoCommit(false);
			preparedStatementDeleteNews = connection.prepareStatement(SQL_DELETE_NEWS);
			preparedStatementDeleteNews.setInt(1, id);
			preparedStatementDeleteNews.executeUpdate();
			connection.setAutoCommit(true);
		} catch (ConnectionPoolException | SQLException  e) {
			e.printStackTrace();
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
				connectionPool.closeConnection(connection, preparedStatementDeleteNews);
			} catch (ConnectionPoolException e) {
				throw new DaoException(e);
			}
		}
	}



	@Override
	public void deleteMultipleNews(int[] id) throws DaoException {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void recoverMultipleNews(int[] id) throws DaoException {
		// TODO Auto-generated method stub
		
	}

}
