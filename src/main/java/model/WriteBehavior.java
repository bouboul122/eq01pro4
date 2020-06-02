package model;

import java.io.IOException;
import java.util.ArrayList;

public interface WriteBehavior {
	
	public void write(String path, ArrayList<ShapeEMR> objectToWrite) throws IOException;

}
