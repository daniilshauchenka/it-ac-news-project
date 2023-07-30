package by.htp.ex.service.impl;

import java.util.List;

import by.htp.ex.bean.News;
import by.htp.ex.dao.DaoProvider;
import by.htp.ex.dao.INewsDAO;
import by.htp.ex.exception.DaoException;
import by.htp.ex.exception.NewsDAOException;
import by.htp.ex.exception.ServiceException;
import by.htp.ex.service.INewsService;

public class NewsServiceImpl implements INewsService{

	private final INewsDAO newsDAO = DaoProvider.getInstance().getNewsDAO();
	


	@Override
	public List<News> latestList(int count) throws ServiceException {
		
		try {
			return newsDAO.getLatestList(5);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<News> list() throws ServiceException {
		try {
			return newsDAO.getLatestList(5);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public News findById(int id) throws ServiceException {
		try {
			return newsDAO.fetchById(id);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void add(News news) throws ServiceException {
		try {
			System.out.println("add");
			newsDAO.addNews(news);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			throw new ServiceException(e);
		}
	}

	
	@Override
	public void delete(int id) throws ServiceException{
		try {
			newsDAO.deleteNews(id);
		}catch (DaoException e) {
			throw new ServiceException(e);
		}
		
	}

	@Override
	public void update(int id) {
		// TODO Auto-generated method stub
		
	}

}
