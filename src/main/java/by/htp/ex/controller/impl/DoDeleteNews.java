package by.htp.ex.controller.impl;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;

import by.htp.ex.bean.News;
import by.htp.ex.bean.User;
import by.htp.ex.controller.Command;
import by.htp.ex.exception.ServiceException;
import by.htp.ex.service.ServiceProvider;
import by.htp.ex.service.INewsService;
import by.htp.ex.service.impl.NewsServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import by.htp.ex.util.validation.UserDataValidation;
import by.htp.ex.util.validation.ValidationProvider;
public class DoDeleteNews implements Command {

	private final INewsService service = ServiceProvider.getInstance().getNewsService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	
		
		try {
			int newsId = Integer.parseInt(request.getParameter(RequestParam.JSP_NEWS_ID_PARAM_NAME).toString());
			 service.delete(newsId);
			 
		}catch(IllegalArgumentException | ServiceException e) {
			//log
			e.printStackTrace();
		}
	
		response.sendRedirect("controller?command=go_to_news_list");
		
	}

}
