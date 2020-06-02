package model;

import java.io.IOException;
import java.util.ArrayList;

import javafx.scene.shape.Shape;

public interface ReadBehavior {
	
	public void read(String path, ArrayList<ShapeEMR> objectToWrite) throws IOException;

}
