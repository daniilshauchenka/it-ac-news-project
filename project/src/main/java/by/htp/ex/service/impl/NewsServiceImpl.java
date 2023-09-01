package by.htp.ex.service.impl;

import java.util.List;

import by.htp.ex.bean.News;
import by.htp.ex.dao.DaoProvider;
import by.htp.ex.dao.INewsDAO;
import by.htp.ex.dao.exception.DaoException;
import by.htp.ex.service.INewsService;
import by.htp.ex.service.exception.ServiceException;

public class NewsServiceImpl implements INewsService{

	private final INewsDAO newsDAO = DaoProvider.getInstance().getNewsDAO();
	

	@Override
	public List<News> getLatestList(int offset,int limit) throws ServiceException {
		
		try {
			return newsDAO.getLatestList(offset, limit);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<News> getDeletedList(int offset,int limit) throws ServiceException {
		
		try {
			return newsDAO.getDeletedList(offset, limit);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}


	@Override
	public News findById(int id) throws ServiceException {
		try {
			News news = newsDAO.fetchById(id);
			return news;
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
	public void update(int id, News news) throws ServiceException {
		try {
			newsDAO.updateNews(id, news);
		}catch (DaoException e) {
			throw new ServiceException(e);
		}
		
	}



	@Override
	public int getNewsQuantity() throws ServiceException {
		
		try {
			return newsDAO.getNewsQuantity();
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

}
