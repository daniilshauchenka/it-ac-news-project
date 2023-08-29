package by.htp.ex.controller.impl;

import java.io.IOException;

import by.htp.ex.bean.News;
import by.htp.ex.controller.Command;
import by.htp.ex.service.ServiceProvider;
import by.htp.ex.service.exception.ServiceException;
import by.htp.ex.service.INewsService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public final class DoAddNews implements Command {

	private final INewsService service = ServiceProvider.getInstance().getNewsService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// User user = (User)request.getSession().getAttribute("userInfo");

		String title = request.getParameter(RequestParam.JSP_NEWS_TITLE_PARAM_NAME);
		String brief = request.getParameter(RequestParam.JSP_NEWS_BRIEF_PARAM_NAME);
		String content = request.getParameter(RequestParam.JSP_NEWS_CONTENT_PARAM_NAME);
		String imagePath = request.getParameter(RequestParam.JSP_NEWS_IMAGE_PATH_PARAM_NAME);
		int userId = -1;
		try {
			userId = Integer.parseInt(request.getParameter("userId").toString().trim());
		} catch (NumberFormatException e) {
			request.setAttribute(RequestParam.JSP_ERROR_PARAM_NAME, "error.user_id_not_found");
			request.setAttribute(RequestParam.JSP_PRESENTATION_PARAM_NAME, RequestParam.ERROR_PAGE);
			request.getRequestDispatcher("controller?command=go_to_error_page").forward(request, response);
		}
		News news = new News(title, brief, content, imagePath, userId);
		System.out.println(news);
		try {
			service.add(news);
		} catch (ServiceException e) {
			request.setAttribute(RequestParam.JSP_ERROR_PARAM_NAME, e.getCause().getMessage());
			request.setAttribute(RequestParam.JSP_PRESENTATION_PARAM_NAME, RequestParam.ERROR_PAGE);
			request.getRequestDispatcher("controller?command=go_to_error_page").forward(request, response);
		}
		response.sendRedirect("controller?command=go_to_news_list");

	}

}
