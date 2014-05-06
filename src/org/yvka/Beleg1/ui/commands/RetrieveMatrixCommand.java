package org.yvka.Beleg1.ui.commands;

import org.yvka.Beleg1.data.Matrix;
import org.yvka.Beleg1.ui.Application;
import org.yvka.Beleg1.ui.ApplicationCommand;
import org.yvka.Beleg1.ui.IOTools;
import org.yvka.Beleg1.ui.MatrixTO;


public class RetrieveMatrixCommand extends ApplicationCommand {

	private MatrixTO matrixResult = null;
	private String prompt = null;
	private boolean shouldAskForNewMatrix = true;
	
	

	public RetrieveMatrixCommand(Application application,  String prompt) {
		super(application);
		this.prompt = prompt;
		shouldAskForNewMatrix = true;
	}
	
	public boolean hasMatrix() {
		return matrixResult != null;
	}
	
	public MatrixTO getMatrixTO() {
		return matrixResult;
	}
	
	public void disableAskForNewMatrix() {
		this.shouldAskForNewMatrix = false;
	}

	@Override
	public void execute(String... args) {
		String name = null;
		if(args.length > 0) {
			name = args[0];
		}
		
		name = retrieveMatrixName(name);
		Matrix matrix = retrieveMatrix(name);
		
		if(matrix != null) {
			matrixResult = new MatrixTO(matrix, name);
		}
	}
	
	private Matrix retrieveMatrix(String name) {

		if(!getContext().hasMatrix(name)) {
			System.out.printf("The specified matrix '%s' does not exists.\n", name);
			if(!shouldAskForNewMatrix) return null;
			String prompt = String.format("Do you want to create the matrix '%s' ? [yes,no] ", name);
			String answer = IOTools.readString(prompt).trim();
			if(!"yes".equalsIgnoreCase(answer)) {
				return null; 
			}
			getMenuCommand("create").execute(name);
		}
		
		return getContext().getMatrix(name);
	}
	
	private String retrieveMatrixName(String name) {
		if(name == null) {
			return  IOTools.readString(prompt).trim();
		} else {
			return name;
		}
	}
	
}
