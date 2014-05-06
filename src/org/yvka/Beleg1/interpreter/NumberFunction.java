package org.yvka.Beleg1.interpreter;
import org.yvka.Beleg1.data.Matrix;
import org.yvka.Beleg1.operations.CommonOperations;
import org.yvka.Beleg1.ui.Context;


public class NumberFunction extends NumberExpression {
	
	public enum FunctionType {
		NONE("<NULL>"),
		RANK("rank"),
		MAX_ROW_SUM("maxRowSum");
		
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
	
	public NumberFunction(FunctionType function, MatrixExpression operand) {
		this.operandRight = operand;
		this.function = function;
	}
	
	@Override
	public Double getValue(Context context) throws MatrixRuntimeException {
		Matrix matrix = ((MatrixExpression)getRight()).getValue(context);
		switch(function) {
			case RANK :
				return (double) CommonOperations.determineRankOfMatrix(matrix);
			case MAX_ROW_SUM : 
				return CommonOperations.determineMaxRowSum(matrix);
			default: 
				throw new MatrixRuntimeException("Unknown or non-matrix function.");
		}
	}
}
