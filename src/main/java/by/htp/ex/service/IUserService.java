package by.htp.ex.service;

import by.htp.ex.bean.User;

public interface IUserService {
	
	User authorization(String login, String password) throws ServiceException;
	boolean registration(User user) throws ServiceException;
	User getUserById(int id);
	User getCurrentUser();	
}
