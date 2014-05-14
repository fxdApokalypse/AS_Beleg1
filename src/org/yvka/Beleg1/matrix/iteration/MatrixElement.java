package org.yvka.Beleg1.matrix.iteration;

/**
 * <p>
 * Simple data structure which contains
 * the row, column index and value of a matrix element.
 * <br>
 * This not clear encapsulated class is designed for iteration 
 * purposes and should not use for any other purpose.<br>
 * <br>
 * </p>
 * @author Yves Kaufmann
 *
 */
public class MatrixElement {
	/**
	 * Matrix element's row index.
	 */
	public int row;
	
	/**
	 * Matrix element's column index.
	 */
	public int col;
	
	/**
	 * The value of pointed matrix element.
	 */
	public double value;
	
	/**
	 * <p>
	 * Just create a ElementIndex which point to
	 * the element at the index [row=0,col=0].
	 *  </p>
	 * @param row the Matrix element's row index.
	 * @param col the Matrix element's column index.
	 */
	MatrixElement() {
		this(0,0);
	}
	
	/**
	 * <p>
	 * Just create a ElementIndex by specify the elements row and column index. 
	 *  </p>
	 *  
	 * @param row the Matrix element's row index.
	 * @param col the Matrix element's column index.
	 */
	MatrixElement(int row, int col) {
		this.row = row;
		this.col = col;
	}

}
