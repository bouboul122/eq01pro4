package model;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javafx.scene.shape.Shape;
/* essaie une nouvelle facon decrire apres souper*/
public class WriteText implements WriteBehavior{
	
	@Override
	public void write(String path, ArrayList<ShapeEMR> objectList) throws IOException
	{
		
		try {

		    BufferedWriter bw = new BufferedWriter(new FileWriter(path));
		    for (int i = 0; i < objectList.size(); i++) {
				bw.write(objectList.get(i).toString());
				bw.newLine();
				System.out.println(objectList.get(i).toString());
			}
		    bw.close();

		} catch (IOException ex) {
		    ex.printStackTrace();
		}
		

	}

}
