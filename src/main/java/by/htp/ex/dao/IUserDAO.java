package by.htp.ex.dao;

import by.htp.ex.bean.User;

public interface IUserDAO {
	
	User authorization(String login, String password) throws DaoException;
	boolean registration(User user) throws DaoException;

}
