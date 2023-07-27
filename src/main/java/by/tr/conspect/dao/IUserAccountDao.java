package by.tr.conspect.dao;

import by.tr.conspect.bean.User;

public interface IUserAccountDao {
	
	boolean registration(User newUser) throws DaoException;
	
	boolean authorization(User newUser) throws DaoException;
	
}
