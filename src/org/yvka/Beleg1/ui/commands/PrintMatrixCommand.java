package org.yvka.Beleg1.ui.commands;

import java.util.Objects;

import org.yvka.Beleg1.operations.MatrixIO;
import org.yvka.Beleg1.ui.Application;
import org.yvka.Beleg1.ui.Command;
import org.yvka.Beleg1.ui.MatrixTO;
import org.yvka.Beleg1.utils.StringUtil;

public class PrintMatrixCommand implements Command {

	private MatrixTO matrixTO = null;
		
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
