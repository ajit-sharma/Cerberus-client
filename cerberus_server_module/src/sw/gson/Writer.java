package sw.gson;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Writer {
	
	public void write(String filename, String jsonstring)
	{
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(filename + ".json"));
			writer.write(jsonstring);		
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
