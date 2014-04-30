package org.yvka.Beleg1.operations;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Formatter;
import java.util.Scanner;
import java.util.function.ToDoubleFunction;

import org.yvka.Beleg1.data.Matrix;
import org.yvka.Beleg1.data.MatrixImpl;

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
	public static final String DEFAULT_ELEMENT_FORMAT = "% 4.2f";
	
	
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
	
	/**
	 * <p>
	 * Parse a matrix form the specified InputStream.<br>
	 * <br>
	 * The matrix is stored with the following syntax: <br>
	 * <br>
	 * <code>
	 * 	1,2,3,
	 *  7,8,9
	 * </code>
	 * </p>
	 * @param in the <code>InputStream</code> from which the matrix should read.  
	 * @return a matrix instance, if the operation was successful.
	 * @throws InvalidMatrixFormatException if the stream is not in the required format.
	 * 
	 */
	public static Matrix parse(InputStream in) throws InvalidMatrixFormatException {
		ArrayList<double[]> matrix = new ArrayList<>();
		String line = null;
		
		final NumberFormat format = NumberFormat.getInstance();
		ToDoubleFunction<String> parseNumber = 
			(String number) -> {
				try {
					return format.parse(number).doubleValue();
				} catch(ParseException ex) {
					throw new RuntimeException(ex.getMessage(), ex);
				}
		};
		
		try(Scanner reader = new Scanner(in)) {
			while((line = reader.nextLine()) != null && !line.isEmpty()) {
				
				double []numbers  = Arrays.stream(line.split(" "))
				.map(String::trim)
				.mapToDouble(parseNumber).toArray();
				
				if(numbers.length <= 0) {
					throw new InvalidMatrixFormatException("Invalid Format: Each row must have the same count of numbers.");
				}
				
				if(matrix.size() > 0 && matrix.get(0).length != numbers.length) {
					throw new InvalidMatrixFormatException("Invalid Format: Each row must have the same count of numbers.");
				}
				
				matrix.add(numbers);		
			}
		} catch(NumberFormatException ex) {
			throw new InvalidMatrixFormatException("Invalid Format: The specified matrix element ins't a number.", ex);
		}
		
		if(matrix.size() == 0 || matrix.get(0).length == 0) {
			throw new InvalidMatrixFormatException("Invalid Format: The matrix must at least one row and one column.");
		}
		
		return new MatrixImpl(matrix.toArray(new double[0][0]));
	}

}
