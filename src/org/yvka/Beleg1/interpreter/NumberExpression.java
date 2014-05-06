package org.yvka.Beleg1.interpreter;
import org.yvka.Beleg1.data.IllegalMatrixComputationException;
import org.yvka.Beleg1.ui.Context;


public class NumberExpression extends Expression<Double> {
	
	protected NumberExpression() {
		super();
	}
	
	public  NumberExpression(Expression.Operation op, NumberExpression left, NumberExpression right) {
		super(op, left, right);
	}
	
	public  NumberExpression(Expression.Operation op, NumberExpression operand) {
		super(op, operand);
	}

	@Override
	public Double getValue(Context context) throws MatrixRuntimeException {
		try {
			return handleOperations(context);
		} catch(IllegalMatrixComputationException ex) {
			throw new MatrixRuntimeException("Illegal matrix operation: "+ ex.getMessage());
		}		
	}

	
	private Double handleOperations(Context context) throws MatrixRuntimeException, 
														IllegalMatrixComputationException {
		NumberExpression left = (NumberExpression) getLeft();
		NumberExpression right = (NumberExpression) getRight();
		
		switch(getOperation()) {
			case SUB : 
				return left.getValue(context) - right.getValue(context);
			case ADD :			
				return left.getValue(context) + right.getValue(context);
			case DIV : 
				
				double rightValue = right.getValue(context);
				if(Double.compare(rightValue, 0.0) == 0) {
					throw new MatrixRuntimeException("Illegal operation: division through zero is not allowed.");
				}
				return left.getValue(context) / rightValue;
				
			case MUL :
				return left.getValue(context) * right.getValue(context);
			case NEG :
				return 0 - right.getValue(context);
			default:
				throw new MatrixRuntimeException("Illegal operator in expression");
		}
		
	}
	
	@Override
	public String getValueAsString(Context context)
			throws MatrixRuntimeException {
		return getValue(context).toString();
	}
	
	

}
