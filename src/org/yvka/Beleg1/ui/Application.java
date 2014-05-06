package org.yvka.Beleg1.ui;

import java.util.Scanner;

public class Application {
	
	private enum State {
		Running,
		Stopping,
		Stopped
	}
	
	private static final String COMMAND_PROMPT = "matrix > ";
	
	private Context ctx = null;
	private State state = null;
	private MatrixMenuSet menues = null;
	
	
	public Application() {
		ctx = new  Context();
		state = State.Stopped;
		menues = new MatrixMenuSet(this);		
	}
	
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
	
	public void stop() {
		if(state != State.Running) {
			throw new IllegalStateException("The Application is already stopped.");
		}
		state = State.Stopping;
	}

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

	public Context getContext() {
		return this.ctx;
	}
	public MatrixMenuSet getMenuSet() {
		return this.menues;
	} 
}
