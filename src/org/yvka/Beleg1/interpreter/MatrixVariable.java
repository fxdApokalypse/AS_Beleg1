package org.yvka.Beleg1.interpreter;
import org.yvka.Beleg1.data.Matrix;
import org.yvka.Beleg1.ui.Context;


public class MatrixVariable extends MatrixExpression {
	
	private String name;
	
	public MatrixVariable(String name) {
		this.name = name;
	}
	
	@Override
	public Matrix getValue(Context context) throws MatrixRuntimeException {
		if(!context.hasMatrix(name)) {
			throw new MatrixRuntimeException("Unknown matrix variable '" + name + "'.");
		}
		return context.getMatrix(name);
 	}

}
