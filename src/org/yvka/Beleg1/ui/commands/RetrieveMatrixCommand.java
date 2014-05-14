package org.yvka.Beleg1.ui.commands;

import org.yvka.Beleg1.matrix.Matrix;
import org.yvka.Beleg1.ui.Application;
import org.yvka.Beleg1.ui.ApplicationCommand;
import org.yvka.Beleg1.ui.IOTools;
import org.yvka.Beleg1.ui.MatrixTO;

/**
 * <p>
 * A Command which is used to request a single matrix from the user.<br>
 * In other word the class asks for a matrix name and enables the caller to 
 * retrieve the corresponding matrix instance.<br>
 * <br>
 * The names of the matrix can be pass to {@link #execute(String...)}
 * but the caller can call {@link #execute(String...)} without arguments.
 * In this case the class request the name from the user.
 * <br>
 * If an entered matrix name does not exist, the class asks the 
 * user to create the matrix.
 * </p>
 * @author Yves Kaufmann
 *
 */
public class RetrieveMatrixCommand extends ApplicationCommand {

	private MatrixTO matrixResult = null;
	private String prompt = null;
	private boolean shouldAskForNewMatrix = true;
	
	
	/**
	 * Create a RetrieveMatrixCommand and assigned to the 
	 * Application 'application'. 
	 * 
	 * @param application the assigned Application.
	 * @param prompt the prompt message which should showed to the user 
	 * 		  when asked for a matrix name.
	 */
	public RetrieveMatrixCommand(Application application,  String prompt) {
		super(application);
		this.prompt = prompt;
		shouldAskForNewMatrix = true;
	}
	
	/**
	 * Determines if the matrix was successfully entered from the user.
	 * 
	 * @return true, if the matrix was correctly entered.
	 */
	public boolean hasMatrix() {
		return matrixResult != null;
	}
	
	/**
	 * Returns the correctly entered matrix.
	 *
	 * @return the entered matrixTO or null if the matrix was not correctly entered.
	 */
	public MatrixTO getMatrixTO() {
		return matrixResult;
	}
	
	/**
	 * Disables the question for creating a matrix when a matrix name is entered
	 * which doesn't exists.
	 */
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
		
		boolean isTransposingRequested = name.endsWith("^t") || name.endsWith("^T");
		if(isTransposingRequested) {
			name = name.substring(0, name.length() - 2);
		}
		
		if(!getContext().hasMatrix(name)) {
			System.out.printf("The specified matrix '%s' does not exists.\n", name);
			if(!shouldAskForNewMatrix) return null;
			String prompt = String.format("Do you want to create the matrix '%s' ? [yes,no] ", name);
			String answer = IOTools.readString(prompt).trim();
			
			if(!answer.startsWith("y")) {
				return null; 
			}
			getMenuCommand("create").execute(name);
		} 
		
		Matrix m = getContext().getMatrix(name);
		
		return isTransposingRequested ? m.transposition() : m;
	}
	
	private String retrieveMatrixName(String name) {
		if(name == null) {
			return  IOTools.readString(prompt).trim();
		} else {
			return name;
		}
	}
	
}
