package org.yvka.Beleg1.ui;

import java.util.List;
import java.util.stream.Collectors;

import org.yvka.Beleg1.ui.menues.CreateMatrixMenu;
import org.yvka.Beleg1.ui.menues.DeleteMatrixMenu;
import org.yvka.Beleg1.ui.menues.HelpMenu;
import org.yvka.Beleg1.ui.menues.ListMatricesMenu;
import org.yvka.Beleg1.ui.menues.MarixRankMenu;
import org.yvka.Beleg1.ui.menues.MatrixAdditionMenu;
import org.yvka.Beleg1.ui.menues.MatrixMultiplicationMenu;
import org.yvka.Beleg1.ui.menues.MatrixScalarMultiplicationMenu;
import org.yvka.Beleg1.ui.menues.MaxRowSumMenu;
import org.yvka.Beleg1.ui.menues.PrintMatrixMenuCommand;
import org.yvka.Beleg1.ui.menues.QuitApplicationMenu;
import org.yvka.Beleg1.ui.menues.TransposeMatrixMenu;
import org.yvka.Beleg1.ui.menues.UnknownMenu;

/**
 * Set of all supported matrix menu commands which are 
 * usable by the user of the user interface.
 * 
 * @author Yves Kaufmann
 *
 */
public class MatrixMenuSet extends CommandSet {
	
	/**
	 * Create a MatrixMenuSet and assigned to a application.
	 *  
	 * @param application the assigned application.
	 */
	public MatrixMenuSet(Application application) {
		setUnknownCommand(new UnknownMenu());
		
		add(new CreateMatrixMenu(application));
		add(new ListMatricesMenu(application));
		add(new PrintMatrixMenuCommand(application));
		add(new MatrixAdditionMenu(application));
		add(new MatrixMultiplicationMenu(application));
		add(new TransposeMatrixMenu(application));
		add(new MarixRankMenu(application));	
		add(new HelpMenu(application));
		add(new QuitApplicationMenu(application));
		add(new MaxRowSumMenu(application));
		add(new DeleteMatrixMenu(application));
		add(new MatrixScalarMultiplicationMenu(application));
	}
	
	/**
	 * Returns a list of all menu commands.
	 * 
	 * @return the list of menu commands.
	 */
	public List<MenuCommand> getAllMenuCommands() {
		return getCommandsCollection()
				.stream()
				.filter(cmd -> cmd instanceof MenuCommand)
				.map(cmd -> (MenuCommand) cmd)
				.sorted((x1, x2) -> x1.getName().compareTo(x2.getName()))
				.collect(Collectors.toList());
	}
	
	private void add(MenuCommand command, String...aliasNames) {
		add(command.getName(), command);
		for(String aliasName : aliasNames) {
			add(aliasName, command);
		}
	}
}
