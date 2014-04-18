package org.yvka.Beleg1.operations;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Formatter;

import org.yvka.Beleg1.data.Matrix;

/**
 * <p>
 * This static class provides io operations for matrices
 * like matrix parsing and print.
 * </p>
 * @author Yves Kaufmann
 *
 */
public class MatrixIO {
	
	/**
	 * Default display format for matrix elements which is described in
	 * the {@link Formatter Format string syntax}.   
	 */
	public static final String DEFAULT_ELEMENT_FORMAT = "%4.2f";
	
	
	/**
	 * Disables the default constructor because this class is for static usage only.
	 */
	protected MatrixIO() {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * <p>
	 * Create the string representation of a specified matrix for display purposes.<br>
	 * The matrix elements are formatted with the {@link MatrixIO#DEFAULT_ELEMENT_FORMAT default format}.
	 * </p>
	 * 
	 * @param matrix the specified matrix which should convert into a string.
	 * @return the string representation of the specified matrix.
	 */
	public static String toString(Matrix matrix) {
		return MatrixIO.toString(matrix, DEFAULT_ELEMENT_FORMAT);
	}
	
	/**
	 * <p>
	 * Print the specified matrix to the standard output stream.<br>
	 * The matrix elements are formatted with the {@link MatrixIO#DEFAULT_ELEMENT_FORMAT default format}.
	 * </p>
	 * @param matrix the specified matrix which should print to the standard output stream.
	 */
	public static void print(Matrix matrix) {
		MatrixIO.print(matrix, System.out);	
	}
	
	/**
	 * <p>
	 * Print the specified matrix to the specified {@link PrintStream}.<br>
	 * The matrix elements are formatted with the {@link MatrixIO#DEFAULT_ELEMENT_FORMAT default format}.
	 * </p>
	 * @param matrix the specified matrix which should print to the specified out stream.
	 * @param out the specified PrintStream.
	 */
	public static void print(Matrix matrix, PrintStream out) {
		MatrixIO.print(matrix, DEFAULT_ELEMENT_FORMAT);	
	}
	
	/**
	 * <p>
	 * Print the specified matrix to the standard output stream.<br>
	 * Each matrix element is formatted by a specified string format.
	 * </p>
	 * @param matrix the specified matrix.
	 * @param elementFormat the matrix elements format string as described in the {@link Formatter Format string syntax}.
	 */
	public static void print(Matrix matrix, String elementFormat) {
		MatrixIO.print(matrix, System.out, elementFormat);	
	}
	
	/**
	 * <p>
	 * Create the string representation of a specified matrix for display purposes.<br>
	 * Each matrix element is formatted by a specified string format.
	 * </p>
	 * 
	 * @param matrix the specified matrix.
	 * @param elementFormat the matrix elements format string as described in the {@link Formatter Format string syntax}.
	 * @return the string representation of the specified matrix.
	 */
	public static String toString(Matrix matrix, String elementFormat) {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		try (PrintStream ps = new PrintStream(outStream)) {
			MatrixIO.print(matrix, ps, elementFormat);
			return outStream.toString();
		}
	}

	/**
	 * <p>
	 * Print the specified matrix to the specified {@link PrintStream}.<br>
	 * Each matrix element is formatted by a specified string format.
	 * </p>
	 * @param matrix the specified matrix.
	 * @param out the specified PrintStream.
	 * @param elementFormat the matrix elements format string as described in the {@link Formatter Format string syntax}.
	 */
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
