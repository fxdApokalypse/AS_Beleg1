package org.yvka.Beleg1.interpreter;
import org.yvka.Beleg1.data.Matrix;

public class Token {
	public enum Type {
		SYMBOL,
		MATRIX_CONSTANT,
		NUMERIC_CONSTANT,
		MATRIX_VAIRABLE,
		NUMERIC_VARIABLE,
		MATRIX_FUNCTION,
		NUMERIC_FUNCTION,
		OPERATOR,
		EOL,
		ERROR
	}
	
	protected Type type;
	protected Matrix matrixValue;
	protected double doubleValue;
	protected String stringValue;
	private Expression.Operation operationValue;
	
	public Token(Type type, String value) {
		super();
		this.type = type;
		this.stringValue = value;
	}
	
	public Token(Type type, Matrix matrixValue) {
		super();
		this.type = type;
		this.matrixValue = matrixValue;
	}
	
	public Token(Type type, double value) {
		super();
		this.type = type;
		this.doubleValue = value;
	}
	
	public Token(Type type, int value) {
		super();
		this.type = type;
		this.doubleValue = (double)value;
	}

	public Token(Type type, Matrix matrixValue, double doubleValue) {
		super();
		this.type = type;
		this.matrixValue = matrixValue;
		this.doubleValue = doubleValue;
	}

	public Token(Type type, String string, Expression.Operation operation) {
		this.type = type;
		this.stringValue = string;
		this.operationValue = operation;
	}

	public Type getType() {
		return type;
	}

	public Matrix getMatrixValue() {
		return matrixValue;
	}

	public double getDoubleValue() {
		return doubleValue;
	}
	
	public String getStringValue() {
		return this.stringValue;
	}
	
	public Expression.Operation getOperator() {
		return this.operationValue;
	}
	
	static final boolean isSymbol(Token t, char s) {
        return ((t != null) && (Type.SYMBOL.equals(t.getType())) && (t.getDoubleValue() == s));
    }

    final boolean isSymbol(char c) {
        return isSymbol(this, c);
    }

    final boolean isOp(int op) {
        return ((Type.OPERATOR.equals(type)) && ((int) getDoubleValue() == op));
    }
    
    final boolean isType(Type type) {
    	return this.type.equals(type);
    }

}
