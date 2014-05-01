package org.yvka.Beleg1.ui.menues;

import org.yvka.Beleg1.ui.MenuCommand;


public final class UnknownMenu extends MenuCommand {

	public UnknownMenu() {
		super(null);
	}

	@Override
	public void execute(String...args) {
		String cmd = args.length > 0 ? args[0] : "null"; 
		System.out.printf("Matrix: '%s' is not a matrix command. See 'matrix help'.\n", cmd);
	}

	@Override
	public String getName() { return ""; }
	@Override
	public String getDescription() { return ""; }
	@Override
	public String getHelp() { return ""; }
	
}
