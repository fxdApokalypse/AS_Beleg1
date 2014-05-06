package org.yvka.Beleg1.interpreter;
import org.yvka.Beleg1.ui.Context;


public class NumberVariable extends NumberExpression {
	
	private String varName;
	
	public NumberVariable(String name) {
		this.varName = name;
	}
	
	@Override
	public Double getValue(Context context) throws MatrixRuntimeException {
		if(!context.hasNumber(varName)) {
			throw new MatrixRuntimeException("Unknown number variable '" + varName + "'.");
		}
		return context.getNumber(varName);
	}
	
}
