package by.htp.ex.dao;

import java.util.List;

import by.htp.ex.bean.User;
import by.htp.ex.exception.DaoException;

public interface IUserDAO {
	
	User authorization(String login, String password) throws DaoException;
	boolean registration(User user) throws DaoException;
	List<User> getList(int start, int count) throws DaoException;
	User getUserById(int id) throws DaoException;
}
