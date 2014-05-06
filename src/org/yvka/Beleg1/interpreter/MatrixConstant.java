package org.yvka.Beleg1.interpreter;
import org.yvka.Beleg1.data.Matrix;
import org.yvka.Beleg1.ui.Context;


public class MatrixConstant extends MatrixExpression {
	
	private Matrix matrixConstant = null;
	
	public MatrixConstant(final Matrix matrix) {
		matrixConstant = matrix.copy();
	}
	
	@Override
	public Matrix getValue(Context context) throws MatrixRuntimeException {
		return matrixConstant;
	}
}
