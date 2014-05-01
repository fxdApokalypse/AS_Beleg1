package org.yvka.Beleg1.ui;


public interface Command {
	final class NullCommand implements Command {
		private NullCommand() {}
		@Override
		public void execute(String...args) {}
	}

	public final static NullCommand NULL = new NullCommand();
	
	public void execute(String...args);
}
