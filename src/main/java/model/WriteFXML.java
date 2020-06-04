package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/*
 * Cette classe fait partie du modele Strategy avec ReadBehavior, ReadText, ReadFXML, WriteText, WriteBehavior
 * 
 * Cette classe fait partie du modele de conception MVC
 */

public class WriteFXML implements WriteBehavior{
	
	public void write(String path, ArrayList<ShapeEMR> objectList) throws IOException
	{
		try {

		    BufferedWriter bw = new BufferedWriter(new FileWriter(path));
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
