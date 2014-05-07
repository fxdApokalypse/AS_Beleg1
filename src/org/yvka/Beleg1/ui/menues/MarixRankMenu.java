package org.yvka.Beleg1.ui.menues;

import org.yvka.Beleg1.operations.CommonMatrixOperations;
import org.yvka.Beleg1.ui.Application;
import org.yvka.Beleg1.ui.MatrixTO;
import org.yvka.Beleg1.ui.MenuCommand;
import org.yvka.Beleg1.ui.commands.RetrieveMatrixCommand;

public class MarixRankMenu extends MenuCommand {

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
