package org.yvka.Beleg1.ui.menues;

import org.yvka.Beleg1.matrix.IllegalMatrixComputationException;
import org.yvka.Beleg1.matrix.Matrix;
import org.yvka.Beleg1.ui.Application;
import org.yvka.Beleg1.ui.MatrixTO;
import org.yvka.Beleg1.ui.MenuCommand;
import org.yvka.Beleg1.ui.commands.BinaryMatrixOperandsRetrieverCommand;
import org.yvka.Beleg1.ui.commands.PrintMatrixCommand;

/**
 * A menu entry which is intended to calculate the sum of two matrices.<br>
 * <br>
 * List of supported parameters which are in the expected order of {@link #execute(String...)}.  
 * <ol>
 * 	<li>firstMatrix - the name of the first matrix summand.</li>
 *  <li>secondtMatrix - the name of the second matrix summand.</li>
 * </ol>
 * @author Yves Kaufmann
 *
 */
public class MatrixAdditionMenu extends MenuCommand {
	
	/**
	 * Creates the Menu and assigns the application 'app'.  
	 * 
	 * @param app the assigned application.
	 */
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
			Matrix result = firstMatrix.getMatrix().copy().add(secondMatrix.getMatrix());
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
