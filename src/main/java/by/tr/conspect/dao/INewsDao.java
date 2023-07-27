package by.tr.conspect.dao;

import by.tr.conspect.bean.News;

public interface INewsDao {
	
	boolean add(News news) throws DaoException;

}
