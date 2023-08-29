package by.htp.ex.service;

import java.util.List;

import by.htp.ex.bean.User;
import by.htp.ex.service.exception.ServiceException;

public interface IUserService {
	List<User> getList(int start, int count)  throws ServiceException;
	User authorization(String login, String password) throws ServiceException;
	boolean registration(User user) throws ServiceException;
	User getUserById(int id) throws ServiceException;
	int getUsersQuantity() throws ServiceException;
}
