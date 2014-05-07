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
	 * Decouples the creation of a matrix instance from its concrete implementation.
	 * This is a GoF Design Pattern called Factory. http://de.wikipedia.org/wiki/Fabrikmethode
	 * 
	 * @author Yves Kaufmann
	 *
	 */
	public interface Factory {
		
		/**
		 * <p>
		 * Create a new matrix with the specified dimension and fills 
		 * her elements with zero. 
		 * </p>
		 * @param rows <br>the Matrix's rows number.<br>
		 * 			   Must be inside the interval [1,7].
		 * @param cols <br>the Matrix's columns number.<br>
		 * 			   Must be inside the interval [1,7].
		 * @return the new created matrix instance.
		 */
		Matrix createMatrix(int rows, int cols);
		
		/**
		 * <p>
		 * Create a new matrix by the specified data array,
		 * which contains all elements of the array.
		 * </p>
		 * 
		 * @param data the Elements of the array.
		 * @return the new created matrix instance.
		 */
		Matrix createMatrixFromArray(double [][] data);
	}
	
	/**
	 * <p>
	 * Adds a scalar 'value' to the specified matrix element.<br>
	 * Basically, the following formula will be calculated.<br>
	 *  <br>
	 * M<sub>ij</sub>  = M<sub>ij</sub> + value <br>
	 * </p>
	 * 
	 * @param row the matrix element's row index
	 * @param col the matrix element's column index
	 * @param scalarValue The value that is added to the element.
	 * @return The resulting matrix.
	 */
	public Matrix addScalar(int row, int col, double scalarValue);
	
	/**
	 * <p>
	 * Performs a matrix addition by the following formula and 
	 * returns the instance of the resulting matrix.<br> 
	 * <br>
	 * A + B := (a<sub>ij</sub> + b<sub>ij</sub>)<sub>i=1,...,m; j=1,...,n</sub><br>
	 * </p>
	 * 
	 * @param otherMatrix the matrix that is added to this matrix.
	 * @return the resulting matrix.
	 * @throws IllegalMatrixComputationException if the dimension of both matrices are not equal.
	 */
	public Matrix add(Matrix otherMatrix) throws IllegalMatrixComputationException;
	
	/**
	 * <p>
	 * Performs a matrix scalar multiplication  by the following formula 
	 * and returns the instance of the resulting matrix.<br>
	 * <br>
	 * &lambda; * A := (&lambda; * a<sub>ij</sub>)<sub>i=1,...,m j=1,...,n</sub>
	 * </p>
	 * 
	 * @param scalarValue the scalar value which should use for the scalar multiplication.
	 * @return the instance of the resulting matrix.
	 */
	public Matrix multiplyBy(double scalarValue);
	
	/**
	 * <p>
	 * Performs a matrices multiplication by the following formula
	 * and returns the resulting matrix instance. <br> 
	 * Checks if the multiplication of both matrices is possible
	 * otherwise a IllegalMatrixComputation Exception will be thrown.
	 * <br>
	 * <br>
	 * A<sup>l x m</sup> X B<sup>m x n</sup> -&gt; C<sup>l x n</sup><br>
	 * c<sub>ik</sub> = \[ \sum_{k=0}^m \] a<sub>ij</sub> * b<sub>jk</sub><br>
	 * </p>
	 * @param otherMatrix the matrix 'B' which should use for the matrix multiplication.  
	 * @return the new resulting matrix instance. 
	 * @throws IllegalMatrixComputationException if this count of columns is not equal to another matrix count of rows.
	 */
	public Matrix multiplyBy(Matrix otherMatrix) throws IllegalMatrixComputationException;
	
	/**
	 * <p>
	 * Transpose this matrix by the following formula and returns the resulting formula.<br>
	 * <br>
	 * M<sup>T</sup>  = (m<sub>ji</sub>)<br>
	 * </p>
	 * 
	 * @return the resulting transposed matrix.
	 */
	public Matrix transposition();
	
	/**
	 * <p>
	 * Returns the value of the the specified matrix element.
	 * Perform a bounds check to make sure the specified element is part of the matrix. 
	 * </p>
	 * 
	 * @param row the matrix element's row index.
	 * @param col the matrix element's column index.
	 * @return the specified elements value.
	 */
	public double get(int row, int col);
	
	
	/**
	 * <p>
	 * Returns the value of the the specified matrix element.
	 * Don't performs a bounds check to provide a faster element access; 
	 * </p>
	 * 
	 * @param row the matrix element's row index.
	 * @param col the matrix element's column index.
	 * @return the specified elements value.
	 */
	public double get_unsafe(int row, int col);
	
	/**
	 * <p>
	 * Set the value of the specified matrix element.
	 * Perform a bounds check to make sure the specified element is part of the matrix.
	 * </p>
	 * 
	 * @param row the matrix element's row index. 
	 * @param col the matrix element's column index.
	 * @param value the element's new value.
	 */
	public void set(int row, int col, double value);
	
	/**
	 * <p>
	 * Set the value of the specified matrix element.
	 * Don't performs a bounds check to provide a faster element write access; 
	 * </p>
	 * 
	 * @param row the matrix element's row index. 
	 * @param col the matrix element's column index.
	 * @param value the element's new value.
	 */
	public void set_unsafe(int row, int col, double value);
	
	/**
	 * <p>
	 * Returns the number of rows in this matrix.
	 * </p>
	 * 
	 * @return the number of rows.
	 */
	public int getNumRows();
	
	/**
	 * <p>
	 * Returns the number of columns in this matrix
	 * </p>
	 * @return the number of columns.
	 */
	public int getNumCols();
	
	/**
	 * Checks if the specified matrix element is inside this matrix.
	 * 
	 * @param row the matrix element's row index.
	 * @param col the matrix elements's column index.
	 * @return <code>true</code> if the specified element is inside this matrix.
	 */
	public boolean isInBounds(int row, int col);
	
	/**
	 * <p>
	 * Convert the matrix into a array representation.
	 * </p>
	 * 
	 * @return the array representation. 
	 */
	public double[][] toArray();
	
	/**
	 * <p>
	 * Create and returns a copy of this matrix.
	 * </p>
	 * 
	 *  @return the copy of this matrix instance.
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
	 * @return the string representation of the matrix.
	 */
	@Override
	public String toString();
	
}
