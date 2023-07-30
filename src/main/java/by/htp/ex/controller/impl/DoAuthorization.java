package by.htp.ex.controller.impl;

import java.io.IOException;

import by.htp.ex.bean.User;
import by.htp.ex.controller.Command;
import by.htp.ex.exception.ServiceException;
import by.htp.ex.service.ServiceProvider;
import by.htp.ex.service.IUserService;
import by.htp.ex.service.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import by.htp.ex.util.validation.UserDataValidation;
import by.htp.ex.util.validation.ValidationProvider;
public class DoAuthorization implements Command {

	private final IUserService service = ServiceProvider.getInstance().getUserService();

	private final UserDataValidation userDataValidation = ValidationProvider.getInstance().getUserValidator();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().removeAttribute(RequestParam.JSP_ERROR_PARAM_NAME);
		String login = request.getParameter(RequestParam.JSP_LOGIN_PARAM_NAME);
		String password = request.getParameter(RequestParam.JSP_PASSWORD_PARAM_NAME);
	
		User user;
		String redirectAddress = "controller?command=go_to_news_list";	
		try {
			if(!userDataValidation.isCorrectAuthData(login, password)) {
				throw new ServiceException("Incorrect login or password length!");
			}
			
			user = service.authorization(login, password);
			String role = user.getRoleName();
			request.getSession(true).setAttribute(RequestParam.JSP_USER_STATUS_PARAM_NAME, RequestParam.USER_STATUS_ACTIVE);
			request.getSession().setAttribute(RequestParam.JSP_USER_ROLE_PARAM_NAME, role);
			request.getSession().setAttribute(RequestParam.JSP_USER_INFO_PARAM_NAME, user);
		//	request.getSession().setAttribute(JSP_LOCALIZATION_PARAM, user.getLocale());
		//	request.getSession().setAttribute(JSP_LOCALE_PARAM, user.getLocale().getLanguage());
					
		} catch (ServiceException e) {
			redirectAddress = "controller?command=go_to_base_page";	
			e.printStackTrace();
			request.getSession().setAttribute(RequestParam.JSP_ERROR_PARAM_NAME, e.getMessage());
			
		}
	
		response.sendRedirect(redirectAddress);
		

	
	}

}
