package org.yvka.Beleg1.ui.menues;

import org.yvka.Beleg1.matrix.Matrix;
import org.yvka.Beleg1.ui.Application;
import org.yvka.Beleg1.ui.IOTools;
import org.yvka.Beleg1.ui.MatrixTO;
import org.yvka.Beleg1.ui.MenuCommand;
import org.yvka.Beleg1.ui.commands.PrintMatrixCommand;
import org.yvka.Beleg1.ui.commands.RetrieveMatrixCommand;


/**
 * <p>
 * A menu entry which is intended to calculate the scalar multiplication a matrix and a scalar.
 *   <br>
 * List of supported parameters which are in the expected order of {@link #execute(String...)}.  
 * <ol>
 * 	<li>matrix - the name of a matrix which should be multiplied by a scalar value.</li>
 *  <li>scalar - the scalar value.</li>
 * </ol>
 * </p> 
 * @author Yves Kaufmann
 *
 */
public class MatrixScalarMultiplicationMenu extends MenuCommand {
	
	/**
	 * Creates the Menu and assigns the application 'app'.  
	 * 
	 * @param app the assigned application.
	 */
	public MatrixScalarMultiplicationMenu(Application app) {
		super(app);
	}

	@Override
	public void execute(String... args) {
		double scalarValue = 0.0;
		RetrieveMatrixCommand cmd = new RetrieveMatrixCommand(
			getApplication(),
			"Wich matrix should used for the scalar multiplication ? "
		);
		
		cmd.execute(args);
		if(cmd.hasMatrix()) {
			if(args.length > 1) {
				scalarValue = Double.valueOf(args[1].replace(",", "."));
			} else {
				scalarValue = IOTools.readDouble("Which scalar value should used ? ");
			}
			
			MatrixTO matrixTO = cmd.getMatrixTO();
			Matrix scalarProduct = matrixTO.getMatrix().copy().multiplyBy(scalarValue);
			String title = String.format("%s * %.2f", matrixTO.getName(), scalarValue);
			new PrintMatrixCommand(new MatrixTO(scalarProduct, title)).execute();
			
		}
	}

	@Override
	public String getName() {
		return "scalarMultiply";
	}

	@Override
	public String getDescription() {
		return "Calculates the a scalar matrix multiplication of a matrix and a scalar.";
	}

	@Override
	public String getHelp() {
		return generateHelpTemplate(
			new Param("matrix", "The name of a matrix."),
			new Param("scalarValue", "A scalar value.")
		);
	}

}
