package by.htp.ex.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;

import by.htp.ex.controller.impl.RequestParam;
import by.htp.ex.dao.pool.ConnectionPool;
import by.htp.ex.dao.pool.ConnectionPoolException;
import jakarta.servlet.RequestDispatcher;
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
			
			//e.printStackTrace();
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String commandName = request.getParameter("command");
		Command command = provider.getCommand(commandName);
		request.setAttribute(RequestParam.JSP_PREV_QUERY_PARAM_NAME, request.getHeader("referer"));
		command.execute(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		Iterator<String> attrs = request.getSession(true).getAttributeNames().asIterator();
//		while (attrs.hasNext()) {
//			String attr = attrs.next();
//			System.out.println(attr.toString() + " : " + request.getSession().getAttribute(attr));
//		}

		String commandName = request.getParameter("command");
		request.setAttribute(RequestParam.JSP_PREV_QUERY_PARAM_NAME, request.getAttribute("jakarta.servlet.forward.query_string"));
		Command command = provider.getCommand(commandName);
		command.execute(request, response);
	}

}
