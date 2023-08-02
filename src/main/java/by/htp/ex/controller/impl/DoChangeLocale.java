package by.htp.ex.controller.impl;

import java.io.IOException;

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
public class DoChangeLocale implements Command {

	private final INewsService service = ServiceProvider.getInstance().getNewsService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String prevUrl = (String) request.getAttribute(RequestParam.JSP_PREV_QUERY_PARAM_NAME);
		response.sendRedirect(prevUrl);
		
	}

}
