package model;

import java.io.IOException;
import java.util.ArrayList;

public interface WriteBehavior {
	
	public void write(ArrayList<ShapeEMR> objectToWrite) throws IOException;
	
	public String saveFileChooser();

}
