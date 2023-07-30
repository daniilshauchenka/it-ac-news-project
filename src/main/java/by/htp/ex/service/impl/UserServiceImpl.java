package by.htp.ex.service.impl;

import java.util.List;

import by.htp.ex.bean.User;
import by.htp.ex.dao.DaoProvider;
import by.htp.ex.dao.IUserDAO;
import by.htp.ex.exception.DaoException;
import by.htp.ex.exception.ServiceException;
import by.htp.ex.service.IUserService;
import by.htp.ex.util.validation.UserDataValidation;
import by.htp.ex.util.validation.ValidationProvider;

public class UserServiceImpl implements IUserService {

	private final IUserDAO userDAO = DaoProvider.getInstance().getUserDao();
	private final UserDataValidation userDataValidation = ValidationProvider.getInstance().getUserValidator();

	@Override
	public User authorization(String login, String password) throws ServiceException {

		try {

			User user = userDAO.authorization(login, password);

			return user;
		} catch (DaoException e) {

			throw new ServiceException(e.getMessage());
		}

	}

	@Override
	public boolean registration(User user) throws ServiceException {
		try {
			
			boolean result = userDAO.registration(user);
			
			return result;
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}



	@Override
	public List<User> getList(int start, int count) throws ServiceException {
		try {
			//TODO
			return userDAO.getList(0, 30);
		} catch(DaoException e) {
			throw new ServiceException(e.getMessage());
		}
		
	}

	@Override
	public User getUserById(int id) throws ServiceException {
		try {
			return userDAO.getUserById(id);
		}catch(DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	
	}

}
