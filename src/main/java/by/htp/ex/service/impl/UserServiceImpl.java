package by.htp.ex.service.impl;

import by.htp.ex.bean.User;
import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.DaoProvider;
import by.htp.ex.dao.IUserDAO;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.IUserService;

public class UserServiceImpl implements IUserService {

	private final IUserDAO userDAO = DaoProvider.getInstance().getUserDao();
//	private final UserDataValidation userDataValidation = ValidationProvider.getIntsance().getUserDataVelidation();

	@Override
	public User authorization(String login, String password) throws ServiceException {
		
		/*
		 * if(!userDataValidation.checkAUthData(login, password)) { throw new
		 * ServiceException("login ...... "); }
		 */
		try {
			//ln("service");
			User user = userDAO.authorization(login, password);
			//ln("service end");
			return user;
		}
	catch(DaoException e)
	{
		//ln("serice error");
		throw new ServiceException(e);
	}

	}

	@Override
	public boolean registration(User user) throws ServiceException {
		try {
			//ln("reg service");
			boolean result = userDAO.registration(user);
			//ln("service end");
			return result;
				}
	catch(DaoException e)
	{
		throw new ServiceException(e.getMessage());
	}
	}
	@Override
	public User getUserById(int id) {
		return null;
	}

	@Override
	public User getCurrentUser() {
		// TODO Auto-generated method stub
		return null;
	}

}
