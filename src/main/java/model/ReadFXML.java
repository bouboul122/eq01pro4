package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.layout.Pane;

public class ReadFXML implements ReadBehavior{
	
	ClipboardContent content;
	Dragboard db;
	Pattern pattern;
	Matcher matcher;
	Pane drawingBoard;
	ShapeFactory shapeFactory = new ShapeFactory(content, db);
	ArrayList<ShapeEMR> shapeList;
	public ReadFXML(ClipboardContent content, Dragboard db, Pane drawingBoard, ArrayList<ShapeEMR> shapeList) {
		this.content = content;
		this.db = db;
		this.drawingBoard = drawingBoard;
		this.shapeList = shapeList;
	}

	@Override
	public void read(String path, ArrayList<ShapeEMR> objectToWrite) throws IOException {
		try {
		    // create a reader
		    BufferedReader br = new BufferedReader(new FileReader(path));

		    // read until end of file
		    String line;
		    while ((line = br.readLine()) != null) {
		        System.out.println(line);
		        pattern = Pattern.compile("class=\"(.*?)\"");
		        matcher = pattern.matcher(line);
		        matcher.find();
		        String name = matcher.group(1);
		        System.out.println(name);
		        
		        pattern = Pattern.compile(" xCoordinate=\"(.*?)\" ");
		        matcher = pattern.matcher(line);
		        matcher.find();
		        double xCoordinate = Double.valueOf(matcher.group(1));
		        System.out.println(xCoordinate);
		        
		        pattern = Pattern.compile(" yCoordinate=\"(.*?)\" ");
		        matcher = pattern.matcher(line);
		        matcher.find();
		        double yCoordinate = Double.valueOf(matcher.group(1));
		        System.out.println(yCoordinate);
		        
		        pattern = Pattern.compile(" mainColor=\"(.*?)\" ");
		        matcher = pattern.matcher(line);
		        matcher.find();
		        String mainColor = matcher.group(1);
		        System.out.println(mainColor);
		        
		        
		        pattern = Pattern.compile(" borderColor=\"(.*?)\"");
		        matcher = pattern.matcher(line);
		        matcher.find();
		        String borderColor = matcher.group(1);
		        System.out.println(borderColor);
		        
		        if (name.contentEquals("PowerSource")) {
		        	ShapeEMR newShape = shapeFactory.getShape(ShapeFactory.eshape.sourcePower, xCoordinate, yCoordinate, mainColor, borderColor);
			    	drawingBoard.getChildren().add(newShape.createShape());
			    	shapeList.add(newShape);
		        } else if (name.contentEquals("AccumulationPower")) {
		        	ShapeEMR newShape = shapeFactory.getShape(ShapeFactory.eshape.accumulationPower, xCoordinate, yCoordinate, mainColor, borderColor);
			    	drawingBoard.getChildren().add(newShape.createShape());
			    	shapeList.add(newShape);
		        }
		        
		       
		        
		    }

		    // close the reader
		    br.close();

		} catch (IOException ex) {
		    ex.printStackTrace();
		}
		
	}

}
