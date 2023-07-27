package by.tr.conspect.controller;

import java.util.HashMap;
import java.util.Map;

import by.tr.conspect.controller.impl.GoToBasePageCommand;
import by.tr.conspect.controller.impl.RegistrationCommand;
import by.tr.conspect.controller.impl.AuthorizationCommand;

public final class CommandProvider {
	private static final CommandProvider instance = new CommandProvider();
	
	private Map<CommandName, Command> commands = new HashMap<>();
	
	private CommandProvider() {
		commands.put(CommandName.AUTHORIZATION, new AuthorizationCommand());
		commands.put(CommandName.REGISTRATION, new RegistrationCommand());
		commands.put(CommandName.GO_TO_BASE_PAGE, new GoToBasePageCommand());
		commands.put(CommandName.UNDEFINED, new GoToBasePageCommand());
			
		
	}
	
	
	public Command getCommand(String name) {
		
		CommandName commandName = CommandName.valueOf(name.toUpperCase());
		if (commandName == null) commandName = CommandName.UNDEFINED;
		return commands.get(commandName);
	}
	
	
	public static CommandProvider getInstance() {
		return instance;
	}

}
