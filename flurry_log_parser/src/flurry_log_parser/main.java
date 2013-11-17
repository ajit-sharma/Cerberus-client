package flurry_log_parser;

import java.util.ArrayList;
import java.util.Iterator;

public class main {

	public static void main(String[] args) {

		String filename = "";
		if(args.length > 0)
		{
			filename = args[0];
			FileReading fr = new FileReading("TodayBreaker-----------[2013_08_15-2013_11_13]-eventsLog.csv");
			ArrayList<FlurryObject> result = new ArrayList<FlurryObject>();
			result = fr.readfile(filename);				
		}
		else
		{
			System.out.println("Type Filename...");
		}
				
		System.out.println("END");
	}

}
