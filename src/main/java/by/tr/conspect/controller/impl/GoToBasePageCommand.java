package by.tr.conspect.controller.impl;

import java.io.IOException;
import java.util.List;

import by.tr.conspect.bean.News;
import by.tr.conspect.controller.Command;
import by.tr.conspect.service.INewsService;
import by.tr.conspect.service.ServiceException;
import by.tr.conspect.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToBasePageCommand implements Command{

	private final INewsService newsService = ServiceProvider.getInstance().getNewsService();

	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<News> latestNews;
		//try {
		System.out.println("trying to go to base page");
		
			latestNews = newsService.getLatestNews(5);
			request.setAttribute("news", latestNews);
			request.getSession(true).setAttribute("currentPage", request.getParameter("command"));
			//request.setAttribute("news", null);

			request.getRequestDispatcher("WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);
//		} catch (ServiceException e) {
//			// loggin - error
//			e.printStackTrace();
//		}
	}

}
