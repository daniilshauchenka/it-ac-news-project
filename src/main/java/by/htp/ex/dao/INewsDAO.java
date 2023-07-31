package by.htp.ex.dao;

import java.util.List;

import by.htp.ex.bean.News;
import by.htp.ex.exception.DaoException;



public interface INewsDAO {

	List<News> getLatestList(int count) throws DaoException;
	News fetchById(int id) throws DaoException;
	void addNews(News news) throws DaoException;
	void updateNews(int id, News news) throws DaoException;
	void deleteNews(int id)throws DaoException;
	void deleteMultipleNews(int [] id) throws DaoException;
	void recoverNews(int id)throws DaoException;
	void recoverMultipleNews(int [] id) throws DaoException;
}
