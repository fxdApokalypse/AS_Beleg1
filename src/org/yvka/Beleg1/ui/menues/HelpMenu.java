package org.yvka.Beleg1.ui.menues;

import static java.lang.System.out;

import java.util.List;

import org.yvka.Beleg1.ui.Application;
import org.yvka.Beleg1.ui.Command;
import org.yvka.Beleg1.ui.MatrixMenuSet;
import org.yvka.Beleg1.ui.MenuCommand;
import org.yvka.Beleg1.utils.StringUtil;

public class HelpMenu extends MenuCommand {
	
	public static final String INDENT = "   ";
	
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
