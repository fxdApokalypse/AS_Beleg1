package org.yvka.Beleg1.ui.commands;

import org.yvka.Beleg1.data.Matrix;
import org.yvka.Beleg1.data.MatrixImpl;
import org.yvka.Beleg1.operations.InvalidMatrixFormatException;
import org.yvka.Beleg1.operations.MatrixIO;
import org.yvka.Beleg1.ui.Application;
import org.yvka.Beleg1.ui.ApplicationCommand;

import Prog1Tools.IOTools;

public class CreateCommand extends ApplicationCommand {

	public CreateCommand(Application app) {
		super(app);
	}
	

	@Override
	public void execute(String... args) {
		String name = retrieveMatrixName(args);
		Matrix matrix = new MatrixImpl(new double[][] {{1,2}, {1,2}}); // MatrixIO.parse(System.in);
		getApplication().getContext().putMatrix(name, matrix);
	}


	private String retrieveMatrixName(String... args) {
		String name = null;
		if(args.length > 0) {
			name = args[0];
		}
		
		do {
			if(name == null) { name = IOTools.readString("Name of the new matrix: "); }
			name = name.trim();
			
			if("".equals(name)) {
				System.out.println("The specified matrix name isn't valid.Please try it again.");
				name = null;
			}
			else
			if(getApplication().getContext().hasMatrix(name)) {
				System.out.println("The specified matrix already exists.Please try it again.");
				name = null;
			} else break;
			
		} while(true);
		
		return name;
	}

	@Override
	public String getName() {
		return "create";
	}

	@Override
	public String getDescription() {
		return "Creates a matrix for later usage.";
	}

	@Override
	public String getHelp() {
		return generateHelpTemplate(
			new Param("name", "The name for the new matrix.", true)
		);
	}

}
