package model;

import java.util.ArrayList;
import java.util.Stack;

import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

/*
 * Cette classe fait partie du modele Command ShapeCommand et Command
 * 
 * Cette classe fait partie du modele de conception MVC
 */

public class ArrowCommand implements Command{
	
	Pane drawingBoard;
	String arrowColor = "Red";
	ArrowShapeFactory arrowFactory;
	Stack<ShapeEMR> children;
	double xBegin;
	double xEnd;
	double yBegin;
	double yEnd;
	Shape shapeDrew;
	
	public ArrowCommand(Pane drawingBoard, String arrowColor, ClipboardContent content, Dragboard db){
		this.drawingBoard = drawingBoard;
		this.arrowFactory = new ArrowShapeFactory(content, db);
		this.arrowColor = arrowColor;
	}
	
	@Override
	public ShapeEMR drawShape() {
		ShapeEMR shape;
		if (arrowColor.equals("Red")) {
			shape = arrowFactory.getArrow(ArrowShapeFactory.arrows.redArrow, this.xBegin, this.yBegin, this.xEnd, this.yEnd);
		}else {
			shape = arrowFactory.getArrow(ArrowShapeFactory.arrows.blackArrow, this.xBegin, this.yBegin, this.xEnd, this.yEnd);
		}
		this.shapeDrew = shape.createShape();
		
		drawingBoard.getChildren().add(shapeDrew);
		
		return shape;
	}
	
	@Override
	public void deleteShape() {

		drawingBoard.getChildren().remove(drawingBoard.getChildren().size() -1);
	}
	
	public void removeFromList(ArrayList<Shape> shapeList) {
		shapeList.remove(this.shapeDrew);
	}
	
	public void addToList(ArrayList<Shape> shapeList) {
		shapeList.add(this.shapeDrew);
	}
	public void redo() {
		drawingBoard.getChildren().add(this.shapeDrew);
	}

	public String getArrowColor() {
		return arrowColor;
	}

	public void setArrowColor(String arrowColor) {
		this.arrowColor = arrowColor;
	}

	public double getxBegin() {
		return xBegin;
	}

	public void setxBegin(double xBegin) {
		this.xBegin = xBegin;
	}

	public double getxEnd() {
		return xEnd;
	}

	public void setxEnd(double xEnd) {
		this.xEnd = xEnd;
	}

	public double getyBegin() {
		return yBegin;
	}

	public void setyBegin(double yBegin) {
		this.yBegin = yBegin;
	}

	public double getyEnd() {
		return yEnd;
	}

	public void setyEnd(double yEnd) {
		this.yEnd = yEnd;
	}

	public Shape getShapeDrew() {
		return shapeDrew;
	}

	public void setShapeDrew(Shape shapeDrew) {
		this.shapeDrew = shapeDrew;
	}

}
