package org.yvka.Beleg1.ui.commands;

import org.yvka.Beleg1.ui.Command;

public class NullCommand implements Command {
	
	private final static NullCommand instance = new NullCommand();
	public static Command getInstance() {
		return instance;
	} 
	
	protected NullCommand() {}
	
	@Override
	public void execute(String...args) {
	}

}
