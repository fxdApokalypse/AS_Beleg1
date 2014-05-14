package org.yvka.Beleg1.ui.commands;

import java.util.Objects;

import org.yvka.Beleg1.matrix.operations.MatrixIO;
import org.yvka.Beleg1.ui.Command;
import org.yvka.Beleg1.ui.MatrixTO;
import org.yvka.Beleg1.utils.StringUtil;

/**
 * <p>
 * A command which enables a caller to print a matrix.
 * </p>
 * @author Yves Kaufmann
 *
 */
public class PrintMatrixCommand implements Command {

	private MatrixTO matrixTO = null;
	
	/**
	 * Creates PrintCommand and assigns the MatrixTO containing the matrix
	 * which should be printed.
	 *  
	 * @param matrix the matrixTO of a matrix.
	 */
	public PrintMatrixCommand(MatrixTO matrix) {
		this.matrixTO = matrix;
		Objects.requireNonNull(matrixTO);
	}
	
	@Override
	public void execute(String... args) {
		String matrixString = MatrixIO.toString(matrixTO.getMatrix());
		matrixString = StringUtil.wrapWithVerticalBorders(matrixString, matrixTO.getName());
		System.out.println(matrixString);
	}
}
