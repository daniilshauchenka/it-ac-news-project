package by.htp.ex.controller.impl;

import java.io.IOException;
import by.htp.ex.bean.User;
import by.htp.ex.controller.Command;
import by.htp.ex.service.IUserService;
import by.htp.ex.service.ServiceProvider;
import by.htp.ex.service.exception.ServiceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public final class GoToViewUser implements Command {

	private final IUserService userService = ServiceProvider.getInstance().getUserService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user;
		String id;
		id = request.getParameter("id");
		try {
			user = userService.getUserById(Integer.parseInt(id));
			request.setAttribute(RequestParam.JSP_SINGLE_USER_PARAM_NAME, user);
			request.setAttribute(RequestParam.JSP_PRESENTATION_PARAM_NAME, RequestParam.VIEW_USER);
			request.setAttribute(RequestParam.JSP_USER_INFO_PARAM_NAME,
					request.getSession(true).getAttribute(RequestParam.JSP_USER_INFO_PARAM_NAME));
			request.getRequestDispatcher("WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);
		} catch (ServiceException e) {
			request.setAttribute(RequestParam.JSP_ERROR_PARAM_NAME, e.getCause().getMessage());
			request.setAttribute(RequestParam.JSP_PRESENTATION_PARAM_NAME, RequestParam.ERROR_PAGE);
			request.getRequestDispatcher("controller?command=go_to_error_page").forward(request, response);
		}

	}

}
