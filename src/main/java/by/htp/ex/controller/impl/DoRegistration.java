package by.htp.ex.controller.impl;

import java.io.IOException;

import org.mindrot.jbcrypt.BCrypt;

import by.htp.ex.controller.Command;
import by.htp.ex.bean.User;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.IUserService;
import by.htp.ex.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DoRegistration implements Command {

	private final IUserService userService = ServiceProvider.getInstance().getUserService();

	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String email = request.getParameter("email");
		String login = request.getParameter("login");
		String password = request.getParameter("password");		
		String passwordRepeat = request.getParameter("password-repeat");	
		//ln(password+" "+passwordRepeat);
		
		if (!passwordRepeat.equals(password)) {
			request.getSession(true).setAttribute("message", "Entered passwords mismatch!");
			response.sendRedirect("controller?command=go_to_base_page");
			return;
		}
		String passwordHash = BCrypt.hashpw(password, BCrypt.gensalt());
		password = null;
		passwordRepeat = null;
		User newUser = new User(login, email, passwordHash, name, surname);
		try {
		
			if(userService.registration(newUser)) {
				//ln("REGISTERED");
				String role = newUser.getRoleName();
				//ln(role);
				request.getSession(true).setAttribute(RequestParam.JSP_USER_STATUS_PARAM_NAME, RequestParam.USER_STATUS_ACTIVE);
				request.getSession().setAttribute(RequestParam.JSP_USER_ROLE_PARAM_NAME, role);
				request.getSession().setAttribute(RequestParam.JSP_USER_INFO_PARAM_NAME, newUser);
			
				request.getSession().setAttribute(RequestParam.JSP_MESSAGE_PARAM_NAME, "Registered!");
				
			}
			//ln("DoRegistration redirecting to controller?command=go_to_base_page");
			
			response.sendRedirect("controller?command=go_to_base_page");
			return;
		} catch (ServiceException e) {
			//ln("reg error");
			e.printStackTrace();
			request.getSession().setAttribute("errorMessage", e.getMessage());
			response.sendRedirect("controller?command=go_to_registration_page");
			
			
		}
	
	}

}
