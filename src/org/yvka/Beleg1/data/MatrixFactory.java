package org.yvka.Beleg1.data;

/**
 * Enables applications to obtain a matrix instance.
 * 
 * @author Yves Kaufmann
 *
 */
public class MatrixFactory implements Matrix.Factory {
	
	protected final static MatrixFactory INSTANCE = new MatrixFactory();
	protected MatrixFactory() {}
	
	@Override
	public Matrix createMatrix(int rows, int cols) {
		return new MatrixImpl(rows, cols);
	}
	public Matrix createMatrixFromArray(double[][] data) {
		return new MatrixImpl(data);
	}; 
	
	/**
	 * Returns the Default Factory.
	 * 
	 * @return the matrix factory.
	 */
	public static  MatrixFactory get() {
		return INSTANCE;
	}
}
