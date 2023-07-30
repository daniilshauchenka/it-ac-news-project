package by.htp.ex.controller.impl;

import java.io.IOException;
import java.util.List;

import by.htp.ex.bean.News;
import by.htp.ex.bean.User;
import by.htp.ex.controller.Command;
import by.htp.ex.exception.ServiceException;
import by.htp.ex.service.IUserService;
import by.htp.ex.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToViewUser implements Command {
	
	private final IUserService userService = ServiceProvider.getInstance().getUserService();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user;
		
		String id;

		id = request.getParameter("id");
		
		try {
			user  = userService.getUserById(Integer.parseInt(id));
			System.out.println(user);
			request.setAttribute(RequestParam.JSP_SINGLE_USER_PARAM_NAME, user);
			request.setAttribute(RequestParam.JSP_PRESENTATION_PARAM_NAME, RequestParam.VIEW_USER);
			request.setAttribute(RequestParam.JSP_USER_INFO_PARAM_NAME, request.getSession(true).getAttribute(RequestParam.JSP_USER_INFO_PARAM_NAME));
			
			request.getRequestDispatcher("WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
