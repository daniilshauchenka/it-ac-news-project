package by.tr.conspect.service;

import java.util.List;

import by.tr.conspect.bean.News;

public interface INewsService {
	
	void add(News news) throws ServiceException;	
	News find(int id) throws ServiceException;
	public List<News> getLatestNews(int count);
}
