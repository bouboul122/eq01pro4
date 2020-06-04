package model;


import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.shape.Shape;


/*
 * Cette classe fait partie du modele Factory avec Arrow, ShapeFactory, ArrowShapeFactory et toutes les formes 
 * concretes
 * 
 * Cette classe fait partie du modele de conception MVC
 */

public abstract class ShapeEMR{
	
	double xCoordinate;
	double yCoordinate;
	String mainColor;
	String borderColor;
	ClipboardContent content;
	Dragboard db;
	double [] topAnchor;
	double [] bottomAnchor;
	double [] rightAnchor;
	double [] leftAnchor;
	
	ShapeEMR (double xCoordinate, double yCoordinate, String mainColor, String borderColor, ClipboardContent content, Dragboard db)
	{
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
		this.mainColor = mainColor;
		this.borderColor = borderColor;
		this.content = content;
		this.db = db;
	}
	
	
	
	public Dragboard getDb() {
		return db;
	}



	public void setDb(Dragboard db) {
		this.db = db;
	}



	public double getxCoordinate() {
		return xCoordinate;
	}



	public double[] getTopAnchor() {
		return topAnchor;
	}



	public void setTopAnchor(double[] topAnchor) {
		this.topAnchor = topAnchor;
	}



	public double[] getBottomAnchor() {
		return bottomAnchor;
	}



	public void setBottomAnchor(double[] bottomAnchor) {
		this.bottomAnchor = bottomAnchor;
	}



	public double[] getRightAnchor() {
		return rightAnchor;
	}



	public void setRightAnchor(double[] rightAnchor) {
		this.rightAnchor = rightAnchor;
	}



	public double[] getLeftAnchor() {
		return leftAnchor;
	}



	public void setLeftAnchor(double[] leftAnchor) {
		this.leftAnchor = leftAnchor;
	}



	public void setxCoordinate(int xCoordinate) {
		this.xCoordinate = xCoordinate;
	}



	public double getyCoordinate() {
		return yCoordinate;
	}



	public void setyCoordinate(double yCoordinate) {
		this.yCoordinate = yCoordinate;
	}

	public abstract Shape createShape();

}
