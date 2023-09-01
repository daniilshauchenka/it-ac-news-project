package by.htp.ex.service.impl;

import java.util.List;

import by.htp.ex.bean.User;
import by.htp.ex.dao.DaoProvider;
import by.htp.ex.dao.IUserDAO;
import by.htp.ex.dao.exception.DaoException;
import by.htp.ex.service.IUserService;
import by.htp.ex.service.exception.ServiceException;

public class UserServiceImpl implements IUserService {

	private final IUserDAO userDAO = DaoProvider.getInstance().getUserDao();

	@Override
	public User authorization(String login, String password) throws ServiceException {

		try {
			User user = userDAO.authorization(login, password);
			return user;
		} catch (DaoException e) {
			throw new ServiceException(e);
		}

	}

	@Override
	public boolean registration(User user) throws ServiceException {
		try {
			boolean result = userDAO.registration(user);
			return result;
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<User> getList(int offset, int limit) throws ServiceException {
		try {
			return userDAO.getList(offset, limit);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}

	}

	@Override
	public User getUserById(int id) throws ServiceException {
		try {
			return userDAO.getUserById(id);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}

	}

	@Override
	public int getUsersQuantity() throws ServiceException {
		try {
			return userDAO.getUsersQuantity();
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

}
