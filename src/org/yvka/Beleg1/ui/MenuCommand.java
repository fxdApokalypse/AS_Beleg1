package org.yvka.Beleg1.ui;

import org.yvka.Beleg1.ui.menues.HelpMenu;
import org.yvka.Beleg1.utils.StringUtil;

/**
 * <p>
 * A MenuCommand is a command which is a encapsulated menu entry of the user interface.<br>
 * <br>
 * Each command has a name which helps the user to identify a command by his name. <br>
 * Each command has a description which describes his task to the user. <br>
 * Each command has a help text which delivered more detailed information to the user.<br>
 *  </p>
 * 
 * @author Yves Kaufmann
 *
 */
public abstract class MenuCommand extends ApplicationCommand {
	/**
	 * Describes a parameter of a menu command and
	 * is used by {@link MenuCommand#generateHelpTemplate(Param...)} to generate 
	 * the detailed help text.
	 * 
	 * @author Yves Kaufmann
	 *
	 */
	protected final class Param {
		protected String name;
		protected String desc;
		protected boolean required;
		
		
		/**
		 * Create a optional parameter. 
		 * 
		 * @param name the name of a parameter.
		 * @param desc the description of a parameter.
		 */
		public Param(String name, String desc) {
			this(name, desc, false);
		}
		
		/**
		 * Create a parameter. 
		 * 
		 * @param name the name of a parameter.
		 * @param desc the description of a parameter.
		 * @param required if the parameter isn't optional.
		 */
		public Param(String name, String desc, boolean required) {
			super();
			this.name = name;
			this.desc = desc;
			this.required = required;
		}
	
		/**
		 * Return the name of the parameter.
		 * 
		 * @return the name of the parameter.
		 */
		public String getName() {
			return name;
		}
		
		/**
		 * Return the description for this parameter.
		 * 
		 * @return the description of this parameter.
		 */
		public String getDesc() {
			return desc;
		}
		/**
		 * Checks if the parameter is required.
		 * 
		 * @return true if the parameter isn't optional.
		 */
		public boolean isRequired() {
			return required;
		}
		
		
		@Override
		public String toString() {
			return String.format("%s%-10s %s\n", HelpMenu.INDENT, getName(), getDesc());
		}
		
		/**
		 * Returns well structured string of the name of the parameter.
		 * 
		 * @return the name.
		 */
		public String toStringOnlyName() {
			String name = "<" + getName() + ">";
			if(!isRequired()) {
				name = "[" + name + "]";
			}
			return name;
		}
		
	}
	
	/**
	 * Create a Menu Command and assign it to the Application 'app'. <br>
	 * 
	 * @param app the application which should assigned to this command.
	 */
	public MenuCommand(Application app) {
		super(app);
	}
	
	/**
	 * Returns the name of this command.
	 * 
	 * @return the name of this command.
	 */
	public abstract String getName();
	
	/**
	 * Returns the description of this command.
	 * 
	 * @return the description of this command.
	 */
	public abstract String getDescription();
	
	/**
	 * Returns a detailed help for this command.
	 * @return the help text of this command.
	 */
	public abstract String getHelp();
	
	/**
	 * Generate the help text for this command.
	 * This class should used by subclasses to generate the help text.
	 * 
	 * @param params the parameter of this command.
	 * @return the help text
	 */
	protected String generateHelpTemplate(Param...params) {
		StringBuilder str = new StringBuilder(); 
		str.append(getDescription() + StringUtil.LINE_SEPERATOR);
		str.append(StringUtil.LINE_SEPERATOR);
		str.append("Usage: matrix " + getName() + " ");
		for(Param param : params) {
			str.append(param.toStringOnlyName());
			str.append(" ");
		}
		str.append(StringUtil.LINE_SEPERATOR);
		str.append(StringUtil.LINE_SEPERATOR);
		if(params.length > 0) {
			str.append("The " +  getName() + "commando has the following parameters:");
			for(Param param : params) {
				str.append(StringUtil.LINE_SEPERATOR);
				str.append(String.format("%s%-10s%s%s\n", HelpMenu.INDENT, param.getName(),HelpMenu.INDENT, param.getDesc()));
				
			}
		} else {
			 str.append("The " + getName() + "  commando has no parameter.\n");
		}
		return str.toString();
	}
	
}
