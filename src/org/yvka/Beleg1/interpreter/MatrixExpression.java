package org.yvka.Beleg1.interpreter;
import org.yvka.Beleg1.data.IllegalMatrixComputationException;
import org.yvka.Beleg1.data.Matrix;
import org.yvka.Beleg1.ui.Context;


public class MatrixExpression extends Expression<Matrix> {
	
	protected MatrixExpression() {
		super();
	}
	
	public MatrixExpression(Expression.Operation op, MatrixExpression leftOperand, NumberExpression rightOperand) {
		super(op, leftOperand, rightOperand);
	}
	
	public MatrixExpression(Expression.Operation op, MatrixExpression leftOperand, MatrixExpression rightOperand) {
		super(op, leftOperand, rightOperand);
	}
	
	public MatrixExpression(Expression.Operation op, MatrixExpression operand) {
		super(op, operand);
	}

	@Override
	public Matrix getValue(Context context) throws MatrixRuntimeException {	
		try {
			return handleOperations(context);
		} catch(IllegalMatrixComputationException ex) {
			throw new MatrixRuntimeException("Illegal matrix operation: "+ ex.getMessage());
		}
	}
	
	@Override
	public String getValueAsString(Context context)
			throws MatrixRuntimeException {
		return getValue(context).toString();
	}

	private Matrix handleOperations(Context context) throws MatrixRuntimeException, 
														IllegalMatrixComputationException {
		double factor = 1.0;
		MatrixExpression left = (MatrixExpression) getLeft();
		MatrixExpression right = null;
		NumberExpression rightScalar = null;
		
		if( getRight() instanceof MatrixExpression ) {
			right = (MatrixExpression) getRight();
		} else if (getRight() instanceof NumberExpression) {
			rightScalar = (NumberExpression) getRight();
		} 
		switch(getOperation()) {
			case SUB : 
				factor = -1.0;
			case ADD :			
				return left.getValue(context).add(right.getValue(context).multiplyBy(factor));
			case MUL :
				if(right != null)
					return left.getValue(context).multiplyBy(right.getValue(context));
				if(rightScalar != null)
					return left.getValue(context).multiplyBy(rightScalar.getValue(context));
				return null;
			case NEG :
				return right.getValue(context).multiplyBy(-1.0);
			default:
				throw new MatrixRuntimeException("Illegal operator in expression");
		}
	}
	
}
