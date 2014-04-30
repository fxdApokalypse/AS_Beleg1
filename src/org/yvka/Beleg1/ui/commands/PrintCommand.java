package org.yvka.Beleg1.ui.commands;

import org.yvka.Beleg1.data.Matrix;
import org.yvka.Beleg1.operations.MatrixIO;
import org.yvka.Beleg1.ui.Application;
import org.yvka.Beleg1.ui.ApplicationCommand;
import org.yvka.Beleg1.utils.StringUtil;

import Prog1Tools.IOTools;

public class PrintCommand extends ApplicationCommand {

	public PrintCommand(Application app) {
		super(app);
	}
	

	@Override
	public void execute(String... args) {
		String matrixName = null;
		
		
		if(args.length <= 0) {
			matrixName = IOTools.readString("Which matrix should print out ? " ).trim();
		} else {
			matrixName = args[0];
		}
		
		
		if(!getApplication().getContext().hasMatrix(matrixName)) {
			System.out.printf("The specified matrix '%s' does not exists.\n", matrixName);
			String prompt = String.format("Do you want to create the matrix '%s' ? [yes,no] ", matrixName);
			String answer = IOTools.readString(prompt).trim();
			if(!"yes".equalsIgnoreCase(answer)) {
				return;
			}
			getApplication().getCommands().get("create").execute(matrixName);
		}
		
		Matrix matrix = getApplication().getContext().getMatrix(matrixName);		
		String matrixString = MatrixIO.toString(matrix);
		matrixString = StringUtil.wrapTopBottomBorders(matrixString, matrixName);
		System.out.println(matrixString);
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
