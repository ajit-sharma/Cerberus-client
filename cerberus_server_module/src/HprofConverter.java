import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class HprofConverter {

	private static String filename = "dump";
	private static String extension = ".hprof";
	private static String convfilename = "conv-dump";

	public HprofConverter(){}	
	
	public void convert() {

		if (filechecker(filename + extension)) {
			try {
				Process oProcess = new ProcessBuilder("./hprof-conv",filename+extension,
						convfilename+extension).start();
				if (filechecker(filename + extension))
				{
					System.out.println("Success to generate file!");
				}
				else
					System.out.println("Fail to generate file!");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private boolean filechecker(String file) {
		try {
			FileReader filereader = new FileReader(file);
			return true;
		} catch (FileNotFoundException e) {
			System.out.println(" Not Exist");
			e.printStackTrace();
			return false;
		}
	}
	
}
