package org.yvka.Beleg1.ui.menues;

import static java.lang.System.out;

import org.yvka.Beleg1.ui.Application;
import org.yvka.Beleg1.ui.Command;
import org.yvka.Beleg1.ui.MatrixMenuSet;
import org.yvka.Beleg1.ui.MenuCommand;

/**
 * <p>
 * A menu entry which is intended to print the help for the application or
 * for a single menu command.
 * <br>
 * List of supported parameters which are in the expected order of {@link #execute(String...)}.  
 * <ol>
 * 	<li>command - the command for which the help should be printed.</li>
 * </ol>
 * </p> 
 * @author Yves Kaufmann
 *
 */
public class HelpMenu extends MenuCommand {
	
	/**
	 * The default indenting which is used for the help page.
	 */
	public static final String INDENT = "   ";
	
	/**
	 * Creates the Menu and assigns the application 'app'.  
	 * 
	 * @param app the assigned application.
	 */
	public HelpMenu(Application app) {
		super(app);
	}
	
	@Override
	public void execute(String...args) {
		if(args.length <= 0 ) {
			printMainHelp();
		} else {
			MatrixMenuSet commands = getApplication().getMenuSet();
			Command command = commands.get(args[0]);
			if(command instanceof MenuCommand) {
				if(command instanceof UnknownMenu) {
					command.execute(args);
					return;
				}
				out.println(((MenuCommand) command).getHelp());
			} 
		}
	}

	private void printMainHelp() {
		MatrixMenuSet menuSet =  getApplication().getMenuSet();
		out.println("Usage: matrix <cmd> [<arguments>]");
		out.println();
		out.println("The most commonly used matrix commands are:");
		
		for(MenuCommand cmd : menuSet.getAllMenuCommands()) {
			out.printf("%s%-10s %s\n", INDENT, cmd.getName(), cmd.getDescription());
		}
		out.println();
	}
	
	@Override
	public String getName() {
		return "help";
	}

	@Override
	public String getDescription() {
		return "Display help information about a specific command.";
	}
	
	@Override
	public String getHelp() {
		return generateHelpTemplate(
			new Param("cmd", "If specified 'help' displays the help for this command.", false)
		);
	}
	
	

}
