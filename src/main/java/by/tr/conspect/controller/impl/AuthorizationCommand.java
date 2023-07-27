package by.tr.conspect.controller.impl;

import java.io.IOException;
import java.sql.SQLException;

import by.tr.conspect.bean.User;
import by.tr.conspect.controller.Command;
import by.tr.conspect.service.ServiceException;
import by.tr.conspect.service.ServiceProvider;
import by.tr.conspect.service.IUserService;
import by.tr.conspect.service.impl.UserServiceImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthorizationCommand implements Command {

	private IUserService userService = ServiceProvider.getInstance().getUserService();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String password = request.getParameter("password");
System.out.println("AuthorizationCommand");
		// 1 Извлечение параметров
		// 2 Базовая проверка параметров - если праметры не дошли, то и дальше
		// (сервисы), дергать нечего

		
		User user;
		try {
			user = userService.authorization(login, password);
			
			if (user == null) {
				request.setAttribute("error", "Problem with authorization!!!");

				RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
				requestDispatcher.forward(request, response);
				return;
			}
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
			requestDispatcher.forward(request, response);
		} catch (ServiceException e) {
			// logging
			request.setAttribute("error", "Problem with authorization!!!");

			RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
			requestDispatcher.forward(request, response);
		}
		
		// 4 Решаем, какой ответ отправляем пользователю, и кто этот ответ будет
		// формировать
		
	
	}

}
