package org.yvka.Beleg1.matrix.iteration;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.yvka.Beleg1.matrix.Matrix;

/**
 * <p>
 * This Iterator is designed to iterate over all elements <br> 
 * of a specified matrix.<br> 
 * <br>
 * The iterator will iterate over each cell from left to right 
 * in each row (starting by the first row).   
 * </p>
 * @author Yves Kaufmann
 * @see Iterator {@link Iterator}
 */
public class MatrixIterator implements Iterator<MatrixElement> {
	
	/**
	 * <p>
	 * The reference of the matrix which should use for iteration.
	 * </p>
	 */
	private Matrix m_Matrix = null;
	
	/**
	 * <p>
	 * The current element index as integer value,
	 * row and column can obtain by using the following formulas.<br>
	 * <br>
	 * row = index / CountOfColumns <br>
	 * col = index % CountOfColumns <br>
	 * </p>
	 */
	private int index = 0;
	
	/**
	 * Count of elements inside the matrix.
	 */
	private int size = 0;

	/**
	 * <p>
	 * Create the Matrix Iterator and assign the matrix
	 * which should use for iteration. 
	 * </p>
	 * 
	 * @param matrix The matrix which should use for the iteration.
	 */
	public MatrixIterator(Matrix matrix) {
		m_Matrix = matrix;
		index = 0;
		size = matrix.getNumCols() * matrix.getNumRows();
	}
	
	@Override
	public boolean hasNext() {
		return index < size;
	}

	@Override
	public MatrixElement next() {
		
		if(!hasNext()) {
			throw new NoSuchElementException();
		}
		
		MatrixElement elIndex = new MatrixElement();
		elIndex.col = index % m_Matrix.getNumCols();
		elIndex.row = index / (m_Matrix.getNumCols());		
		elIndex.value = m_Matrix.get_unsafe(elIndex.row, elIndex.col);
		index++;
		
		return elIndex;
	}
}
