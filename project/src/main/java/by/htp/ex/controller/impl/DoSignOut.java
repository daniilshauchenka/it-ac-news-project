package by.htp.ex.controller.impl;

import java.io.IOException;

import by.htp.ex.controller.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public final class DoSignOut implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession(true).setAttribute(RequestParam.JSP_USER_STATUS_PARAM_NAME,
				RequestParam.USER_STATUS_NOT_ACTIVE);
		request.getSession().setAttribute(RequestParam.JSP_USER_INFO_PARAM_NAME, null);
		request.getSession().setAttribute(RequestParam.JSP_USER_ROLE_PARAM_NAME, null);
		response.sendRedirect("index.jsp");
	}
}
