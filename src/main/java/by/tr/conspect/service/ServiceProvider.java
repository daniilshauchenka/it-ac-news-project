package by.tr.conspect.service;

import by.tr.conspect.service.INewsService;
import by.tr.conspect.service.IUserService;

import by.tr.conspect.service.impl.NewsServiceImpl;
import by.tr.conspect.service.impl.UserServiceImpl;

public final class ServiceProvider {
	private static final ServiceProvider instance = new ServiceProvider();

	private final IUserService userService = new UserServiceImpl();
	private final INewsService newsService = new NewsServiceImpl();

	public IUserService getUserService() {
		return userService;
	}

	public INewsService getNewsService() {
		return newsService;
	}

	public static ServiceProvider getInstance() {
		return instance;
	}

}
