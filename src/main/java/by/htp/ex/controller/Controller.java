package by.htp.ex.controller;

import java.io.IOException;
import java.util.Enumeration;

import by.htp.ex.dao.pool.ConnectionPool;
import by.htp.ex.dao.pool.ConnectionPoolException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static ConnectionPool connectionPool = ConnectionPool.getInstance();

	private final CommandProvider provider = new CommandProvider();

	public Controller() {
		super();
		try {
			connectionPool.initConnectionPool();
		} catch (ConnectionPoolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Enumeration<String> attributes = request.getSession().getAttributeNames();
		while (attributes.hasMoreElements()) {
		    String attribute = (String) attributes.nextElement();
		   // System.out.println(attribute+" : "+request.getSession().getAttribute(attribute));
		}
		
		
		
		
		String commandName = request.getParameter("command");
		Command command = provider.getCommand(commandName);
		command.execute(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Enumeration<String> attributes = request.getSession().getAttributeNames();
		
		while (attributes.hasMoreElements()) {
		    String attribute = (String) attributes.nextElement();
		    //System.out.println(attribute+" : "+request.getSession().getAttribute(attribute));
		}
		String commandName = request.getParameter("command");
		Command command = provider.getCommand(commandName);
		command.execute(request, response);
	}

}
