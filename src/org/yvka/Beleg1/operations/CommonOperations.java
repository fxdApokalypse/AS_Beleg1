package org.yvka.Beleg1.operations;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.stream.Stream;

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
	
	private static class CountOfZeroRows implements Consumer<double []> {
		private int zeroRows = 0;
		@Override
		public void accept(double[] matrixRow) {
			if(Arrays.stream(matrixRow).sum() == 0) {
				zeroRows++;
			}
		}
	}
	
	/**
	 * <p>
	 * Determine the rank of a specified matrix by the 
	 * Gaussian elimination.
	 * </p>
	 * @param matrix The matrix on which this operation should work.
	 * @return The rank of the specified matrix.
	 */
	public static int determineRankOfMatrix(Matrix matrix) {
		int maxRank = Integer.min(matrix.getNumRows(), matrix.getNumRows());
		matrix = CommonOperations.convertToRowEchelonForm(matrix.copy());
		
		Stream<double[]> stream = Arrays.stream(matrix.toArray());  
		CountOfZeroRows countOfZeroRows = new CountOfZeroRows();
		stream.forEach(countOfZeroRows);
		
		return (int) (maxRank - countOfZeroRows.zeroRows);
	}
	
	/**
	 * Convert a specified matrix into the row echelon form which describe is the shape 
	 * resulting of a Gaussian Elimination.
	 *   
	 * @param matrix the matrix on which this operation should work.
	 * @return the specified matrix in row echelon form. 
	 */
	public static Matrix convertToRowEchelonForm(Matrix matrix) {
		int rows = matrix.getNumRows(); 
		int cols = matrix.getNumCols();
		double [][]_matrix = matrix.toArray();
		
		for(int piviotRow = 0; piviotRow < rows;piviotRow++) {
			
			if (_matrix[piviotRow][piviotRow] == 0.0) {
				ArrayUtils.swapRows(_matrix, piviotRow, rows - 1);
			}
			
			for(int i = piviotRow + 1; i < rows; i++) {
				double multipleOfPiviot = (_matrix[i][piviotRow] / _matrix[piviotRow][piviotRow]);
			    for(int piviotCol = piviotRow; piviotCol < cols; piviotCol++) {
			    	_matrix[i][piviotCol] = _matrix[i][piviotCol] - _matrix[piviotRow][piviotCol] * multipleOfPiviot;
			    }
			    _matrix[i][piviotRow] = 0.0;
		    }
		}
		
		CommonOperations.fillByArray(matrix, _matrix);
		
		return matrix;
	}
}
