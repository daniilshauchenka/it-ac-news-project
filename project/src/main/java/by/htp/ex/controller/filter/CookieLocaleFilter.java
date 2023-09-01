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


public final class CookieLocaleFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse res = (HttpServletResponse) response;

		if (request.getParameter(RequestParam.URL_COOKIE_LOCALE_PARAM_NAME) != null) {
			Cookie locale = new Cookie("locale", request.getParameter(RequestParam.URL_COOKIE_LOCALE_PARAM_NAME));
			res.addCookie(locale);
		}
		chain.doFilter(request, response);
	}
}
