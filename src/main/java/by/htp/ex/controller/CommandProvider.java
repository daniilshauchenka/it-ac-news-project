package by.htp.ex.controller;

import java.util.HashMap;
import java.util.Map;

import by.htp.ex.controller.impl.DoAddNews;
import by.htp.ex.controller.impl.DoAuthorization;
import by.htp.ex.controller.impl.DoDeleteNews;
import by.htp.ex.controller.impl.DoRegistration;
import by.htp.ex.controller.impl.DoSignOut;
import by.htp.ex.controller.impl.DoUpdateUser;
import by.htp.ex.controller.impl.GoToAddNews;
import by.htp.ex.controller.impl.GoToBasePage;
import by.htp.ex.controller.impl.GoToEditUserProfile;
import by.htp.ex.controller.impl.GoToNewsList;
import by.htp.ex.controller.impl.GoToRegistrationPage;
import by.htp.ex.controller.impl.GoToUserProfile;
import by.htp.ex.controller.impl.GoToUsersList;
import by.htp.ex.controller.impl.GoToViewNews;
import by.htp.ex.controller.impl.GoToViewUser;

public class CommandProvider {
	private Map<CommandName, Command> commands = new HashMap<>();
	
	public CommandProvider() {
		commands.put(CommandName.GO_TO_BASE_PAGE, new GoToBasePage());
		commands.put(CommandName.GO_TO_REGISTRATION_PAGE, new GoToRegistrationPage());
		commands.put(CommandName.DO_AUTHORIZATION, new DoAuthorization());
		commands.put(CommandName.DO_REGISTRATION, new DoRegistration());
		commands.put(CommandName.DO_SIGN_OUT, new DoSignOut());
		commands.put(CommandName.GO_TO_NEWS_LIST, new GoToNewsList());
		commands.put(CommandName.GO_TO_VIEW_NEWS, new GoToViewNews());
		commands.put(CommandName.UNDEFINED, new GoToBasePage());
		
		commands.put(CommandName.GO_TO_USER_PROFILE, new GoToUserProfile());
		commands.put(CommandName.GO_TO_EDIT_USER_PROFILE, new GoToEditUserProfile());
		commands.put(CommandName.DO_UPDATE_USER, new DoUpdateUser());
		commands.put(CommandName.GO_TO_ADD_NEWS, new GoToAddNews());
		commands.put(CommandName.DO_ADD_NEWS, new DoAddNews());
		commands.put(CommandName.DO_DELETE_NEWS, new DoDeleteNews());
		commands.put(CommandName.GO_TO_USERS_LIST, new GoToUsersList());
		commands.put(CommandName.GO_TO_VIEW_USER, new GoToViewUser());
		
	}
	
	
	public Command getCommand(String name) {
		CommandName  commandName = CommandName.UNDEFINED;
		try {
			commandName = CommandName.valueOf(name.toUpperCase());	
		} catch(IllegalArgumentException e) {
		//ignore
			//log?
		}		
		return commands.get(commandName);
	}

}
