package model;

import java.io.IOException;
import java.util.ArrayList;

import javafx.scene.shape.Shape;

/*
 * Cette classe fait partie du modele Strategy avec WriteBehavior, ReadFXML, WriteFXML, ReadText, WriteText
 * 
 * Cette classe fait partie du modele de conception MVC
 */
public interface ReadBehavior {
	
	public void read(String path, ArrayList<ShapeEMR> objectToWrite) throws IOException;

}
