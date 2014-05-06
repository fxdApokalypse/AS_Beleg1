package org.yvka.Beleg1.ui.menues;

import org.yvka.Beleg1.operations.CommonOperations;
import org.yvka.Beleg1.ui.Application;
import org.yvka.Beleg1.ui.MatrixTO;
import org.yvka.Beleg1.ui.MenuCommand;
import org.yvka.Beleg1.ui.commands.RetrieveMatrixCommand;
import org.yvka.Beleg1.utils.StringUtil;

public class MaxRowSumMenu extends MenuCommand {

	private static final String RETRIEVE_MATRIX_PROMPT_TEXT = "For which matrix the most valued row sum should be determined : ";

	public MaxRowSumMenu(Application app) {
		super(app);
	}

	@Override
	public void execute(String... args) {
		RetrieveMatrixCommand retrieveMatrixCmd = new RetrieveMatrixCommand(getApplication(), RETRIEVE_MATRIX_PROMPT_TEXT);
		retrieveMatrixCmd.execute(args);
		
		if(retrieveMatrixCmd.hasMatrix()) {
			MatrixTO matrix = retrieveMatrixCmd.getMatrixTO();
			double maxRowSum = CommonOperations.determineMaxRowSum(matrix.getMatrix());
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
