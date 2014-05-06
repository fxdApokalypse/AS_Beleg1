package org.yvka.Beleg1.interpreter;
import org.yvka.Beleg1.ui.Context;


public abstract class Expression<RETURN_TYPE> {
	public enum Operation {
		NONE("<NULL>"),
		ADD ("+"),
		SUB ("-"),
		MUL ("*"),
		DIV ("/"),
		NEG	("-"); 
		
		private final String title;
		
		Operation(String title) {
			this.title = title;
		}
		
		@Override
		public String toString() {
			return title;
		}		
	}
	
	final static String operationStrings[] = {
        "<NULL>", "+", "-", "*", "/", "-"
    };
	
	protected Expression<?> operandLeft;
	protected Expression<?> operandRight;
	private Operation operation;
	
	protected Expression() {};
	
	protected Expression(Operation op, Expression<?> operand) {
		
		this.operation = op;
		this.operandRight = operand;
	};
	
	protected Expression(Operation op, Expression<?> operandLeft, Expression<?> operandRight) {
		this.operation = op;
		this.operandLeft = operandLeft;
		this.operandRight = operandRight;
	};
	
	abstract public RETURN_TYPE getValue(Context context) throws MatrixRuntimeException;
	abstract public String getValueAsString(Context context) throws MatrixRuntimeException;
	
	protected Operation getOperation() {
		return operation;
	}
	
	protected Expression getLeft() {
		return operandLeft;
	}
	
	protected Expression getRight() {
		return operandRight;
	}
	
	@Override
	public String toString() {
		StringBuffer strBuffer = new StringBuffer();
		strBuffer.append("(");
		if(operandLeft != null) {
			strBuffer.append(operandLeft);
		}
		strBuffer.append(operation);
		strBuffer.append(operandRight);
		strBuffer.append(")");
		return strBuffer.toString();
	}
}
