package by.htp.ex.controller.impl;

import java.io.IOException;


import by.htp.ex.controller.Command;
import by.htp.ex.service.ServiceProvider;
import by.htp.ex.service.exception.ServiceException;
import by.htp.ex.service.INewsService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public final class DoDeleteNews implements Command {

	private final INewsService service = ServiceProvider.getInstance().getNewsService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int newsId = Integer.parseInt(request.getParameter(RequestParam.JSP_NEWS_ID_PARAM_NAME).toString());
			service.delete(newsId);
		} catch (IllegalArgumentException | ServiceException e) {
			request.setAttribute(RequestParam.JSP_ERROR_PARAM_NAME, e.getCause().getMessage());
			request.setAttribute(RequestParam.JSP_PRESENTATION_PARAM_NAME, RequestParam.ERROR_PAGE);
			request.getRequestDispatcher("controller?command=go_to_error_page").forward(request, response);
		}
		response.sendRedirect("controller?command=go_to_news_list");
	}
}
