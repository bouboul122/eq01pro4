package model;

import java.io.IOException;
import java.util.ArrayList;

/*
 * Cette classe fait partie du modele Strategy avec ReadBehavior, ReadText, ReadFXML, WriteText, WrtieFXML
 * 
 * Cette classe fait partie du modele de conception MVC
 */

public interface WriteBehavior {
	
	public void write(String path, ArrayList<ShapeEMR> objectToWrite) throws IOException;

}
