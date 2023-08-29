package by.htp.ex.controller.impl;

import java.io.IOException;

import org.mindrot.jbcrypt.BCrypt;
import by.htp.ex.controller.Command;
import by.htp.ex.bean.User;
import by.htp.ex.service.IUserService;
import by.htp.ex.service.ServiceProvider;
import by.htp.ex.service.exception.ServiceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public final class DoRegistration implements Command {

	private final IUserService userService = ServiceProvider.getInstance().getUserService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String email = request.getParameter("email");
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String passwordRepeat = request.getParameter("password-repeat");

		if (!passwordRepeat.equals(password)) {
			request.getSession(true).setAttribute(RequestParam.JSP_AUTH_ERROR_PARAM_NAME, "error.passwords_mismatch");
			response.sendRedirect("controller?command=go_to_registration");
			return;
		}
		String passwordHash = BCrypt.hashpw(password, BCrypt.gensalt());
		password = null;
		passwordRepeat = null;
		User newUser = new User(login, email, passwordHash, name, surname);
		try {
			if (userService.registration(newUser)) {
				String role = newUser.getRoleName();
				request.getSession(true).setAttribute(RequestParam.JSP_USER_STATUS_PARAM_NAME,
						RequestParam.USER_STATUS_ACTIVE);
				request.getSession().setAttribute(RequestParam.JSP_USER_ROLE_PARAM_NAME, role);
				request.getSession().setAttribute(RequestParam.JSP_USER_INFO_PARAM_NAME, newUser);
				request.getSession().setAttribute(RequestParam.JSP_MESSAGE_PARAM_NAME, "Registered!");
			}
			response.sendRedirect("controller?command=go_to_base_page");
		} catch (ServiceException e) {
			request.getSession().setAttribute(RequestParam.JSP_AUTH_ERROR_PARAM_NAME, e.getCause().getMessage());
			response.sendRedirect("controller?command=go_to_registration_page");
		}
	}
}
