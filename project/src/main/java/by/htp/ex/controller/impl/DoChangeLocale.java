package by.htp.ex.controller.impl;

import java.io.IOException;
import by.htp.ex.controller.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public final class DoChangeLocale implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String prevUrl = (String) request.getAttribute(RequestParam.JSP_PREV_QUERY_PARAM_NAME);
		response.sendRedirect(prevUrl);
	}
}
