package by.htp.ex.controller.impl;

import java.io.IOException;

import by.htp.ex.bean.News;
import by.htp.ex.bean.User;
import by.htp.ex.controller.Command;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.ServiceProvider;
import by.htp.ex.service.INewsService;
import by.htp.ex.service.impl.NewsServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import by.htp.ex.util.validation.UserDataValidation;
import by.htp.ex.util.validation.ValidationProvider;
public class DoAddNews implements Command {

	private final INewsService service = ServiceProvider.getInstance().getNewsService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//User user = (User)request.getSession().getAttribute("userInfo");
		
		String title = request.getParameter(RequestParam.JSP_NEWS_TITLE_PARAM_NAME);
		String brief = request.getParameter(RequestParam.JSP_NEWS_BRIEF_PARAM_NAME);
		String content = request.getParameter(RequestParam.JSP_NEWS_CONTENT_PARAM_NAME);
		String imagePath = request.getParameter(RequestParam.JSP_NEWS_IMAGE_PATH_PARAM_NAME);
		int userId = -1;
		try {
			userId = Integer.parseInt(request.getParameter("userId").toString().trim());
		}catch (NumberFormatException e) {
			e.printStackTrace();
		}
		News news = new News(title, brief, content, content, imagePath, userId);
		System.out.println(news);
		try {
			service.add(news);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("controller?command=go_to_news_list");
		
	}

}
