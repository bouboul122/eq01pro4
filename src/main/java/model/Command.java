package model;

import java.util.ArrayList;

import javafx.scene.shape.Shape;

/*
 * Cette classe fait partie du modele Command avec ShapeCommand et ArrowCommand
 * 
 * Cette classe fait partie du modele de conception MVC
 */

public interface Command {
	
	public ShapeEMR drawShape();
	public void deleteShape();
	public void removeFromList(ArrayList<Shape> shapeList);
	public void addToList(ArrayList<Shape> shapeList);
	public void redo();

}
