package by.tr.conspect.service;


import by.tr.conspect.bean.User;

public interface IUserService {
	
	User authorization(String login, String password) throws ServiceException;
	boolean registration(User newUser) throws ServiceException;

}
