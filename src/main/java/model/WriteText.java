package model;

import java.io.BufferedWriter;
import java.io.File;

import java.io.FileWriter;
import java.io.IOException;


import java.util.ArrayList;


import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
/* essaie une nouvelle facon decrire apres souper*/
public class WriteText implements WriteBehavior{
	
	public String saveFileChooser() {
		
		FileChooser fc = new FileChooser();
		fc.getExtensionFilters().addAll(
		    new ExtensionFilter("txt Files", "*.txt"));
		File selectedFile = null;
		
		try {			
			selectedFile = fc.showSaveDialog(null);
			fc.setInitialDirectory(selectedFile.getParentFile());
		}
		catch(Exception ex) {
		}
		return selectedFile.getAbsolutePath();
		
		
	}
	
	@Override
	public void write(ArrayList<ShapeEMR> objectList) throws IOException
	{
		
		try {

		    BufferedWriter bw = new BufferedWriter(new FileWriter(saveFileChooser()));
		    for (int i = 0; i < objectList.size(); i++) {
				bw.write(objectList.get(i).toString());
				bw.newLine();
				System.out.println(objectList.get(i).toString());
			}
		    bw.close();

		} catch (IOException ex) {
		    ex.printStackTrace();
		}
		

	}

}
