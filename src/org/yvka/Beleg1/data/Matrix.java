package org.yvka.Beleg1.data;

import org.yvka.Beleg1.data.iteration.MatrixElement;


/**
 * <p>
 * Interface for all 64 bit floating point rectangular matrices.<br>
 * </p>
 * 
 * @author Yves Kaufmann
 */
public interface Matrix extends Iterable<MatrixElement> {

	/**
	 * <p>
	 * Adds a scalar 'value' to the specified matrix element.</br>
	 * Basically, the following formula will be calculated.</br>
	 *  </br>
	 * M<sub>ij</sub>  = M<sub>ij</sub> + value </br>
	 * </p>
	 * 
	 * @param row Matrix element's row index
	 * @param col Matrix element's column index
	 * @param scalarValue The value that is added to the element.
	 * @return The resulting matrix.
	 */
	public Matrix addScalar(int row, int col, double scalarValue);
	
	/**
	 * <p>
	 * Performs a matrix addition by the following formula and 
	 * returns the instance of the resulting matrix.<br> 
	 * <br>
	 * A + B := (a<sub>ij</sub> + b<sub>ij</sub>)<sub>i=1,...,m; j=1,...,n</sub></br>
	 * </p>
	 * 
	 * @param otherMatrix The matrix that is added to this matrix.
	 * @return The resulting matrix.
	 * @throws IllegalMatrixComputation Will be thrown if the dimension of both matrices are different.
	 */
	public Matrix add(Matrix otherMatrix) throws IllegalMatrixComputation;
	
	/**
	 * <p>
	 * Performs a matrix scalar multiplication  by the following formula 
	 * and returns the instance of the resulting matrix.<br>
	 * <br>
	 * &lambda; * A := (&lambda; * a<sub>ij</sub>)<sub>i=1,...,m j=1,...,n</sub>
	 * </p>
	 * 
	 * @param scalarValue The scalar value which should use for the scalar multiplication.
	 * @return The instance of the resulting matrix.
	 */
	public Matrix multiplyBy(double scalarValue);
	
	/**
	 * <p>
	 * Performs a matrices multiplication by the following formula
	 * and returns the resulting matrix instance. <br> 
	 * Checks if the multiplication of both matrices is possible
	 * otherwise a IllegalMatrixComputation Exception will be thrown.
	 * 
	 * <br>
	 * A<sup>l x m</sup> X B<sup>m x n</sup> -> C<sup>l x n</sup><br>
	 * c<sub>ik</sub> = \[ \sum_{k=0}^m \] a<sub>ij</sub> * b<sub>jk</sub><br>
	 * </p>
	 * @param otherMatrix The matrix 'B' which should use for the matrix multiplication.  
	 * @return The new resulting matrix instance. 
	 * @throws IllegalMatrixComputation Will be thrown if a multiplication of both matrices isn't possible.
	 */
	public Matrix multiplyBy(Matrix otherMatrix) throws IllegalMatrixComputation;
	
	/**
	 * <p>
	 * Transpose this matrix by the following formula and returns the resulting formula.<br>
	 * <br>
	 * M<sup>T</sup>  = (m<sub>ji</sub>)<br>
	 * </p>
	 * 
	 * @return The resulting transposed matrix.
	 */
	public Matrix transposition();
	
	/**
	 * <p>
	 * Returns the value of the the specified matrix element.
	 * Perform a bounds check to make sure the specified element is part of the matrix. 
	 * </p>
	 * 
	 * @param row Matrix element's row index.
	 * @param col Matrix element's column index.
	 * @return The specified elements value.
	 */
	public double get(int row, int col);
	
	
	/**
	 * <p>
	 * Returns the value of the the specified matrix element.
	 * Don't performs a bounds check to provide a faster element access; 
	 * </p>
	 * 
	 * @param row Matrix element's row index.
	 * @param col Matrix element's column index.
	 * @return The specified elements value.
	 */
	public double get_unsafe(int row, int col);
	
	/**
	 * <p>
	 * Set the value of the specified matrix element.
	 * Perform a bounds check to make sure the specified element is part of the matrix.
	 * </p>
	 * 
	 * @param row Matrix element's row index. 
	 * @param col Matrix element's column index.
	 * @param value The element's new value.
	 */
	public void set(int row, int col, double value);
	
	/**
	 * <p>
	 * Set the value of the specified matrix element.
	 * Don't performs a bounds check to provide a faster element write access; 
	 * </p>
	 * 
	 * @param row Matrix element's row index. 
	 * @param col Matrix element's column index.
	 * @param value The element's new value.
	 */
	public void set_unsafe(int row, int col, double value);
	
	/**
	 * <p>
	 * Returns the number of rows in this matrix.
	 * </p>
	 * 
	 * @return Number of rows.
	 */
	public int getNumRows();
	
	/**
	 * <p>
	 * Returns the number of columns in this matrix
	 * </p>
	 * @return Number of columns.
	 */
	public int getNumCols();
	
	/**
	 * Checks if the specified matrix element is inside this matrix.
	 * 
	 * @param row Matrix element's row index.
	 * @param col Matrix elements's col index.
	 * @return Returns true if the specified element is inside this matrix.
	 */
	public boolean isInBounds(int row, int col);
	
	/**
	 * <p>
	 * Convert the matrix into a array representation.
	 * </p>
	 * 
	 * @return The array representation. 
	 */
	public double[][] toArray();
	
	/**
	 * <p>
	 * Create and returns a copy of this matrix.
	 * </p>
	 * 
	 *  @return The copy instance of this matrix.
	 */
	public Matrix copy();
	
	/**
	 * <p>
	 * Converts the matrix into a string format for display purposes
	 * and print this string to the PrintStream 'System.out'. 
	 * </p>
	 */
	public void print();
	/**
	 * <p>
	 * Converts the array into a string format for display purposes.
	 * </p>
	 * 
	 * @return String representation of the matrix.
	 */
	@Override
	public String toString();
	
}
