package org.yvka.Beleg1.matrix.operations;


/**
 * <p>
 * This exception is raised when, an matrix read error occurs.
 * </p>
 * 
 * @see Exception {@link Exception}
 * @author Yves Kaufmann
 * 
 */
public class InvalidMatrixFormatException extends Exception {

	/**
	 * Just the serial version id
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructs a new IllegalMatrixComputationException with no detail message.
	 * 
	 * @see Exception {@link Exception}
	 */
	public InvalidMatrixFormatException() {
		super();

	}
	
	/**
	 * Constructs a new IllegalMatrixComputationException with a specified detail message and cause.
	 * 
	 * @param message the detail message.
	 * @param cause the cause of this exception.
	 * @see Exception {@link Exception}
	 */
	public InvalidMatrixFormatException(String message, Throwable cause ) {
		super(message, cause );

	}
	
	/**
	 * Constructs a new exception with the specified detail message.
	 * 
	 * @param message the detail message.
	 * @see Exception {@link Exception}
	 */
	public InvalidMatrixFormatException(String message) {
		super(message);
	}
}
