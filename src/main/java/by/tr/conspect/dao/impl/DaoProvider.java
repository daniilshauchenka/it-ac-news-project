package by.tr.conspect.dao.impl;

import by.tr.conspect.dao.IUserAccountDao;
import by.tr.conspect.dao.INewsDao;

public final class DaoProvider {
	private static final DaoProvider instance = new DaoProvider();

	private DaoProvider() {
	}

	private final INewsDao newsDao = new SQLNewsDao();
	private final IUserAccountDao userAccountDao = new SQLUserDao();

	public INewsDao getNewsDao() {
		return newsDao;
	}

	public IUserAccountDao getAccauntDao() {
		return userAccountDao;
	}

	public static DaoProvider getInstance() {
		return instance;
	}
}
