package org.yvka.Beleg1.ui.commands;

import org.yvka.Beleg1.ui.Command;

public class UnknowCommand implements Command {

	@Override
	public void execute(String...args) {
		String cmd = args.length > 0 ? args[0] : "null"; 
		System.out.printf("Matrix: '%s' is not a matrix command. See 'matrix help'.\n", cmd);
	}
	
}
