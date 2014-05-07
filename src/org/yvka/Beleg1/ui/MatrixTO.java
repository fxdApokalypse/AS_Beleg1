package org.yvka.Beleg1.ui;

import org.yvka.Beleg1.data.Matrix;

/**
 * A matrix transfer object which is intend to transfer matrices between {@link ApplicationCommand} objects.
 * 
 * @author Yves Kaufmann
 *
 */
public class MatrixTO {
	protected Matrix matrix = null;
	protected String name = null;
	
	/**
	 * Create a matrix transfer object.
	 *  
	 * @param matrix the matrix which should be transfered.
	 * @param name the name of the matrix.
	 */
	public MatrixTO(Matrix matrix, String name) {
		super();
		this.matrix = matrix;
		this.name = name;
	}
	
	/**
	 * Returns the matrix.
	 * 
	 * @return the matrix object.
	 */
	public Matrix getMatrix() {
		return matrix;
	}
	
	/**
	 * Returns the name of the matrix.
	 * 
	 * @return the name of the matrix.
	 */
	public String getName() {
		return name;
	}

}
