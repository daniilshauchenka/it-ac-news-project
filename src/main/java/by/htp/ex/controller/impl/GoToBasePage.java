package by.htp.ex.controller.impl;

import java.io.IOException;
import java.util.List;

import by.htp.ex.bean.News;
import by.htp.ex.bean.User;
import by.htp.ex.controller.Command;
import by.htp.ex.service.INewsService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToBasePage implements Command{
	
	private final INewsService newsService = ServiceProvider.getInstance().getNewsService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		List<News> latestNews;
		try {
			latestNews = newsService.latestList(5);
			request.setAttribute(RequestParam.JSP_NEWS_LIST_PARAM_NAME, latestNews);
			//request.setAttribute("news", null);
			request.setAttribute(RequestParam.JSP_USER_STATUS_PARAM_NAME, request.getSession(true).getAttribute(RequestParam.JSP_USER_STATUS_PARAM_NAME));
			request.setAttribute(RequestParam.JSP_PRESENTATION_PARAM_NAME, RequestParam.NEWS_LIST);
			request.getRequestDispatcher("WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);
		} catch (ServiceException e) {
			
			e.printStackTrace();
		}
		
		
	}

}
