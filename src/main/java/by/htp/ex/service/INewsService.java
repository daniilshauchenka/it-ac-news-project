package by.htp.ex.service;

import java.util.List;

import by.htp.ex.bean.News;
import by.htp.ex.exception.ServiceException;

public interface INewsService {
 
  List<News> getLatestList(int offset,int limit)  throws ServiceException;
  News findById(int id) throws ServiceException;
  void add(News news) throws ServiceException;
  void delete(int id) throws ServiceException;
  void update(int id, News news) throws ServiceException;
  int getNewsQuantity() throws ServiceException;
}

