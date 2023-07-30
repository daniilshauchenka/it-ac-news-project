package by.htp.ex.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.htp.ex.bean.News;
import by.htp.ex.dao.INewsDAO;
import by.htp.ex.dao.pool.ConnectionPool;
import by.htp.ex.dao.pool.ConnectionPoolException;
import by.htp.ex.exception.DaoException;
import by.htp.ex.exception.NewsDAOException;

public class NewsDAO implements INewsDAO {
	private final static ConnectionPool connectionPool = ConnectionPool.getInstance();
	
	private final static String SQL_GET_LASTEST_NEWS = "SELECT * FROM news where is_deleted = 0 ORDER BY date_created DESC LIMIT ?";

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
	


	
	private News getFullNewsFromResultSet(ResultSet resultSet) throws SQLException {
		News news = new News();
		news.setIdNews(resultSet.getInt("id"));
		news.setBriefNews(resultSet.getString("brief"));
		news.setContent(resultSet.getString("content"));
		news.setNewsDate(resultSet.getTimestamp("date_created").toString());
		news.setTitle(resultSet.getString("title"));
		news.setImagePath(resultSet.getString("image_path"));
	
		return news;
	}
	
	private News getBriefNewsFromResultSet(ResultSet resultSet) throws SQLException {
		News news = new News();
		news.setIdNews(resultSet.getInt("id"));
		news.setBriefNews(resultSet.getString("brief"));
		news.setNewsDate(resultSet.getDate("date_created").toString());
		news.setTitle(resultSet.getString("title"));
			System.out.println(news);
		return news;
	}
	
	
	private final static String SQL_GET_SINGLE_NEWS = "SELECT * FROM news WHERE id = ?";
	

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

	private final static String SQL_ADD_NEWS = "INSERT INTO news (title, brief, content, image_path, user_id, date_created) VALUES (?, ?, ?, ?, ?, NOW())";

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
			preparedStatementAddingNews.setInt(5, news.getUserId());
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

	@Override
	public void updateNews(News news) throws DaoException {
		
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
