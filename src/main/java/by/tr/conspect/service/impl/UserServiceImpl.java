package by.tr.conspect.service.impl;

import java.sql.SQLException;


import by.tr.conspect.bean.User;
import by.tr.conspect.dao.IUserAccountDao;
import by.tr.conspect.dao.DaoException;
import by.tr.conspect.dao.impl.DaoProvider;
import by.tr.conspect.service.ServiceException;
import by.tr.conspect.service.IUserService;

public class UserServiceImpl implements IUserService {
	
	private final IUserAccountDao userDao = DaoProvider.getInstance().getAccauntDao();
	
	


	@Override
	public User authorization(String login, String password) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean registration(User newUser) throws ServiceException {
		try {
			if (userDao.registration(newUser)) {
				return true;
			}
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}

