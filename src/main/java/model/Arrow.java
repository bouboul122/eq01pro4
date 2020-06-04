package model;

import javafx.scene.input.ClipboardContent;

/*
 * Cette classe fait partie du modele Factory avec toutes les autres formes concretes, ShapeEMR, ShapeFactory
 * ArrowShapeFactory
 * 
 * Cette classe fait partie du modele de conception MVC
 */
import javafx.scene.input.Dragboard;

public abstract class Arrow extends ShapeEMR{
	
	double xEnd;
	double yEnd;
	
	Arrow(double xCoordinate, double yCoordinate, double xEnd, double yEnd, String mainColor, String borderColor, ClipboardContent content, Dragboard db){
		super(xCoordinate, yCoordinate, mainColor, borderColor, content, db);
		this.xEnd = xEnd;
		this.yEnd = yEnd;
	}
	
}
