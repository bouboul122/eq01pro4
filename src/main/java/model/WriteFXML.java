package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class WriteFXML implements WriteBehavior{
	
	public String saveFileChooser() {
		
		FileChooser fc = new FileChooser();
		fc.getExtensionFilters().addAll(
		    new ExtensionFilter("xml Files", "*.xml"));
		File selectedFile = null;
		try {			
			selectedFile = fc.showSaveDialog(null);
			fc.setInitialDirectory(selectedFile.getParentFile());
		}
		catch(Exception ex) {
		}
		return selectedFile.getAbsolutePath();
		
		
	}
	
	public void write(ArrayList<ShapeEMR> objectList) throws IOException
	{
		try {

		    BufferedWriter bw = new BufferedWriter(new FileWriter(saveFileChooser()));
		    for (int i = 0; i < objectList.size(); i++) {
		    	
		    	String line = objectList.get(i).toString();
		    	String[] tokens = line.split(",");
		    	String strToWrite = "<Shape class=" +"\"" + tokens[0] + "\"" + " xCoordinate=" + "\""+tokens[1] +"\"" + 
		    			" yCoordinate=" + "\""+tokens[2] + "\""+" mainColor=" + "\"" + tokens[3] + "\"" + " borderColor=" +
		    			"\"" + tokens[4] + "\"" + " \\>"; 
				bw.write(strToWrite);
				bw.newLine();
				System.out.println(strToWrite);
			}
		    bw.close();

		} catch (IOException ex) {
		    ex.printStackTrace();
		}
		
		
	}
	
}
