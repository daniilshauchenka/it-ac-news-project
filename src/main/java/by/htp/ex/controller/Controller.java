package by.htp.ex.controller;

import java.io.IOException;
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
		String commandName = request.getParameter("command");
//ln("CONTROLLER: "+commandName);
		Command command = provider.getCommand(commandName);
		//ln("found command: " + command.toString());
		command.execute(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
