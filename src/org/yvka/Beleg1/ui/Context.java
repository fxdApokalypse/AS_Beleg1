package org.yvka.Beleg1.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import org.yvka.Beleg1.data.Matrix;

/**
 * <p>
 * The context of the application is intend to share 
 * information between {@link ApplicationCommands}. <br>
 * <br>
 * Users of this class are capable to share Numbers and matrices with each other. <br>
 * <br>
 * </p>
 * @author Yves Kaufmann
 * @see Commmand
 * @see ApplicationCommand
 */
public class Context {

	private HashMap<String, Matrix> matrices = null;
	private HashMap<String, Double> numbers = null;
	
	/**
	 * Create a blank context.
	 */
	public Context() {
		matrices = new HashMap<>();
		numbers = new HashMap<>();
	}
	
	/**
	 * Check if a number with the name 'name' exits.
	 * 
	 * @param name the name of the number. 
	 * @return true if a number with the specified name exists.
	 */
	public boolean hasNumber(String name) {
		return numbers.containsKey(name);
	}
	
	/**
	 * Check if a matrix with the name 'name' exits.
	 * 
	 * @param name the name of the matrix. 
	 * @return true if a matrix with the specified name exists.
	 */
	public boolean hasMatrix(String name) {
		return matrices.containsKey(name);
	}
	
	/**
	 * Returns a number by his specified name 'name'.
	 * 
	 * @param name the name of the desired Number.
	 * @return the desired number if the number exists otherwise null.
	 */
	public Double getNumber(String name) {
		return numbers.get(name);
	}
	
	/**
	 * Returns a matrix by his specified name 'name'.
	 * 
	 * @param name the name of the desired matrix.
	 * @return the desired matrix if the matrix exists otherwise null.
	 */
	public Matrix getMatrix(String name) {
		return matrices.get(name);
	}
	
	/**
	 * Returns a list of stored matrix names.
	 * 
	 * @return the list of stored matrices. 
	 */
	public List<String> getMatrixNames() {
		ArrayList<String> list = new ArrayList<>(matrices.keySet());
		list.sort(String.CASE_INSENSITIVE_ORDER);
		return list;

	}
	
	/**
	 * Returns a list of stored number names.
	 * 
	 * @return the list of stored numbers. 
	 */
	public List<String> getNumberNames() {
		ArrayList<String> list = new ArrayList<>(matrices.keySet());
		list.sort(String.CASE_INSENSITIVE_ORDER);
		return list;
	}
	
	/**
	 * <p>
	 * Stores a specified number under a name.<br>
	 * <br>
	 * NOTE: Existing numbers will be replaced with a new one.
	 * </p>
	 * @param name the name of the number .
	 * @param number the number which should stored under the name 'name'.
	 */
	public void putNumber(String name, double number) {
		if(!hasMatrix(name)) {
			this.numbers.put(name, number);
		}
	}
	
	/**
	 * <p>
	 * Stores a specified matrix under a name.<br>
	 * <br>
	 * NOTE: Existing matrices will be replaced with a new one.
	 * </p>
	 * @param name the name of the matrix.
	 * @param matrix the matrix which should stored under the name 'name'.
	 */
	public void putMatrix(String name, Matrix matrix) {
		Objects.requireNonNull(matrix);
		if(!hasMatrix(name)) {
			this.matrices.put(name, matrix);
		}
	}
	
	/**
	 * Remove a matrix with the name 'name' if such a matrix exists.
	 * 
	 * @param name the name of the matrix which should removed.
	 */
	public void removeMatrix(String name) {
		if(hasMatrix(name)) {
			matrices.remove(name);
		}
	}
	
	/**
	 * Remove a number with the name 'name' if such a number exists.
	 * 
	 * @param name the name of the number which should removed.
	 */
	public void removeNumber(String name) {
		if(hasMatrix(name)) {
			numbers.remove(name);
		}
	}
	
	
	
}
