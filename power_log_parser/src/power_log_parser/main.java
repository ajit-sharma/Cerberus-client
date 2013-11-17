package power_log_parser;

import java.util.ArrayList;
import java.util.Iterator;

public class main {
	
	public static void main(String[] args) {
		FileReading fr = new FileReading("PowerTrace1382413409905.log");
		ArrayList<PowerObject> result = new ArrayList<PowerObject>();
		result = fr.readfile();	
		Iterator<PowerObject> iter = result.iterator();
		while(iter.hasNext())
		{
			System.out.println(iter.next().toString());
		}		
		System.out.println(result);
	}
}
