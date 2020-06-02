package model;

import java.util.Stack;

import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

public class ShapeCommand implements Command{
	
	Pane drawingBoard;
	String shapeToDraw;
	ShapeFactory shapeFactory;
	double xBegin;
	double xEnd;
	double yBegin;
	double yEnd;
	
	public ShapeCommand(String shapeToDraw, Pane drawingBoard, ClipboardContent content, Dragboard db){
		this.shapeToDraw = shapeToDraw;
		this.shapeFactory = new ShapeFactory(content, db);
		this.drawingBoard = drawingBoard;
	}
	
	@Override
	public ShapeEMR drawShape() {
		ShapeEMR shape;
		
		if (shapeToDraw.equals("PowerSource")) {
			shape = shapeFactory.getShape(ShapeFactory.eshape.sourcePower, this.xBegin, this.yBegin, "#98FB98", "#008000");
		}else {
			shape =  shapeFactory.getShape(ShapeFactory.eshape.accumulationPower, this.xBegin, this.yBegin, "#FFD700", "#FF0000");
		}
		Shape shapeToDraw = shape.createShape();
		drawingBoard.getChildren().add(shapeToDraw);
		//drawingElements.add(shapeToDraw);
		return shape;
	}
	
	@Override
	public void deleteShape() {

		drawingBoard.getChildren().remove(drawingBoard.getChildren().size() -1);
	}

	public Pane getDrawingBoard() {
		return drawingBoard;
	}

	public void setDrawingBoard(Pane drawingBoard) {
		this.drawingBoard = drawingBoard;
	}

	public String getShapeToDraw() {
		return shapeToDraw;
	}

	public void setShapeToDraw(String shapeToDraw) {
		this.shapeToDraw = shapeToDraw;
	}

	public ShapeFactory getShapeFactory() {
		return shapeFactory;
	}

	public void setShapeFactory(ShapeFactory shapeFactory) {
		this.shapeFactory = shapeFactory;
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

}
