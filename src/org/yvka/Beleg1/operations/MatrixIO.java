package org.yvka.Beleg1.operations;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.yvka.Beleg1.data.Matrix;

public class MatrixIO {
	
	public static final String DEFAULT_ELEMENT_FORMAT = "%4.2f";
	
	public static String toString(Matrix matrix) {
		return MatrixIO.toString(matrix, DEFAULT_ELEMENT_FORMAT);
	}
	
	public static void print(Matrix matrix) {
		MatrixIO.print(matrix, System.out);	
	}
	
	public static void print(Matrix matrix, PrintStream out) {
		MatrixIO.print(matrix, DEFAULT_ELEMENT_FORMAT);	
	}
	
	public static void print(Matrix matrix, String format) {
		MatrixIO.print(matrix, System.out, format);	
	}
	
	public static String toString(Matrix matrix, String format) {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		try (PrintStream ps = new PrintStream(outStream)) {
			MatrixIO.print(matrix, ps, format);
			return outStream.toString();
		}
	}

	public static void print(Matrix matrix, PrintStream out, String elementFormat) {
		elementFormat+= " ";
		for(int row = 0; row < matrix.getNumRows(); row++) {
			for(int col = 0; col < matrix.getNumCols(); col++) {
				out.format(elementFormat, matrix.get_unsafe(row, col));
			}
			out.println();
		}
	}
	
}
