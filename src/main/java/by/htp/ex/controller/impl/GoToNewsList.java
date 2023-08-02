package by.htp.ex.controller.impl;

import java.io.IOException;
import java.util.List;

import by.htp.ex.bean.News;
import by.htp.ex.controller.Command;
import by.htp.ex.exception.ServiceException;
import by.htp.ex.service.INewsService;
import by.htp.ex.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToNewsList implements Command {
	
	private final INewsService newsService = ServiceProvider.getInstance().getNewsService();
	final static int NEWS_PER_PAGE = 5;

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<News> newsList;
		try {
			int newsQuantity = newsService.getNewsQuantity();
			System.out.println(newsQuantity);
			int currentPage;
			try {
				currentPage = Integer.parseInt(request.getParameter(RequestParam.JSP_CURRENT_PAGE_PARAM_NAME).toString());
			} catch (IllegalArgumentException | NullPointerException e) {
				currentPage = 1;
			
			}
			int pagesQuantity = (int) Math.ceil(newsQuantity * 1.0 / NEWS_PER_PAGE);
			System.out.println("page " + currentPage + " of " + pagesQuantity);
		
			newsList = newsService.getLatestList((currentPage-1) * NEWS_PER_PAGE, NEWS_PER_PAGE);;
			request.setAttribute(RequestParam.JSP_NEWS_LIST_PARAM_NAME, newsList);
			request.setAttribute(RequestParam.JSP_TOTAL_PAGES_PARAM_NAME, pagesQuantity);
			request.setAttribute(RequestParam.JSP_CURRENT_PAGE_PARAM_NAME, currentPage);
			request.setAttribute(RequestParam.JSP_PRESENTATION_PARAM_NAME, RequestParam.NEWS_LIST);
			request.setAttribute(RequestParam.JSP_USER_STATUS_PARAM_NAME, request.getSession(true).getAttribute(RequestParam.JSP_USER_STATUS_PARAM_NAME));
		

			request.getRequestDispatcher("WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);
		} catch (ServiceException e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", e.getMessage());
			request.setAttribute(RequestParam.JSP_PRESENTATION_PARAM_NAME, RequestParam.ERROR_PAGE);
			request.getRequestDispatcher("controller?command=go_to_error_page").forward(request, response);
			return;
		
		}
		
	}

}
