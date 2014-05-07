package org.yvka.Beleg1.ui;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

/**
 * <p>
 * A CommandSet is a set of command name command instance pairs.<br>
 * <br>
 * This class is to intended to store, retrieve and remove command by his names.<br>
 * Furthermore this is itself a command which dispatches the assigned arguments<br>
 * to a command which can handle the specified arguments, for more details see
 * {@link CommandSet#execute(String...)}.<br>
 * <br>
 * </p>
 * @author Yves Kaufmann
 * @see Command
 *
 */
public class CommandSet implements Command {
	
	private HashMap<String, Command> commands = new HashMap<>();
	private Command commandForUnknownCommands = null;
	
	
	/**
	 * Returns the unknown command defined by {@link #setUnknownCommand(Command)} 
	 * which is by default {@link Command.NullCommand }.
	 * 
	 * @return the unknown command.
	 */
	public Command getUnknownCommand() {
		if(this.commandForUnknownCommands != null) {
			return this.commandForUnknownCommands;
		}
		return Command.NULL;
	}

	/**
	 * Defines the command which should returned for unknown commands. 
	 * 
	 * @param command the command which handled unknown commands.
	 */
	public void setUnknownCommand(Command command) {
		this.commandForUnknownCommands = command;
	}
	
	/**
	 * <p>
	 * Checks if a command  with the name <code>'command'</code> exists.<br>
	 * <br>
	 * </p>
	 * @param command the name of the command which should test for its existence. 
	 * @return true if the command exits.
	 */
	public boolean has(String command) {
		return commands.containsKey(command);
	}
	
	/**
	 * <p>
	 * Inserts a command with a identifying name. <br>
	 * <br>
	 * </p>
	 * @param name the name of the command which should inserted into this set.
	 * @param command the command which should inserted into this set.
	 */
	public void add(String name, Command command) {
		commands.put(name, command);
	}

	/**
	 * <p>
	 * Returns the desired command with the name 'name' if such a command exists.<br> 
	 * Otherwise it returns the defined unknown command object.
	 * </p>
	 * @param name the name of the desired command.
	 * @return the desired command if the desired command exists otherwise the unknown command.
	 * @see #setUnknownCommand(Command) 
	 */
	public Command get(String name) {
		if(has(name)) {
			return commands.get(name);
		}
		return getUnknownCommand();
	}
	
	/**
	 * <p>
	 * Remove a command by his name.<br>
	 * <br>
	 * </p>
	 * @param name the name of the command which should be removed.
	 * @return either the removed command or unknown command.  
	 */
	public Command remove(String name) {
		if(has(name)) {
			return commands.remove(name);
		}
		return getUnknownCommand();
	}
	
	/**
	 * Returns a collection of stored command objects.
	 *  
	 * @return the collection of stored command objects.
	 */
	public Collection<Command> getCommandsCollection() {
		return commands.values();
	}
	
	/**
	 * <p>
	 * Executes the command which is specified in the first argument by his name <br> 
	 * with the rest of the arguments (the name argument is not passed).<br>
	 * <br> 
	 * In the case when a unknow command is specified the {@link #getUnknownCommand()} is executed. 
	 * 
	 */
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
