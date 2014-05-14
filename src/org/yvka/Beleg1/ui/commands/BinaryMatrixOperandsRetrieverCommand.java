package org.yvka.Beleg1.ui.commands;

import org.yvka.Beleg1.ui.Application;
import org.yvka.Beleg1.ui.ApplicationCommand;
import org.yvka.Beleg1.ui.MatrixTO;

/**
 * <p>
 * A Command which is used to request two matrices from the user.<br>
 * In other word the class asks for two matrix names and enables the caller to 
 * retrieve the corresponding matrix instances.<br>
 * <br>
 * The two names of matrices can be pass to {@link #execute(String...)}
 * but the caller can call {@link #execute(String...)} without arguments.
 * In this case the class request the names from the user.
 * <br>
 * If an entered matrix name does not exist, the class asks the 
 * user to create the matrix.
 * </p>
 * @author Yves Kaufmann
 *
 */
public class BinaryMatrixOperandsRetrieverCommand extends ApplicationCommand  {
	
	private MatrixTO firstOperand = null;
	private MatrixTO secondOperand = null;
	
	/**
	 * Create a BinaryMatrixOperandsRetrieverCommand and assigned to the 
	 * Application 'app'.
	 * 
	 * @param app the assigned Application.
	 */
	public BinaryMatrixOperandsRetrieverCommand(Application app) {
		super(app);
	}
	
	@Override
	public void execute(String... args) {
		resetCommand();
		RetrieveMatrixCommand retrieveFirstMatrix = new RetrieveMatrixCommand(
			getApplication(),
			"Please provide the name of the first matrix: "
		);
		RetrieveMatrixCommand retrieveSecondMatrix = new RetrieveMatrixCommand(
			getApplication(),
			"Please provide the name of the second matrix: "
		);
				
		retrieveFirstMatrix.execute(args);
		setFirstOperand(retrieveFirstMatrix.getMatrixTO());
		if(!retrieveFirstMatrix.hasMatrix()) {
			handleMissingParameter("first matrix");
			return;
		}
		
		retrieveSecondMatrix.execute(args.length > 1 ? args[1] : null);
		setSecondOperand(retrieveSecondMatrix.getMatrixTO());
		if(!retrieveSecondMatrix.hasMatrix()) {
			handleMissingParameter("second matrix");
			return;
		}
	}
	/**
	 * Prints out a error message for a missing parameter.
	 * 
	 * @param name the name of a missing Parameter
 	 */
	@SuppressWarnings("static-method")
	private void handleMissingParameter(String name) {
		System.out.println("This operation requires two matrix operands but the " + name + " is missing.");
		System.out.println("Please try it again.");
	}

	protected void resetCommand() {
		setFirstOperand(null);
		setSecondOperand(null);
	}
	
	protected void setFirstOperand(MatrixTO firstOperand) {
		this.firstOperand = firstOperand;
	}
	
	protected void setSecondOperand(MatrixTO secondOperand) {
		this.secondOperand = secondOperand;
	}
	
	/**
	 * Returns the first correctly entered matrix.
	 * 
	 * @return the first matrix operand.
	 */
	public MatrixTO getFirstOperand() {
		return firstOperand;
	}
	
	/**
	 * Returns the second correctly entered matrix.
	 *
	 * @return the second matrix operand.
	 */
	public MatrixTO getSecondOperand() {
		return secondOperand;
	}
	
	/**
	 * Determines if both operands are successfully requested from the user.
	 * 
	 * @return true, if both matrices correctly entered.
	 */
	public boolean hasOperands() {
		return getFirstOperand() != null && getSecondOperand() != null;
	}
}
