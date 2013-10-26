import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class TraceFileHandler {

	private String filename = "dmtrace";
	private String extension = ".trace";
	private String conv_extension = ".txt";
    private PrintWriter out;
    
	public void TraceFileHandler() {

	}

	public boolean filechecker() {
		try {
			FileReader filereader = new FileReader(filename+extension);
			return true;
		} catch (FileNotFoundException e) {
			System.out.println(" Not Exist");
			e.printStackTrace();

			return false;
		}
	}

	public void convert() {
		
	
		if (filechecker()) {
			
			try {
				File f1 = new File(filename+conv_extension);
				out = new PrintWriter(new FileWriter(f1, false)); 
				Process process = Runtime.getRuntime().exec("./dmtracedump " + filename + extension);
				BufferedReader br = new BufferedReader(new InputStreamReader(
						process.getInputStream()));
				Scanner scanner = new Scanner(br);
				scanner.useDelimiter(System.getProperty("line.separator"));
				while (scanner.hasNext())
					out.println(scanner.next());
				scanner.close();
				br.close();

			} catch (IOException e) {
				e.printStackTrace();
				System.exit(1); //명령어가 없으면 종료.
				// TODO Auto-generated catch block
				
			}
		}
	}
}
