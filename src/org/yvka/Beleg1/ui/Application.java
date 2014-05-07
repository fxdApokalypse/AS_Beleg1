package org.yvka.Beleg1.ui;

import java.util.Scanner;

import org.yvka.Beleg1.ui.menues.HelpMenu;

/**
 * <p>
 * This class is intended to start and stop the application 
 * which includes the user interface.
 * <br>
 * This class is finite state machine, hence you
 * can only start a stopped application and you can only stop a
 * running application and so on. 
 * </p>
 * 
 * 
 * @author Yves Kaufmann
 *
 */
public class Application {
	
	private enum State {
		Running,
		Stopping,
		Stopped
	}
	
	/**
	 * The command prompt for this command line application
	 */
	private static final String COMMAND_PROMPT = "matrix > ";
	
	private Context ctx = null;
	private State state = null;
	private MatrixMenuSet menues = null;
	
	
	/**
	 * Creates the Application and initialize the application
	 */
	public Application() {
		ctx = new  Context();
		state = State.Stopped;
		menues = new MatrixMenuSet(this);		
	}
	
	/**
	 * <p>
	 * Starts the application if its not already running.<br>
	 * <br>
	 * When the application is started  without program arguments,
	 * the application starts a interactive command prompt otherwise
	 * the specified commands will be immediately executed after this the application will
	 * be stopped.
	 *    
	 * @param arguments the application arguments which are specified by the caller of this application.
	 * @throws IllegalStateException if the application is already running. 
	 */
	public void start(String[] arguments) {
		if(state != State.Stopped) {
			throw new IllegalStateException("The Application is already running.");
		}
		state = State.Running;
		
		if(arguments.length > 0) {
			menues.execute(arguments);
		} else {
			readArgumentsFromConsole();
		}
		
	}
	
	/**
	 * <p>
	 * Stops the application if it still running.<br>
	 * </p>
	 * 
	 * @throws IllegalStateException if the application isn't running.
	 */
	public void stop() {
		if(state != State.Running) {
			throw new IllegalStateException("The Application is already stopped.");
		}
		state = State.Stopping;
	}
	
	/**
	 * 
	 */
	private void readArgumentsFromConsole() {
		menues.execute("help"); 
		try(Scanner sc =  new Scanner(System.in)) {
			while( state == State.Running ) {
				System.out.print(COMMAND_PROMPT);
				String line  = sc.nextLine();
				
				if(line == null) break;
				String[] arguments = line.split(" ");			
				menues.execute(arguments);
			}
		}
	}
	
	/**
	 * <p>
	 * Returns the Context of this application.<br>
	 * <br>
	 * This getter is intended to use by MenuCommands to share
	 * Information between different MenuCommands. This ensures
	 * that a menu command can for example create a matrix which can used by
	 * a other MenuCommand.  
	 * </p>
	 * 
	 * @return the current context object of this application.
	 */
	public Context getContext() {
		return this.ctx;
	}
	
	/**
	 * <p>
	 * Returns a set of supported MenuCommands. <br>
	 * <br>
	 * This getter is intended to use by the {@link HelpMenu} 
	 * which enables the HelpMenu to generate help information pages.
	 * </p>
	 * 
	 * @return the currently supported MenuCommands of this application.
	 */
	public MatrixMenuSet getMenuSet() {
		return this.menues;
	} 
}
