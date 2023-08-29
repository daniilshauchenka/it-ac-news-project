package by.htp.ex.controller.impl;

import java.io.IOException;

import by.htp.ex.controller.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public final class GoToEditUserProfile implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute(RequestParam.JSP_USER_INFO_PARAM_NAME,
				request.getSession().getAttribute(RequestParam.JSP_USER_INFO_PARAM_NAME));
		request.setAttribute(RequestParam.JSP_USER_STATUS_PARAM_NAME, RequestParam.USER_STATUS_ACTIVE);
		request.setAttribute(RequestParam.JSP_PRESENTATION_PARAM_NAME, RequestParam.USER_PROFILE);
		request.setAttribute(RequestParam.JSP_ACTION_PARAM_NAME, RequestParam.EDIT_ACTION);
		request.getRequestDispatcher("WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);
	}
}
