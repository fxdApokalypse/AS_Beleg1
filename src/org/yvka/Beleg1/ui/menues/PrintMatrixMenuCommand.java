package org.yvka.Beleg1.ui.menues;

import org.yvka.Beleg1.ui.Application;
import org.yvka.Beleg1.ui.MenuCommand;
import org.yvka.Beleg1.ui.commands.PrintMatrixCommand;
import org.yvka.Beleg1.ui.commands.RetrieveMatrixCommand;


/**
 * <p>
 * A menu entry which is intended to print matrices.
 *  <br>
 * List of supported parameters which are in the expected order of {@link #execute(String...)}.  
 * <ol>
 * 	<li>matrix - the name of a matrix which should be printed.</li>
 * </ol>
 * </p> 
 * @author Yves Kaufmann
 *
 */
public class PrintMatrixMenuCommand extends MenuCommand {
	
	/**
	 * Creates the Menu and assigns the application 'app'.  
	 * 
	 * @param app the assigned application.
	 */
	public PrintMatrixMenuCommand(Application app) {
		super(app);
	}
		
	@Override
	public void execute(String... args) {
		
		RetrieveMatrixCommand cmd = new RetrieveMatrixCommand(getApplication(), "Which matrix should print out ? ");
		cmd.execute(args);
		
		if(cmd.hasMatrix()) {
			new PrintMatrixCommand(cmd.getMatrixTO()).execute();
		}
	}

	@Override
	public String getName() {
		return "print";
	}

	@Override
	public String getDescription() {
		return "Print out a specified matrix.";
	}

	@Override
	public String getHelp() {
		return generateHelpTemplate(
			new Param("matrix", "The name of the matrix which should print out.")
		);
		
	}

}
