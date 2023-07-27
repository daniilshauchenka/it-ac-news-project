package by.tr.conspect.dao.impl;

import java.sql.SQLException;

import by.tr.conspect.bean.News;
import by.tr.conspect.dao.DaoException;
import by.tr.conspect.dao.INewsDao;

public class SQLNewsDao implements INewsDao {

	@Override
	public boolean add(News news) throws DaoException{

		try {
			throw new SQLException();
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		
		//return false;
	}

}
