package org.yvka.Beleg1.ui;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

public class CommandSet implements Command {
	
	private HashMap<String, Command> commands = new HashMap<>();
	private Command commandForUnknownCommands = null;
	
	public void setUnknownCommand(Command command) {
		this.commandForUnknownCommands = command;
	}
	
	public Command getUnknownCommand() {
		if(this.commandForUnknownCommands != null) {
			return this.commandForUnknownCommands;
		}
		return Command.NULL;
	}
	
	public boolean has(String command) {
		return commands.containsKey(command);
	}
	public void add(String name, Command command) {
		commands.put(name, command);
	}

	public Command get(String name) {
		if(has(name)) {
			return commands.get(name);
		}
		return getUnknownCommand();
	}
	
	public Command remove(String name) {
		if(has(name)) {
			return commands.remove(name);
		}
		return getUnknownCommand();
	}
	
	protected Collection<Command> getCommandsCollection() {
		return commands.values();
	}
	
	@Override
	public void execute(String...arguments) {
		if(arguments.length > 0) {
			String cmd = arguments[0].trim();		
			String[] cmdArguments = Arrays.copyOfRange(arguments,1, arguments.length);
			
			if(has(cmd)) {
				get(cmd).execute(cmdArguments);
			} else {
				get(cmd).execute(arguments);
			}
		}
	}

}
