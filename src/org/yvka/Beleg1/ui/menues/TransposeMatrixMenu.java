package org.yvka.Beleg1.ui.menues;

import org.yvka.Beleg1.ui.Application;
import org.yvka.Beleg1.ui.MatrixTO;
import org.yvka.Beleg1.ui.MenuCommand;
import org.yvka.Beleg1.ui.commands.PrintMatrixCommand;
import org.yvka.Beleg1.ui.commands.RetrieveMatrixCommand;

public class TransposeMatrixMenu extends MenuCommand {

	public TransposeMatrixMenu(Application app) {
		super(app);
	}

	@Override
	public void execute(String... args) {
		
		RetrieveMatrixCommand cmd = new RetrieveMatrixCommand(getApplication(), "Which matrix should be transposed ? ");
		cmd.execute(args);
		
		if(cmd.hasMatrix()) {
			MatrixTO matrix = cmd.getMatrixTO();
			MatrixTO transposedMatrix = new MatrixTO(
					matrix.getMatrix().transposition(),
					matrix.getName() + "^T"
			);			
			new PrintMatrixCommand(transposedMatrix).execute();
		} 
	}

	@Override
	public String getName() {
		return "transpose";
	}

	@Override
	public String getDescription() {
		return "Calculates the transposed matrix of a specified matrix.";
	}

	@Override
	public String getHelp() {
		return generateHelpTemplate(
			new Param("matrix", "The matrix for which should the transposed matrix calculated.")
		);
	}
}
