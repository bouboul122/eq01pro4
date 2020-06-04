package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TitledPane;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Shape;
import model.AccumulationPower;
import model.ArrowCommand;
import model.Command;
import model.PowerSource;
import model.ReadBehavior;
import model.ReadFXML;
import model.ReadText;
import model.ShapeCommand;
import model.ShapeEMR;
import model.WriteBehavior;
import model.WriteFXML;
import model.WriteText;

/*
 * Cette classe contient le controlleur du modele MVC
 */
public class EMRSaveController {
	
	WriteBehavior writeBehavior;
	ReadBehavior readBehavior;
	
	ShapeCommand shapeCommand;
	ArrowCommand arrowCommand;
	
	Dragboard db;
	ClipboardContent content = new ClipboardContent();
	
	ArrayList<ShapeEMR> shapeEMRList = new ArrayList<ShapeEMR>();
	ArrayList<Shape> shapeList = new ArrayList<Shape>();
	Stack<Command> doneCommands= new Stack<Command>();
	Stack<Command> deletedCommands= new Stack<Command>();
	
	PowerSource powerSource = new PowerSource(0,0,"#98FB98", "#008000", content, db);
	Shape powerSourceShape = powerSource.createShape();
	AccumulationPower accumulationPower = new AccumulationPower(0,0,"#FFD700", "#FF0000", content, db);
	Shape accPowerShape = accumulationPower.createShape();
	
	boolean firstConfirmed = false;
	boolean secondConfirmed = false;
	String arrowColor;
	ShapeEMR first;
	ShapeEMR second;
	
	@FXML
	TitledPane powerShapes;
	@FXML
	TitledPane arrowShapes;
	@FXML
	Button stateButton;
	@FXML
	Label stateLabel;
	@FXML
	Accordion shapeMenu;
	@FXML
	VBox powerVBox;
	@FXML 
	VBox arrowVBox;
	@FXML
	Pane drawingBoard;
	@FXML
	Button redArrowButton;
	@FXML
	Button blackArrowButton;
	@FXML
	MenuItem saveTextMenu;
	@FXML
	MenuItem openTextMenu;
	@FXML
	MenuItem saveFXMLMenu;
	@FXML
	MenuItem openFXMLMenu;
	
	
	public void initialize()
	{
		
		System.out.println("Initialising");
		shapeMenu.setExpandedPane(powerShapes);
		System.out.println(powerShapes.isExpanded());
		
		//Adding dans les VBox
		powerVBox.setSpacing(10);
	    powerVBox.setAlignment(Pos.TOP_CENTER);
	    
		powerVBox.getChildren().add(powerSourceShape);
		powerVBox.getChildren().add(accPowerShape);
		
		drawingBoard.setOnDragOver(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		        if (event.getGestureSource() != drawingBoard &&
		                event.getDragboard().hasString()) {
		            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
		        }
		        
		        event.consume();
		    }
		});
		
		drawingBoard.setOnDragDropped(new EventHandler<DragEvent>() {
		    public void handle(DragEvent event) {
		    	ShapeEMR shape;
		    	Shape shapeDrew;
		        db = event.getDragboard();
		        boolean success = false;
		        if (db.hasString()) {
		        	if (content.getString().contentEquals("PowerSource"))
		        	{
		        		System.out.println("PowerSource");
		        		shapeCommand = new ShapeCommand("PowerSource",drawingBoard, content, db, shapeEMRList);
		        		shapeCommand.setxBegin(event.getX());
		        		shapeCommand.setyBegin(event.getY());
		        		shape = shapeCommand.drawShape();
		        		shapeDrew = shapeCommand.getShapeDrew();
		        		doneCommands.add(shapeCommand);
		        		shapeCommand.addToList(shapeList);
		        	} else if(content.getString().contentEquals("AccumulationPower"))
		        	{
		        		System.out.println("AccumulationPower");
		        		shapeCommand = new ShapeCommand("AccumulationPower",drawingBoard, content, db, shapeEMRList);
		        		shapeCommand.setxBegin(event.getX());
		        		shapeCommand.setyBegin(event.getY());
		        		shape = shapeCommand.drawShape();
		        		shapeDrew = shapeCommand.getShapeDrew();
		        		doneCommands.add(shapeCommand);
		        		shapeCommand.addToList(shapeList);
		        	}else {
		        		shape = null;
		        		shapeDrew = null;
		        	}
							
		        			           
		           success = true;
		        }
		        event.setDropCompleted(success);
		        
		        event.consume();
		     }
		});
		
	}
	
	@FXML
	public void undoButtonClicked()
	{
		if(powerShapes.isExpanded()) {
			if(doneCommands.size() > 0) {
			Command commandToUndo = doneCommands.pop();
			commandToUndo.deleteShape();
			deletedCommands.add(commandToUndo);
			commandToUndo.removeFromList(shapeList);
			System.out.println(shapeList.size());
			
			}
			else {
				System.out.println("Stack empty");
			}
		}
	}
	
	@FXML
	public void redoButtonClicked()
	{

		if(powerShapes.isExpanded()) {
			if(deletedCommands.size() > 0) {
			Command commandToRedo = deletedCommands.pop();
			commandToRedo.drawShape();
			doneCommands.add(commandToRedo);
			commandToRedo.addToList(shapeList);
			System.out.println(shapeList.size());
			
			}
			else {
				System.out.println("Stack empty");
			}
		}
	}
	
	@FXML
	public void saveToTextClicked()
	{
		writeBehavior = new WriteText();
		try {
			writeBehavior.write("C:\\Users\\ludov\\Documents\\textSaveApp2.txt", shapeEMRList);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Save to text");
	}
	
	public void saveToFXMLClicked()
	{
		writeBehavior = new WriteFXML();
		try {
			writeBehavior.write("C:\\Users\\ludov\\Documents\\textSaveApp2FXML.xml", shapeEMRList);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Save to text");
	}
	
	@FXML
	public void openTextMenuClicked()
	{
		readBehavior = new ReadText(content, db, drawingBoard, shapeEMRList);
		doneCommands.clear();
		deletedCommands.clear();
		shapeEMRList.clear();
		drawingBoard.getChildren().clear();
		try {
			readBehavior.read("C:\\Users\\ludov\\Documents\\textSaveApp2.txt", shapeEMRList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void openFXMLMenuClicked()
	{
		readBehavior = new ReadFXML(content, db, drawingBoard, shapeEMRList);
		doneCommands.clear();
		deletedCommands.clear();
		shapeEMRList.clear();
		drawingBoard.getChildren().clear();
		try {
			readBehavior.read("C:\\Users\\ludov\\Documents\\textSaveApp2FXML.xml", shapeEMRList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}



}
