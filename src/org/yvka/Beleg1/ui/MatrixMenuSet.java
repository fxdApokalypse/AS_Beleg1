package org.yvka.Beleg1.ui;

import java.util.List;
import java.util.stream.Collectors;

import org.yvka.Beleg1.ui.menues.CreateMatrixMenu;
import org.yvka.Beleg1.ui.menues.DeleteMatrixMenu;
import org.yvka.Beleg1.ui.menues.HelpMenu;
import org.yvka.Beleg1.ui.menues.ListMatricesMenu;
import org.yvka.Beleg1.ui.menues.MatrixAdditionMenu;
import org.yvka.Beleg1.ui.menues.MatrixMultiplicationMenu;
import org.yvka.Beleg1.ui.menues.MatrixScalarMultiplicationMenu;
import org.yvka.Beleg1.ui.menues.MaxRowSumMenu;
import org.yvka.Beleg1.ui.menues.QuitApplicationMenu;
import org.yvka.Beleg1.ui.menues.MarixRankMenu;
import org.yvka.Beleg1.ui.menues.PrintMatrixMenuCommand;
import org.yvka.Beleg1.ui.menues.TransposeMatrixMenu;
import org.yvka.Beleg1.ui.menues.UnknownMenu;

public class MatrixMenuSet extends CommandSet {
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
	

	private void add(MenuCommand command, String...aliasNames) {
		add(command.getName(), command);
		for(String aliasName : aliasNames) {
			add(aliasName, command);
		}
	}
	
	public List<MenuCommand> getAvailableApplicationCommands() {
		return getCommandsCollection()
				.stream()
				.filter(cmd -> cmd instanceof MenuCommand)
				.map(cmd -> (MenuCommand) cmd)
				.collect(Collectors.toList());
	}
}
