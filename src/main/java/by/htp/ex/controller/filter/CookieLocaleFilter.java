package by.htp.ex.controller.filter;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

import com.oracle.wls.shaded.org.apache.xml.utils.URI;

import by.htp.ex.controller.impl.RequestParam;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Locale;
public class CookieLocaleFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		//System.out.println("Filter  started...");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse res = (HttpServletResponse) response;
		HttpServletRequest req = (HttpServletRequest) request;

		if (request.getParameter(RequestParam.URL_COOKIE_LOCALE_PARAM_NAME) != null) {
			Locale locale = new Locale(request.getParameter(RequestParam.URL_COOKIE_LOCALE_PARAM_NAME));
			
			Cookie localeShort = new Cookie("locale", request.getParameter("cookieLocale"));
			res.addCookie(localeShort);
//			Cookie localeFull = new Cookie("localeFull", locale.getLanguage() + "_"+locale.getCountry());
//			res.addCookie(localeFull);
		}
		
	
//			String uri = req.getRequestURI();
//			System.out.println(uri);
//			uri = uri + "?";
//			Enumeration<String> parameterNames = req.getParameterNames();
//			
//			while (parameterNames.hasMoreElements()) {
//				String paramName = parameterNames.nextElement();
//				if (paramName.equals(RequestParam.URL_COOKIE_LOCALE_PARAM_NAME)) {
//					break;
//				}
//				uri = uri + paramName + "=";
//				String[] paramValues = req.getParameterValues(paramName);
//				System.out.print(uri);
//				uri = uri+paramValues[0];
//				for (int i = 1; i < paramValues.length; i++) {
//					String paramValue = paramValues[i];
//					System.out.print(paramValue + ", ");
//				}
//				if(parameterNames.hasMoreElements()) {
//					uri = uri + "&";
//				}
//				System.out.println(uri);
//			}
//			res.sendRedirect(uri);
//			return;

//			String uri = req.getRequestURI();
//			System.out.println(uri);
//			String[] uriParts = uri.split("[#?]");
//			String path = uriParts[0];
//			String rest = uri.substring(uriParts[0].length());
//			String params[] = rest.split("&");
//			String newUri = path;
//		
//			for (String param : params) {
//				if (!param.contains("cookieLocale")) {
//					newUri = newUri  + param + "=" + req.getParameter(param)+"&";
//				}
//			}			
//			System.out.println(newUri);
//			res.sendRedirect(newUri);

		
		chain.doFilter(request, response);
	}
}
