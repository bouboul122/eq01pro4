package model;

import java.util.Stack;

import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

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

}
