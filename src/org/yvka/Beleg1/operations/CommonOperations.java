package org.yvka.Beleg1.operations;

import org.yvka.Beleg1.data.Matrix;
import org.yvka.Beleg1.data.iteration.MatrixElement;
import org.yvka.Beleg1.utils.ArrayUtils;
import org.yvka.Beleg1.utils.ArrayUtils.ArrayDimensions;

/**
 * List of common used matrix operations.
 * 
 * @author Yves Kaufmann
 *
 */
public class CommonOperations {
	
	
	/**
	 * <p>
	 * Set the value of each element of the matrix to the corresponding value inside an array.<br>
	 * Only these elements be taken over which are in the bounds of the matrix.<br>
	 * <br>
	 * This operation works 'in place' on the specified matrix instance and returns 
	 * the same matrix instance.  
	 *  </p>
	 * @param matrix The Matrix on which this operation should work.
	 * @param data The specified array which contains the element values.
	 * @return The specified Matrix instance.
	 */
	public static Matrix fillByArray(Matrix matrix, double[][] data) {
		ArrayDimensions dim = ArrayUtils.getDimension(data);
		dim.ensureIsNotEmpty();
		
		for(MatrixElement el : matrix) {
			
			if(!dim.isIndexInBound(el.row, el.col)) continue; 
			
			// ensure that the specified array has in each row the same count of cells/columns.
			if(data[el.row].length != dim.cols) {
				throw new IllegalArgumentException(
					"Invalid data array: rows have different columns."
				);
			}
			
			matrix.set_unsafe(el.row, el.col, data[el.row][el.col]);
		}	
		return matrix;
	}
	
	/**
	 * <p>
	 * Set the value of each element of the matrix to a specified value.<br>
	 * <br>
	 * This operation works 'in place' on the specified matrix instance and returns<br> 
	 * the same matrix instance. <br>
	 * </p>
	 * @param matrix The Matrix on which this operation should work.
	 * @param value The specified value.
	 * @return The specified matrix instance. 
	 */
	public static Matrix fill(Matrix matrix, double value) {
		for(MatrixElement el : matrix) {
			matrix.set_unsafe(el.row, el.col, value);
		}
		return matrix;
	}
	
	/**
	 * <p>
	 * Set the value of each element of the matrix to zero.<br>
	 * <br>
	 * This operation works 'in place' on the specified matrix instance and returns<br> 
	 * the same matrix instance. <br>
	 * </p>
	 * @param matrix The Matrix on which this operation should work.
	 * @return The specified matrix instance.
	 */
	public static Matrix zero(Matrix matrix) {
		return fill(matrix,0.0);
	}
}
