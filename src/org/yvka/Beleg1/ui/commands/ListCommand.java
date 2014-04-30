package org.yvka.Beleg1.ui.commands;

import java.util.List;

import org.yvka.Beleg1.ui.Application;
import org.yvka.Beleg1.ui.ApplicationCommand;

import static java.lang.System.out;

public class ListCommand extends ApplicationCommand {

	public ListCommand(Application app) {
		super(app);
	}

	@Override
	public void execute(String... args) {	
		List<String> names = getApplication().getContext().getMatrixNames();
		
		if(names.size() > 0) {
			System.out.println("List of created matrices: ");
			names.forEach(out::println);
		} else {
			System.out.println("There are no created matrices.");
		}
	}

	@Override
	public String getName() {
		return "list";
	}

	@Override
	public String getDescription() {
		return "Lists the names of the created matrices.";
	}

	@Override
	public String getHelp() {
		return generateHelpTemplate();
	}

}
