package org.yvka.Beleg1.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.yvka.Beleg1.ui.commands.CreateCommand;
import org.yvka.Beleg1.ui.commands.HelpCommand;
import org.yvka.Beleg1.ui.commands.ListCommand;
import org.yvka.Beleg1.ui.commands.PrintCommand;
import org.yvka.Beleg1.ui.commands.QuitCommand;
import org.yvka.Beleg1.ui.commands.UnknowCommand;

public class MatrixCommandSet extends CommandSet {
	public MatrixCommandSet(Application application) {
		setUnknownCommand(new UnknowCommand());
		
		//add("create", 	new CreateMatrixCommand(application));
		//add("list",   	new ListMatricesCommand(application));
		//add("delete", 	new DeleteMatrixCommand(application));
		//add("print",  	new PrintMatrixCommand(application));
		//add("add", 		new SummarizeMatricesCommand(application));
		//add("multiply", new MultiplyMatricesCommand(application));
		//add("transpose", new TransposingMatrixCommand(application));
		//add("rank", new DetermineMatrixRankCommand(application));
		
		add(new CreateCommand(application));
		add(new PrintCommand(application));
		add(new ListCommand(application));
		add(new HelpCommand(application));
		add(new QuitCommand(application));
	}
	

	private void add(ApplicationCommand command, String...aliasNames) {
		add(command.getName(), command);
		for(String aliasName : aliasNames) {
			add(aliasName, command);
		}
	}
	
	public List<ApplicationCommand> getAvailableApplicationCommands() {
		return getCommandsCollection()
				.stream()
				.filter(cmd -> cmd instanceof ApplicationCommand)
				.map(cmd -> (ApplicationCommand) cmd)
				.collect(Collectors.toList());
	}
}
