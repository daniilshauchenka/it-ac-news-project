package by.htp.ex.controller.filter;

import java.io.IOException;

import by.htp.ex.controller.impl.RequestParam;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Cookie;


public class CookieLocaleFilter implements Filter{
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("Filter  started...");
	}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse res = (HttpServletResponse) response;
		System.out.println("Filter  got request");
		if(request.getParameter("cookieLocale")!=null) {
			
			Cookie cookie = new Cookie("locale", request.getParameter("cookieLocale"));
			res.addCookie(cookie);
			
		}
	
	}
}


