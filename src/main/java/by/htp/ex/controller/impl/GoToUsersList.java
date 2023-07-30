package by.htp.ex.controller.impl;

import java.io.IOException;
import java.util.List;

import by.htp.ex.bean.News;
import by.htp.ex.bean.User;
import by.htp.ex.controller.Command;
import by.htp.ex.exception.ServiceException;
import by.htp.ex.service.INewsService;
import by.htp.ex.service.IUserService;
import by.htp.ex.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToUsersList implements Command {
	
	private final IUserService userService = ServiceProvider.getInstance().getUserService();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<User> usersList;
		try {
			usersList = userService.getList(0,30);
			request.setAttribute(RequestParam.JSP_USERS_LIST_PARAM_NAME, usersList);
			request.setAttribute(RequestParam.JSP_PRESENTATION_PARAM_NAME, RequestParam.USERS_LIST);
			request.setAttribute(RequestParam.JSP_USER_STATUS_PARAM_NAME, request.getSession(true).getAttribute(RequestParam.JSP_USER_STATUS_PARAM_NAME));
			

			request.getRequestDispatcher("WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
