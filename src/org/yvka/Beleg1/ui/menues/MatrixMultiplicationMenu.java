package org.yvka.Beleg1.ui.menues;

import org.yvka.Beleg1.data.IllegalMatrixComputationException;
import org.yvka.Beleg1.data.Matrix;
import org.yvka.Beleg1.ui.Application;
import org.yvka.Beleg1.ui.MatrixTO;
import org.yvka.Beleg1.ui.MenuCommand;
import org.yvka.Beleg1.ui.commands.BinaryMatrixOperandsRetrieverCommand;
import org.yvka.Beleg1.ui.commands.PrintMatrixCommand;

public class MatrixMultiplicationMenu extends MenuCommand {

	public MatrixMultiplicationMenu(Application app) {
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
			Matrix result = firstMatrix.getMatrix().multiplyBy(secondMatrix.getMatrix());
			MatrixTO matrixTO = new MatrixTO(result, firstMatrix.getName() + " x " + secondMatrix.getName());
			new PrintMatrixCommand(matrixTO).execute();
		} catch (IllegalMatrixComputationException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public String getName() {
		return "multiply";
	}

	@Override
	public String getDescription() {
		return "Calculates the product of two matrices";
	}

	@Override
	public String getHelp() {
		return generateHelpTemplate(
			new Param("firstMatrix", "The first factor of the matrix product.", false),
			new Param("secondMatrix", "The second factor of the matrix product.", false)
		);
	}

}
