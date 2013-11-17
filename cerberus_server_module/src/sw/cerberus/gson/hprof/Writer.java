package sw.cerberus.gson.hprof;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Writer {

	public void write(String filename, String jsonstring) {
		try {

			 BufferedWriter writer = new BufferedWriter(new
			 FileWriter(filename + ".json"));
			 String data = "";
			 
			 
			 writer.write(jsonstring);
			 System.out.println("Writing "+filename+".json completed!");
			 
			 writer.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
