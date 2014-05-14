package org.yvka.Beleg1.ui.menues;

import org.yvka.Beleg1.matrix.operations.CommonMatrixOperations;
import org.yvka.Beleg1.ui.Application;
import org.yvka.Beleg1.ui.MatrixTO;
import org.yvka.Beleg1.ui.MenuCommand;
import org.yvka.Beleg1.ui.commands.RetrieveMatrixCommand;
import org.yvka.Beleg1.utils.StringUtil;

/**
 * A menu entry which is intended to determine the max valued row sum of a specified matrix.<br>
 * <br>
 * List of supported parameters which are in the expected order of {@link #execute(String...)}.  
 * <ol>
 * 	<li>matrix - the name of a matrix for which the max row sum should be determined.</li>
 * </ol>
 * @author Yves Kaufmann
 *
 */
public class MaxRowSumMenu extends MenuCommand {

	private static final String RETRIEVE_MATRIX_PROMPT_TEXT = "For which matrix the most valued row sum should be determined : ";
	
	/**
	 * Creates the Menu and assigns the application 'app'.  
	 * 
	 * @param app the assigned application.
	 */
	public MaxRowSumMenu(Application app) {
		super(app);
	}

	@Override
	public void execute(String... args) {
		RetrieveMatrixCommand retrieveMatrixCmd = new RetrieveMatrixCommand(getApplication(), RETRIEVE_MATRIX_PROMPT_TEXT);
		retrieveMatrixCmd.execute(args);
		
		if(retrieveMatrixCmd.hasMatrix()) {
			MatrixTO matrix = retrieveMatrixCmd.getMatrixTO();
			double maxRowSum = CommonMatrixOperations.determineMaxRowSum(matrix.getMatrix());
			System.out.printf("The most valued row sum of the matrix '%s' is %.2f.%s", matrix.getName(), maxRowSum, StringUtil.LINE_SEPERATOR );
		}
		
	}

	@Override
	public String getName() {
		return "maxRowSum";
	}

	@Override
	public String getDescription() {
		return "Determines the most valued row sum of a specified matrix.";
	}

	@Override
	public String getHelp() {
		return generateHelpTemplate(
			new Param("matrix", "The name of the matrix for which should the max row sum calculated.")
		);
	}

}
