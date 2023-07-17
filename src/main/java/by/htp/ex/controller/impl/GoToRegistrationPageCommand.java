package by.htp.ex.controller.impl;

import java.io.IOException;
import java.util.Locale;

import by.htp.ex.controller.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToRegistrationPageCommand implements Command {
	private final static String JSP_REGISTRATION_PARAM = "registration";
	private final static String JSP_ACTION_PARAM = "action";
	private final static String JSP_LOCAL_PARAM = "local";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute(JSP_ACTION_PARAM, JSP_REGISTRATION_PARAM);
		request.setAttribute(JSP_LOCAL_PARAM, Locale.getDefault());
		request.getRequestDispatcher("/WEB-INF/pages/tiles/registration.jsp").forward(request, response);

	}

}
