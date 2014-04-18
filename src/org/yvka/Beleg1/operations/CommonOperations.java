package org.yvka.Beleg1.operations;

import java.util.Arrays;

import org.yvka.Beleg1.data.Matrix;
import org.yvka.Beleg1.data.MatrixImpl;
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
	 * Disables the default constructor because this class is for static usage only.
	 */
	protected CommonOperations() {
		throw new UnsupportedOperationException();
	}
	
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
	
	/**
	 * <p>
	 * Determines the high valued sum of row elements of a specified matrix.<br>
	 * For example if this operation is applied to matrix A this functions returns <b>4</b>.
	 * </p>
	 * <br>
	 * <table summary="Example max row sum">
	 * 	<tr>
	 * 		<td rowspan="2">A = </td>
	 * 		<td style="border-left: 1px solid black;">1</td>
	 * 		<td style="border-right: 1px solid black;">1</td>
	 * 	</tr>
	 * 	<tr>
	 * 		<td style="border-left: 1px solid black;">1</td>
	 * 		<td style="border-right: 1px solid black;">3</td>
	 * 	</tr>
	 * </table>
	 * 
	 * 
	 * @param matrix The Matrix on which this operation should work.
	 * @return The high valued row sum.
	 */
	public static double determineMaxRowSum(Matrix matrix) {
		double [][]array = matrix.toArray();
		return Arrays.stream(array)
			.mapToDouble((x) -> Arrays.stream(x).sum())
			.max().getAsDouble();
	} 
	
	/**
	 * <p>
	 * Determine the rang of a specified matrix by the 
	 * Gaussian elimination.
	 * </p>
	 * @param matrix The matrix on which this operation should work.
	 * @return The rang of the specified matrix.
	 */
	public static int determineRangOfMatrix(Matrix matrix) {
		int rows = matrix.getNumRows(); 
		int cols = matrix.getNumCols();
		int maxRang = Integer.max(rows, cols);
		double [][]_matrix = matrix.toArray();		
		for(int k = 0; k < rows;k++) {
			//Find pivot for column k:
			int i_max = 0;
			double max_value = Double.MIN_VALUE;
			for(int h = k; h < rows; h++) {
				double currValue = _matrix[h][k];
				if( currValue > max_value ) {
					i_max = h;
					max_value = currValue;
				}
			}
			//int i_max  = argmax (i = k ... m, Math.abs(_matrix[i][k]]));
			if (_matrix[i_max][k] == 0) {
				throw new RuntimeException("Matrix is singular!");
			}
			ArrayUtils.swapRows(_matrix, k, i_max);		
			   		  
			for(int i = k + 1; i < rows; i++) {
			    for(int j = k; j < cols; j++) {
			    	_matrix[i][j] = _matrix[i][j] - _matrix[k][j] * (_matrix[i][k] / _matrix[k][k]);
			    }
			    // Fill lower triangular matrix with zeros:
			    _matrix[i][k] = 0.0;
		    }
		}
		
		
		MatrixIO.print(new MatrixImpl(_matrix));
		
		int rang = (int) (maxRang-Arrays.stream(_matrix)
										.mapToDouble(x->Arrays.stream(x).sum())
										.filter(x-> x == 0.0).count());
		
		System.out.println(rang);
		return rang;
	}
}
