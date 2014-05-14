package org.yvka.Beleg1.ui.menues;

import org.yvka.Beleg1.matrix.operations.CommonMatrixOperations;
import org.yvka.Beleg1.ui.Application;
import org.yvka.Beleg1.ui.MatrixTO;
import org.yvka.Beleg1.ui.MenuCommand;
import org.yvka.Beleg1.ui.commands.RetrieveMatrixCommand;

/**
 * <p>
 * A menu entry which is intended to determine the rank of a specified matrix.
 *  <br>
 * List of supported parameters which are in the expected order of {@link #execute(String...)}.  
 * <ol>
 * 	<li>name - the name of a matrix for which the rank should be determined.</li>
 * </ol>
 * </p> 
 * @author Yves Kaufmann
 *
 */
public class MarixRankMenu extends MenuCommand {
	
	/**
	 * Creates the Menu and assigns the application 'app'.  
	 * 
	 * @param app the assigned application.
	 */
	public MarixRankMenu(Application app) {
		super(app);
	}
	

	@Override
	public void execute(String... args) {
		RetrieveMatrixCommand cmd = new RetrieveMatrixCommand(getApplication(), "Matrix for which the rank should calculated ? ");
		cmd.execute(args);
		
		if(cmd.hasMatrix()) {
			MatrixTO matrix = cmd.getMatrixTO();
			int rank = CommonMatrixOperations.determineRankOfMatrix(matrix.getMatrix());
			System.out.printf("The rank of the matrix '%s' is %d", matrix.getName(), rank);
			System.out.println();
		} 
	}

	@Override
	public String getName() {
		return "rank";
	}

	@Override
	public String getDescription() {
		return "Determines the rank of a specified matrix";
	}

	@Override
	public String getHelp() {
		return generateHelpTemplate(
			new Param("matrix", "The matrix for which the rank should calculated.", false)
		);
	}

}
