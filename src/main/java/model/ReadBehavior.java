package model;

import java.io.IOException;
import java.util.ArrayList;



public interface ReadBehavior {
	
	public void read(ArrayList<ShapeEMR> objectToWrite) throws IOException;
	
	public String openFileChooser(); 
}
