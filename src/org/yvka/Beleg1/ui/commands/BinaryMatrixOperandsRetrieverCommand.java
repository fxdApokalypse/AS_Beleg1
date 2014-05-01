package org.yvka.Beleg1.ui.commands;

import org.yvka.Beleg1.ui.Application;
import org.yvka.Beleg1.ui.ApplicationCommand;
import org.yvka.Beleg1.ui.MatrixTO;

public class BinaryMatrixOperandsRetrieverCommand extends ApplicationCommand  {
	
	private MatrixTO firstOperand = null;
	private MatrixTO secondOperand = null;
	
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
	
	public MatrixTO getFirstOperand() {
		return firstOperand;
	}
	
	public MatrixTO getSecondOperand() {
		return secondOperand;
	}
	
	public boolean hasOperands() {
		return getFirstOperand() != null && getSecondOperand() != null;
	}
}
