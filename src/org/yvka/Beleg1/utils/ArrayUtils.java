package org.yvka.Beleg1.utils;


/**
 * Common used array utility methods.
 * @author Yves Kaufmann
 *
 */
public class ArrayUtils {
	
	/**
	 * Just a simple data structure for the dimension of a array.
	 * The Purpose of this class is only for convenience transferring between methods.
	 * @author fxdapokalypse
	 *
	 */
	public static class ArrayDimensions {
		public int cols;
		public int rows;
		
		private ArrayDimensions() {
			cols = 0;
			rows = 0;
		}
		
		/**
		 * Checks if the dimension is empty.
		 * 
		 * @return Returns true if the dimension is empty.
		 */
		public boolean isEmpty() {
			return cols <= 0 || rows <= 0;
		}
		
		/**
		 * Ensure that the dimension not describe a empty array.
		 * Otherwise a Exception will be thrown.
		 */
		public void ensureIsNotEmpty() {
			if(this.isEmpty()) {
				throw new IllegalArgumentException(
					String.format("Invalid array: empty [%d,%d]", rows, cols)
				);
			}
		}

		public boolean isIndexInBound(int rowIndex, int colIndex) {
			return rowIndex >= 0 && rowIndex < rows && colIndex >= 0 && colIndex < cols;
		}
	}
	
	/**
	 * Returns true if the specified array is empty.
	 * <br>
	 * @param double array The specified array.
	 * @return Returns true if the specified array is empty
	 */
	public static boolean isEmpty(double [][]array) {
		ArrayDimensions dim = getDimension(array); 
		return dim.isEmpty();
	}
	
	/**
	 * Determines the dimension of a specified array.
	 * <br>
	 * @param array The specified array.
	 * @return The dimension of the array.
	 */
	public static ArrayDimensions getDimension(double[][] array) {
		ArrayDimensions dim = new ArrayDimensions();
		dim.rows = array != null ? array.length : 0;
		dim.cols = (dim.rows > 0 && array[0].length > 0)? array[0].length : 0;
		return dim;
	}
	
	/**
	 * Returns true if the specified index is in side the specified array.<br>
	 * <br>
	 * @param array The specified array.
	 * @param rowIndex The row Index which should be checked.
	 * @param colIndex The col Index which should be checked.
	 * @return Returns true if the specified index is inside this array.
	 */
	public static boolean isIndexInBound(double[][] array, int rowIndex, int colIndex) {
		ArrayDimensions dim = getDimension(array);
		return dim.isIndexInBound(rowIndex,colIndex);
	}
	
}
