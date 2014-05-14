package org.yvka.Beleg1.ui.menues;

import java.util.List;

import org.yvka.Beleg1.ui.Application;
import org.yvka.Beleg1.ui.MenuCommand;

import static java.lang.System.out;

/**
 * <p>
 * A menu entry which is intended to list the names of the already created matrices.
 * </p> 
 * @author Yves Kaufmann
 *
 */
public class ListMatricesMenu extends MenuCommand {
	
	/**
	 * Creates the Menu and assigns the application 'app'.  
	 * 
	 * @param app the assigned application.
	 */
	public ListMatricesMenu(Application app) {
		super(app);
	}

	@Override
	public void execute(String... args) {	
		List<String> names = getApplication().getContext().getMatrixNames();
		
		if(names.size() > 0) {
			out.println("List of created matrices: ");
			names.forEach(out::println);
		} else {
			out.println("There are no created matrices.");
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
