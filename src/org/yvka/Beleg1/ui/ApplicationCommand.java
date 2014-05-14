package org.yvka.Beleg1.ui;


/**
 * <p>
 * This Command is intended to used as base class for new ApplicationCommand implementation.<br>
 * <br> 
 * It is recommend to use this class because its contains convenience methods<br>
 * which can be used to share informations with the application.<br>
 * </p>
 * 
 * @author Yves Kaufmann
 * @see Command
 */
public abstract class ApplicationCommand implements Command {
	
	private Application application = null;
	
	/**
	 * All subclasses of ApplicationCommand must call this
	 * Constructor which assigns the Application to the current command.
	 *  
	 * @param application the Application which should assigned to the command.
	 */
	protected ApplicationCommand(Application application) {
		this.application = application;
	}
	
	/**
	 * <p>
	 * Returns the current assigned application of this command.<br>
	 * <br> 
	 * </p>
	 * 
	 * @return the current assigned application. 
	 */
	protected Application getApplication() {
		return application;
	}
	
	/**
	 * Returns the context of the current assigned application. 
	 * 
	 * @return the context of the current assigned application. 
	 */
	protected Context getContext() {
		return application.getContext();
	}
	
	/**
	 * <p>
	 * Returns a MenuCommand by its name if such a MenuCommand exists. <br>
	 * <br>
	 * </p>
	 * 
	 * @param commandName the name of a desired menu command.
	 * @return the desired menu command if it exists otherwise null.
	 */
	protected MenuCommand getMenuCommand(String commandName) {
		Command cmd = getApplication().getMenuSet().get(commandName);
		if(cmd instanceof MenuCommand) {
			return (MenuCommand) cmd; 
		}
		return null;
	}
}
