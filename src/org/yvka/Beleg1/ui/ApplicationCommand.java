package org.yvka.Beleg1.ui;



public abstract class ApplicationCommand implements Command {
	
	private Application application = null;
	
	public ApplicationCommand(Application application) {
		this.application = application;
	}
	
	protected Application getApplication() {
		return application;
	}
	
	protected Context getContext() {
		return application.getContext();
	}
	
	protected MenuCommand getMenuCommand(String commandName) {
		Command cmd = getApplication().getMenuSet().get(commandName);
		if(cmd instanceof MenuCommand) {
			return (MenuCommand) cmd; 
		}
		return null;
	}
}
