package org.yvka.Beleg1.ui;


/**
 * <p>
 * A command is a decoupled action inside the application which is intended for reusability.<br>
 * <br>
 * This interface is part of a <a href="http://en.wikipedia.org/wiki/Command_pattern">Command Pattern</a> which
 * is intended to create reusable actions which decouples execution from the caller. This leads to avoiding
 * code duplications for common application action which must executed in many classes. 
 * </p>
 * @author Yves Kaufmann
 *
 */
public interface Command {
	/**
	 * <p>
	 * This class implements the <a href="http://en.wikipedia.org/wiki/Null_Object_pattern">Null Object Pattern</a>.<br>
	 * <br>
	 * It is intended to avoid checks for null, hence methods should return Command.NULL instead of null. <br> 
	 * </p>
	 * @author Yves Kaufmann
	 *
	 */
	final class NullCommand implements Command {
		private NullCommand() {}
		@Override
		public void execute(String...args) {}
	}
	
	/**
	 * The Null Command object.
	 */
	public final static NullCommand NULL = new NullCommand();
	
	/**
	 * <p>
	 * Executes this command.<br> 
	 * <br>
	 * </p>
	 * @param args a variable count of parameters for this command.
	 */
	public void execute(String...args);
}
