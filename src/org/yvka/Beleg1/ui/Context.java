package org.yvka.Beleg1.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import org.yvka.Beleg1.data.Matrix;

/**
 * Set of Variables for number and matrices.  
 * 
 * @author Yves Kaufmann
 *
 */
public class Context {

	private HashMap<String, Matrix> matrices = null;
	private HashMap<String, Double> numbers = null;
	
	public Context() {
		matrices = new HashMap<>();
		numbers = new HashMap<>();
	}
	
	public boolean hasNumber(String name) {
		
		return numbers.containsKey(name);
	}
	
	public boolean hasMatrix(String name) {
		return matrices.containsKey(name);
	}
	
	public Double getNumber(String name) {
		return numbers.get(name);
	}
	
	public Matrix getMatrix(String name) {
		return matrices.get(name);
	}
	
	public List<String> getMatrixNames() {
		ArrayList<String> list = new ArrayList<>(matrices.keySet());
		list.sort(String.CASE_INSENSITIVE_ORDER);
		return list;

	}
	
	public List<String> getNumberNames() {
		ArrayList<String> list = new ArrayList<>(matrices.keySet());
		list.sort(String.CASE_INSENSITIVE_ORDER);
		return list;
	}
	
	public void putNumber(String name, double number) {
		if(!hasMatrix(name)) {
			this.numbers.put(name, number);
		}
	}
	
	public void putMatrix(String name, Matrix matrix) {
		Objects.requireNonNull(matrix);
		if(!hasMatrix(name)) {
			this.matrices.put(name, matrix);
		}
	}
	
	public void removeMatrix(String name) {
		if(hasMatrix(name)) {
			matrices.remove(name);
		}
	}
	
	public void removeNumber(String name) {
		if(hasMatrix(name)) {
			numbers.remove(name);
		}
	}
	
	
	
}
