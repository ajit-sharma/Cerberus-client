import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class HprofConverter {

	private static String filename = "dump";
	private static String extension = ".hprof";
	private static String convfilename = "conv-dump";

	public HprofConverter(){}	
	
	//안드로이드에서 막 뽑아낸 raw data를 분석이 가능한 파일로 변환하는 작업을 수행한다.
	//여기에서 hprof-conv명령은 본 페키지와 함께 묶어서 가지고 있는다. 
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
	
	//변환을 하기 위한 원본파일이 있는지 확인한다. 
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
