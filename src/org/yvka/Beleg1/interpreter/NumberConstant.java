package org.yvka.Beleg1.interpreter;
import org.yvka.Beleg1.ui.Context;


public class NumberConstant extends NumberExpression {
	private double value;
	public NumberConstant(double value) {
		this.value = value;
	}
	
	@Override
	public Double getValue(Context context) throws MatrixRuntimeException {
		return value;
	}

	
	
	
}
