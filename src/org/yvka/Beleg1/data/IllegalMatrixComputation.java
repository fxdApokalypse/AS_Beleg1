package org.yvka.Beleg1.data;

/**
 * <p>
 * Indicate an error which occurs while matrix computations.
 * </p>
 * 
 * @author Yves Kaufmann
 *
 */
public class IllegalMatrixComputation extends Exception {

	/**
	 * Just the serial version id
	 */
	private static final long serialVersionUID = 1L;

	public IllegalMatrixComputation() {
		super();

	}
	
	public IllegalMatrixComputation(String arg0, Throwable arg1, boolean arg2,
			boolean arg3) {
		super(arg0, arg1, arg2, arg3);

	}

	public IllegalMatrixComputation(String arg0, Throwable arg1) {
		super(arg0, arg1);

	}

	public IllegalMatrixComputation(String arg0) {
		super(arg0);

	}

	public IllegalMatrixComputation(Throwable arg0) {
		super(arg0);

	}

}
