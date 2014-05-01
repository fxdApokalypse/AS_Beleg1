package org.yvka.Beleg1.ui.menues;

import org.yvka.Beleg1.data.IllegalMatrixComputationException;
import org.yvka.Beleg1.data.Matrix;
import org.yvka.Beleg1.ui.Application;
import org.yvka.Beleg1.ui.MatrixTO;
import org.yvka.Beleg1.ui.MenuCommand;
import org.yvka.Beleg1.ui.commands.BinaryMatrixOperandsRetrieverCommand;
import org.yvka.Beleg1.ui.commands.PrintMatrixCommand;

public class MatrixAdditionMenu extends MenuCommand {

	public MatrixAdditionMenu(Application app) {
		super(app);
	}
	

	@Override
	public void execute(String... args) {
		
		BinaryMatrixOperandsRetrieverCommand cmd = new BinaryMatrixOperandsRetrieverCommand(getApplication());
		cmd.execute(args);
		
		if(!cmd.hasOperands()) return;
		
		MatrixTO firstMatrix = cmd.getFirstOperand();
		MatrixTO secondMatrix = cmd.getSecondOperand();
		
		try {
			Matrix result = firstMatrix.getMatrix().add(secondMatrix.getMatrix());
			MatrixTO matrixTO = new MatrixTO(result, firstMatrix.getName() + " + " + secondMatrix.getName());
			new PrintMatrixCommand(matrixTO).execute();
		} catch (IllegalMatrixComputationException e) {
			System.out.println(e);
		}
	}

	@Override
	public String getName() {
		return "add";
	}

	@Override
	public String getDescription() {
		return "Caculates the sum of two matrices.";
	}

	@Override
	public String getHelp() {
		return generateHelpTemplate(
			new Param("firstMatrix", "The first summand of the matrix sum.", false),
			new Param("secondMatrix", "The second summand of the matrix sum.", false)
		);
	}

}
