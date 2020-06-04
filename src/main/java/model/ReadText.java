package model;

import java.io.BufferedReader;
import java.io.File;

import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.layout.Pane;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/*
 * Cette classe fait partie du modele Strategy avec WriteBehavior, ReadBehavior, ReadFXML, WriteText, WriteFXML
 * 
 * Cette classe fait partie du modele de conception MVC
 */

public class ReadText implements ReadBehavior{
	
	ClipboardContent content;
	Dragboard db;
	Pattern pattern;
	Matcher matcher;
	Pane drawingBoard;
	ShapeFactory shapeFactory = new ShapeFactory(content, db);
	ArrayList<ShapeEMR> shapeList;
	public ReadText(ClipboardContent content, Dragboard db, Pane drawingBoard, ArrayList<ShapeEMR> shapeList) {
		this.content = content;
		this.db = db;
		this.drawingBoard = drawingBoard;
		this.shapeList = shapeList;
	}
	
	//Fonction pour ouvrir le FileChooser en .txt
	public String openFileChooser() {
		
			FileChooser fc = new FileChooser();
			fc.getExtensionFilters().addAll(
	    		new ExtensionFilter("txt Files", "*.txt"));
			File selectedFile = fc.showOpenDialog(null);
			return selectedFile.getAbsolutePath();
	}
	
	public void read(ArrayList<ShapeEMR> shapeList) throws IOException
	{
		ShapeEMR newShape = null;
		try {
		    
		    BufferedReader br = new BufferedReader(new FileReader(openFileChooser()));
		    
		    // read until end of file
		    String line;
		    
		    while ((line = br.readLine()) != null) {
			    String[] tokens = line.split(",");
			    double xCoord = Double.valueOf(tokens[1]);
			    double yCoord = Double.valueOf(tokens[2]);
			    String mainColor = tokens[3];
			    String borderColor = tokens[4];
			    if (tokens[0].contentEquals("PowerSource")) {
			    	newShape = shapeFactory.getShape(ShapeFactory.eshape.sourcePower, xCoord, yCoord, mainColor, borderColor);
			    	drawingBoard.getChildren().add(newShape.createShape());
			    	shapeList.add(newShape);
			    } else if (tokens[0].contentEquals("AccumulationPower")) {
			    	newShape = shapeFactory.getShape(ShapeFactory.eshape.accumulationPower, xCoord, yCoord, mainColor, borderColor);
			    	drawingBoard.getChildren().add(newShape.createShape());
			    	shapeList.add(newShape);
			    }
		    	
		        System.out.println(line);
		    }

		 
		    br.close();

		} catch (IOException ex) {
		    ex.printStackTrace();
		}
		
	}
	
}
