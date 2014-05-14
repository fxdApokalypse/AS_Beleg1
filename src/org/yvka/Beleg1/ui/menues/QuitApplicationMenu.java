package org.yvka.Beleg1.ui.menues;

import org.yvka.Beleg1.ui.Application;
import org.yvka.Beleg1.ui.MenuCommand;

/**
 * <p>
 * A menu entry which is intended to quit the assigned application.
 * 
 * </p> 
 * @author Yves Kaufmann
 *
 */
public class QuitApplicationMenu extends MenuCommand {
	
	/**
	 * Creates the Menu and assigns the application 'app'.  
	 * 
	 * @param app the assigned application.
	 */
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
