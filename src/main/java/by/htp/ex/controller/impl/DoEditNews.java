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

public final class DoEditNews implements Command {

	private final INewsService service = ServiceProvider.getInstance().getNewsService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = -1;
		try {
			id = Integer.parseInt(request.getParameter(RequestParam.JSP_NEWS_ID_PARAM_NAME));
		} catch (IllegalArgumentException e) {
			request.setAttribute(RequestParam.JSP_ERROR_PARAM_NAME, "error.wrong_news_id");
			request.setAttribute(RequestParam.JSP_PRESENTATION_PARAM_NAME, RequestParam.ERROR_PAGE);
			request.getRequestDispatcher("controller?command=go_to_error_page").forward(request, response);
		}
		String title = request.getParameter(RequestParam.JSP_NEWS_TITLE_PARAM_NAME);
		String brief = request.getParameter(RequestParam.JSP_NEWS_BRIEF_PARAM_NAME);
		String content = request.getParameter(RequestParam.JSP_NEWS_CONTENT_PARAM_NAME);
		String imagePath = request.getParameter(RequestParam.JSP_NEWS_IMAGE_PATH_PARAM_NAME);

		News news = new News();
		news.setIdNews(id);
		news.setTitle(title);
		news.setBriefNews(brief);
		news.setContent(content);
		news.setImagePath(imagePath);
		try {
			service.update(news.getIdNews(), news);
		} catch (ServiceException e) {
			request.setAttribute(RequestParam.JSP_ERROR_PARAM_NAME, e.getCause().getMessage());
			request.setAttribute(RequestParam.JSP_PRESENTATION_PARAM_NAME, RequestParam.ERROR_PAGE);
			request.getRequestDispatcher("controller?command=go_to_error_page").forward(request, response);
		}
		response.sendRedirect("controller?command=go_to_view_news&id=" + news.getIdNews());
	}
}
