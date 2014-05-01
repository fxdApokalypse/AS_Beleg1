package org.yvka.Beleg1.ui.menues;

import org.yvka.Beleg1.ui.Application;
import org.yvka.Beleg1.ui.Command;
import org.yvka.Beleg1.ui.MenuCommand;

/**
 */
public class QuitApplicationMenu extends MenuCommand {

	public QuitApplicationMenu(Application app) {
		super(app);
	}
	
	@Override
	public void execute(String...args) {
		getApplication().stop();
	}

	
	@Override
	public String getName() {
		return "quit";
	}

	@Override
	public String getDescription() {
		return "Stops this application.";
	}

	@Override
	public String getHelp() {
		StringBuilder str = new StringBuilder(); 
		 str.append(getDescription());
		 str.append("\n");
		 str.append("Usage: matrix quit\n");
		 str.append("\n");
		 str.append("The quit commando has no parameter.\n");
		 return str.toString();
	}

}
