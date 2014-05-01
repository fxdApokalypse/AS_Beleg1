package org.yvka.Beleg1.ui;

import org.yvka.Beleg1.data.Matrix;

public class MatrixTO {
	protected Matrix matrix = null;
	protected String name = null;
	
	public MatrixTO(Matrix matrix, String naem) {
		super();
		this.matrix = matrix;
		this.name = naem;
	}
	
	public Matrix getMatrix() {
		return matrix;
	}
	public String getName() {
		return name;
	}
	
	
}
