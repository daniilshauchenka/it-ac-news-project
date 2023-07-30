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

public class GoToEditNews implements Command {
	private final IUserService userService = ServiceProvider.getInstance().getUserService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		request.setAttribute(RequestParam.JSP_USER_INFO_PARAM_NAME, request.getSession().getAttribute(RequestParam.JSP_USER_INFO_PARAM_NAME));
		request.setAttribute(RequestParam.JSP_USER_STATUS_PARAM_NAME, RequestParam.USER_STATUS_ACTIVE);
		request.setAttribute(RequestParam.JSP_PRESENTATION_PARAM_NAME, RequestParam.VIEW_NEWS);
		request.setAttribute(RequestParam.JSP_ACTION_PARAM_NAME, RequestParam.EDIT_ACTION);
		request.getRequestDispatcher("WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);

		
		
//		try {
//			//int userId = Integer.parseInt(request.getSession(true).getAttribute("userId").toString());
//			//user = userService.getUserById(userId);
//			
//		}catch (ServiceException e) {
//			
//		}
	
	}

}
