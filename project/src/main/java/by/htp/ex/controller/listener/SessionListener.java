package by.htp.ex.controller.listener;

import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {
	private final static String JSP_USER_PARAM = "user";
	private final static String JSP_NOT_ACTIVE_PARAM = "not active";

	@Override
	public void sessionCreated(HttpSessionEvent event) {
		event.getSession().setAttribute(JSP_USER_PARAM, JSP_NOT_ACTIVE_PARAM);
	}
}
