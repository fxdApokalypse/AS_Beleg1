package org.yvka.Beleg1.ui.menues;

import org.yvka.Beleg1.data.Matrix;
import org.yvka.Beleg1.data.MatrixImpl;
import org.yvka.Beleg1.ui.Application;
import org.yvka.Beleg1.ui.IOTools;
import org.yvka.Beleg1.ui.MenuCommand;


public class CreateMatrixMenu extends MenuCommand {

	public CreateMatrixMenu(Application app) {
		super(app);
	}
	
	@Override
	public void execute(String... args) {
		Matrix matrix = null;
		double [][]data = null;
		int rows = 0; int cols = 0;
		String name = retrieveMatrixName(args);
		
		rows = IOTools.readNumberInRange("Count of desired rows", 1, 7);
		cols = IOTools.readNumberInRange("Count of desired columns", 1, 7);
		
		data = new double[rows][cols];
		
		for(int row = 0; row < rows; row++) {
			for(int col = 0; col < cols; col++) {
				data[row][col] = IOTools.readDouble(
					String.format("%s[%d,%d] : ", name, row+1, col+1)
				); 
			}
		}
		
		getApplication().getContext().putMatrix(name, new MatrixImpl(data));
	}
	
	private String retrieveMatrixName(String... args) {
		String name = null;
		if(args.length > 0) {
			name = args[0];
		}
		
		do {
			if(name == null) { name = IOTools.readString("Name of the new matrix: "); }
			name = name.trim();
			
			if("".equals(name)) {
				System.out.println("The specified matrix name isn't valid.Please try it again.");
				name = null;
			}
			else
			if(getApplication().getContext().hasMatrix(name)) {
				System.out.println("The specified matrix already exists.Please try it again.");
				name = null;
			} else break;
			
		} while(true);
		
		return name;
	}

	@Override
	public String getName() {
		return "create";
	}

	@Override
	public String getDescription() {
		return "Creates a matrix for later usage.";
	}

	@Override
	public String getHelp() {
		return generateHelpTemplate(
			new Param("name", "The name for the new matrix.", true)
		);
	}

}
