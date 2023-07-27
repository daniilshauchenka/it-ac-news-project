package by.tr.conspect.controller.impl;

import java.io.IOException;

import by.tr.conspect.bean.Role;
import by.tr.conspect.bean.User;
import by.tr.conspect.controller.Command;
import by.tr.conspect.service.IUserService;
import by.tr.conspect.service.ServiceException;
import by.tr.conspect.service.ServiceProvider;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RegistrationCommand implements Command {

	private final IUserService userService = ServiceProvider.getInstance().getUserService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String email = request.getParameter("email");
		String login = request.getParameter("login");
		String password = request.getParameter("password");		
		User newUser = new User(login,  password,  email,  name,  surname);
		try {
		
			if(userService.registration(newUser)) {
				System.out.println("REGISTERED");
				request.getSession(true).setAttribute("message", "Registered!");
			}
			System.out.println("redirecting to controller?command=go_to_base_page");
			
			response.sendRedirect("Controller?command=go_to_base_page");
		} catch (ServiceException e) {
			request.getSession(true).setAttribute("message", "Error!!!");
			response.sendRedirect("Controller?command=go_to_base_page");
			e.printStackTrace();
		}
		
	
	}

}
