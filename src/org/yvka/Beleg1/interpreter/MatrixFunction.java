package org.yvka.Beleg1.interpreter;
import org.yvka.Beleg1.data.Matrix;
import org.yvka.Beleg1.ui.Context;


public class MatrixFunction extends MatrixExpression {
	
	public enum FunctionType {
		NONE("<NULL>"),
		TRANSPOSE("transpose");
		
		private final String title;
		
		FunctionType(String title) {
			this.title = title;
		}
		
		@Override
		public String toString() {
			return title;
		}		
	}
	private FunctionType function; 
	
	public MatrixFunction(FunctionType function, MatrixExpression operand) {
		super(Operation.NONE, operand);
		this.function = function;
	}
	
	@Override
	public Matrix getValue(Context context) throws MatrixRuntimeException {
		switch(function) {
			case TRANSPOSE : 
				return ((MatrixExpression)getRight()).getValue(context).transposition();
			default: 
				throw new MatrixRuntimeException("Unknown or non-matrix function.");
		}
	}
}
