package by.tr.conspect.controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import by.tr.conspect.bean.User;
import by.tr.conspect.util.Constants;

public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final CommandProvider provider = CommandProvider.getInstance();
	
	public Controller() {
		super();
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String commandName = request.getParameter(Constants.COMMAND_PARAMETER_NAME);
		System.out.println("GET " + commandName);
		response.setContentType("text/html");

		String locale = request.getParameter(Constants.LOCALE_PARAMETER_NAME);
		if(locale!=null) {
			request.getSession(true).setAttribute(Constants.LOCALE_PARAMETER_NAME, locale);
		}
		
		Command command = provider.getCommand(commandName);
		
		
		command.execute(request, response);		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");

		String commandName = request.getParameter(Constants.COMMAND_PARAMETER_NAME);
		System.out.println("POST " + commandName);
		
		String locale = request.getParameter(Constants.LOCALE_PARAMETER_NAME);
		if(locale!=null) {
			request.getSession(true).setAttribute(Constants.LOCALE_PARAMETER_NAME, locale);
		}
		
		
		Command command = provider.getCommand(commandName);
		
		command.execute(request, response);		
	
	}
	


}
