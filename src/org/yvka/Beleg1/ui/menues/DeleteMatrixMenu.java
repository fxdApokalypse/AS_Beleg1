package org.yvka.Beleg1.ui.menues;

import org.yvka.Beleg1.ui.Application;
import org.yvka.Beleg1.ui.MatrixTO;
import org.yvka.Beleg1.ui.MenuCommand;
import org.yvka.Beleg1.ui.commands.RetrieveMatrixCommand;

public class DeleteMatrixMenu extends MenuCommand {

	public DeleteMatrixMenu(Application app) {
		super(app);
	}

	@Override
	public void execute(String... args) {
		RetrieveMatrixCommand cmd = new RetrieveMatrixCommand(getApplication(), "Which matrix should be deleted : ");
		cmd.disableAskForNewMatrix();
		cmd.execute(args);
		
		if(cmd.hasMatrix()) {
			MatrixTO matrix = cmd.getMatrixTO();
			getContext().removeMatrix(matrix.getName());
		} 
	}

	@Override
	public String getName() {
		return "delete";
	}

	@Override
	public String getDescription() {
		return "Deletes a specified matrix by its name.";
	}

	@Override
	public String getHelp() {
		return generateHelpTemplate(
			new Param("matrix", "The matrix which should be deleted.")
		);
	}
	
}
