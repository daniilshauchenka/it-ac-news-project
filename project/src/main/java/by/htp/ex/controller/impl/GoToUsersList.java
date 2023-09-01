package by.htp.ex.controller.impl;

import java.io.IOException;
import java.util.List;
import by.htp.ex.bean.User;
import by.htp.ex.controller.Command;
import by.htp.ex.service.IUserService;
import by.htp.ex.service.ServiceProvider;
import by.htp.ex.service.exception.ServiceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public final class GoToUsersList implements Command {

	private final IUserService userService = ServiceProvider.getInstance().getUserService();
	final static int USERS_PER_PAGE = 4;

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			if (!(request.getSession(true).getAttribute(RequestParam.JSP_USER_STATUS_PARAM_NAME)
					.equals(RequestParam.USER_STATUS_ACTIVE))) {
				request.setAttribute(RequestParam.JSP_ERROR_PARAM_NAME, "error.user_is_offline");
				request.setAttribute(RequestParam.JSP_PRESENTATION_PARAM_NAME, RequestParam.ERROR_PAGE);
				request.getRequestDispatcher("controller?command=go_to_error_page").forward(request, response);
				return;
			}
		} catch (NullPointerException e) {
			request.setAttribute(RequestParam.JSP_ERROR_PARAM_NAME, "error.user_is_offline");
			request.setAttribute(RequestParam.JSP_PRESENTATION_PARAM_NAME, RequestParam.ERROR_PAGE);
			request.getRequestDispatcher("controller?command=go_to_error_page").forward(request, response);
			return;
		}

		List<User> usersList;
		try {
			int usersQuantity = userService.getUsersQuantity();

			int currentPage = 1;
			try {
				currentPage = Integer
						.parseInt(request.getParameter(RequestParam.JSP_CURRENT_PAGE_PARAM_NAME).toString());
			} catch (IllegalArgumentException | NullPointerException e) {
			}
			int pagesQuantity = (int) Math.ceil(usersQuantity * 1.0 / USERS_PER_PAGE);
			usersList = userService.getList((currentPage - 1) * USERS_PER_PAGE, USERS_PER_PAGE);
			request.setAttribute(RequestParam.JSP_TOTAL_PAGES_PARAM_NAME, pagesQuantity);
			request.setAttribute(RequestParam.JSP_CURRENT_PAGE_PARAM_NAME, currentPage);
			request.setAttribute(RequestParam.JSP_USERS_LIST_PARAM_NAME, usersList);
			request.setAttribute(RequestParam.JSP_PRESENTATION_PARAM_NAME, RequestParam.USERS_LIST);
			request.setAttribute(RequestParam.JSP_USER_STATUS_PARAM_NAME,
					request.getSession(true).getAttribute(RequestParam.JSP_USER_STATUS_PARAM_NAME));
			request.getRequestDispatcher("WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);
		} catch (ServiceException e) {
			request.setAttribute(RequestParam.JSP_ERROR_PARAM_NAME, e.getCause().getMessage());
			request.setAttribute(RequestParam.JSP_PRESENTATION_PARAM_NAME, RequestParam.ERROR_PAGE);
			request.getRequestDispatcher("controller?command=go_to_error_page").forward(request, response);
			return;
		}
	}
}
