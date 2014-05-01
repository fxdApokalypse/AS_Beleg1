package org.yvka.Beleg1.ui.menues;

import org.yvka.Beleg1.data.Matrix;
import org.yvka.Beleg1.operations.MatrixIO;
import org.yvka.Beleg1.ui.Application;
import org.yvka.Beleg1.ui.MatrixTO;
import org.yvka.Beleg1.ui.MenuCommand;
import org.yvka.Beleg1.ui.commands.PrintMatrixCommand;
import org.yvka.Beleg1.ui.commands.RetrieveMatrixCommand;
import org.yvka.Beleg1.utils.StringUtil;

public class PrintMatrixMenuCommand extends MenuCommand {
	
	private MatrixTO matrixTO = null;
	
	public PrintMatrixMenuCommand(Application app) {
		super(app);
		matrixTO = null;
	}
		
	@Override
	public void execute(String... args) {
		
		RetrieveMatrixCommand cmd = new RetrieveMatrixCommand(getApplication(), "Which matrix should print out ? ");
		cmd.execute(args);
		
		if(cmd.hasMatrix()) {
			new PrintMatrixCommand(cmd.getMatrixTO()).execute();
		}
	}

	@Override
	public String getName() {
		return "print";
	}

	@Override
	public String getDescription() {
		return "Print out a specified matrix.";
	}

	@Override
	public String getHelp() {
		return generateHelpTemplate(
			new Param("matrix", "The name of the matrix which should print out.")
		);
		
	}

}
