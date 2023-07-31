package by.htp.ex.controller;

import java.util.HashMap;
import java.util.Map;

import by.htp.ex.controller.impl.*;


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
		commands.put(CommandName.GO_TO_EDIT_NEWS, new GoToEditNews());
		commands.put(CommandName.DO_EDIT_NEWS, new DoEditNews());
		
		commands.put(CommandName.GO_TO_USER_PROFILE, new GoToUserProfile());
		commands.put(CommandName.GO_TO_EDIT_USER_PROFILE, new GoToEditUserProfile());
		commands.put(CommandName.DO_UPDATE_USER, new DoUpdateUser());
		commands.put(CommandName.GO_TO_ADD_NEWS, new GoToAddNews());
		commands.put(CommandName.DO_ADD_NEWS, new DoAddNews());
		commands.put(CommandName.DO_DELETE_NEWS, new DoDeleteNews());
		commands.put(CommandName.GO_TO_USERS_LIST, new GoToUsersList());
		commands.put(CommandName.GO_TO_VIEW_USER, new GoToViewUser());
		commands.put(CommandName.GO_TO_ERROR_PAGE, new GoToErrorPage());
		
				
		
	}
	
	
	public Command getCommand(String name) {
		CommandName  commandName = CommandName.UNDEFINED;
		try {
			if(name!=null) {
				commandName = CommandName.valueOf(name.toUpperCase());
			}
		} catch(IllegalArgumentException e) {
		//ignore
			//log?
		}		
		return commands.get(commandName);
	}

}
