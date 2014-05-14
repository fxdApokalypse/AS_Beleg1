package org.yvka.Beleg1.ui.menues;

import org.yvka.Beleg1.ui.MenuCommand;

/**
 * A menu command which prints a error message that a passed argument
 * isn't a valid matrix command.<br>
 * <br>
 * List of supported parameters which are in the expected order of {@link #execute(String...)}.  
 * <ol>
 * 	<li>command - the name of a command which should be marked in the error message.</li>
 * </ol>
 * @author Yves Kaufmann
 *
 */
public final class UnknownMenu extends MenuCommand {
	
	/**
	 * Creates the UnknownCommand.
	 */
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
