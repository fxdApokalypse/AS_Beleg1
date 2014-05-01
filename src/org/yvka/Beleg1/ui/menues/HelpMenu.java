package org.yvka.Beleg1.ui.commands;

import static java.lang.System.out;

import java.util.List;

import org.yvka.Beleg1.ui.Application;
import org.yvka.Beleg1.ui.ApplicationCommand;
import org.yvka.Beleg1.ui.Command;
import org.yvka.Beleg1.ui.MatrixCommandSet;
import org.yvka.Beleg1.utils.StringUtil;

public class HelpCommand extends ApplicationCommand {
	
	public static final String INDENT = "   ";
	
	public HelpCommand(Application app) {
		super(app);
	}
	
	@Override
	public void execute(String...args) {
		if(args.length <= 0 ) {
			printMainHelp();
		} else {
			MatrixCommandSet commands = getApplication().getCommands();
			Command command = commands.get(args[0]);
			if(command instanceof ApplicationCommand) {
				out.println(((ApplicationCommand) command).getHelp());
			}
		}
	}

	private void printMainHelp() {
		
		List<ApplicationCommand> applicationCommands = getApplication().getCommands().getAvailableApplicationCommands();
		applicationCommands.sort((x1, x2) -> x1.getName().compareTo(x2.getName()));
		out.println("Usage: matrix <cmd> [<arguments>]");
		out.println();
		out.println("The most commonly used matrix commands are:");
		
		for(ApplicationCommand cmd : applicationCommands) {
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
