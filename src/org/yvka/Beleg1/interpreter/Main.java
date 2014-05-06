package org.yvka.Beleg1.interpreter;


import java.util.Stack;

import org.yvka.Beleg1.data.MatrixImpl;
import org.yvka.Beleg1.ui.Context;





public class Main {

	public static void main(String[] args) throws MatrixRuntimeException {
		Context context = new Context();
		context.putMatrix("m1", new MatrixImpl(new double[][] {{1.0, 1.0, 1.0}}));
		context.putMatrix("m2", new MatrixImpl(new double[][] {{1.0, 2.0, 1.0}}));
		context.putMatrix("m3", new MatrixImpl(new double[][] {{1.0, 1.0, 3.0}}));
		
		String expr = "[[1 2 3],[1 2 3]] +  m2";
		
		Stack<Expression> stack = new Stack<Expression>();
		LexicalTokenizer tokenizer = new LexicalTokenizer(new char[expr.length() + 1]);
		tokenizer.reset(expr);
		while(tokenizer.hasMoreTokens()) {
			Token token = tokenizer.nextToken();
			
			if(token.isType(Token.Type.ERROR)) {
				System.out.println(token.getStringValue());
				return;
			}
			
			if(token.getType().equals(Token.Type.OPERATOR)) {
				Expression.Operation op = token.getOperator();
				if(tokenizer.hasMoreTokens())
					token = tokenizer.nextToken();
				if(token == null || (!token.isType(Token.Type.MATRIX_VAIRABLE) && !token.isType(Token.Type.MATRIX_CONSTANT))) {
					throw new MatrixRuntimeException("missing argument");
				}
				MatrixExpression argumentTwo = token.getType().equals(Token.Type.MATRIX_CONSTANT) ? new MatrixConstant(token.getMatrixValue()) : new MatrixVariable(token.getStringValue());
				MatrixExpression exp = new MatrixExpression(op, (MatrixExpression)stack.pop(), argumentTwo);
				stack.push(exp);
			} else if(token.getType().equals(Token.Type.MATRIX_VAIRABLE)) {
				stack.push(new MatrixVariable(token.getStringValue()));
			} else if(token.getType().equals(Token.Type.MATRIX_CONSTANT)) {
				stack.push(new MatrixConstant(token.getMatrixValue()));
			}
			
		}
		
		System.out.println(stack.pop().getValueAsString(context));
		
	}


}
