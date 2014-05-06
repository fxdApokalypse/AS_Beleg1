package org.yvka.Beleg1.ui.menues;

import org.yvka.Beleg1.data.Matrix;
import org.yvka.Beleg1.ui.Application;
import org.yvka.Beleg1.ui.IOTools;
import org.yvka.Beleg1.ui.MatrixTO;
import org.yvka.Beleg1.ui.MenuCommand;
import org.yvka.Beleg1.ui.commands.PrintMatrixCommand;
import org.yvka.Beleg1.ui.commands.RetrieveMatrixCommand;



public class MatrixScalarMultiplicationMenu extends MenuCommand {

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
		return "scalarProd";
	}

	@Override
	public String getDescription() {
		return "Calculates the scalar matrix product of a specified matrix and a scalar value.";
	}

	@Override
	public String getHelp() {
		return generateHelpTemplate(
			new Param("matrix", "The name of a matrix."),
			new Param("scalarValue", "A scalar value.")
		);
	}

}
