package sw.cerberus.util;
import java.io.FileReader;


public class HprofConverter {

	private static String filename = "dump";
	private static String extension = ".hprof";
	private static String convfilename = "conv-dump";

	public HprofConverter(){}	
	
	//안드로이드에서 막 뽑아낸 raw data를 분석이 가능한 파일로 변환하는 작업을 수행한다.
	//여기에서 hprof-conv명령은 본 페키지와 함께 묶어서 가지고 있는다. 
	public synchronized void convert(String path, String path2) {

			try {
				int i = 1;
				
				if(filechecker(path + filename + i + extension))
				{
					String currentdir = System.getProperty("user.dir");
//					System.out.println(currentdir);
					Process oProcess = new ProcessBuilder(currentdir + "/public/hprof-conv", path
							+ filename + i + extension, path + convfilename + i
							+ extension).start();
					oProcess.waitFor();
					if (filechecker(path + convfilename + i + extension))
						System.out.println("[" + convfilename + i + extension + "]" + "Success to generate file!");
					else {
						System.out.println("[" + convfilename + i + extension + "]"
					+ "Fail to generate file! I'll be shutdown!");
						System.exit(0);
					}
					
					i=2;
					
					while (filechecker(path2 + filename + i + extension)) {
						oProcess = new ProcessBuilder(currentdir + "/public/hprof-conv", path2
								+ filename + i + extension, path2 + convfilename + i
								+ extension).start();
						oProcess.waitFor();
						if (filechecker(path2 + convfilename + i + extension))
							System.out.println("[" + convfilename + i + extension + "]" + "Success to generate file!");
						else {
							System.out.println("[" + convfilename + i + extension + "]" 
						+ "Fail to generate file! I'll be shutdown!");
							System.exit(0);
						}
						i++;
					}
				}
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
	//변환을 하기 위한 원본파일이 있는지 확인한다. 
	public boolean filechecker(String file) {
		try {
			FileReader filereader = new FileReader(file);
			filereader.close();
			return true;
		} catch (Exception e) {
			System.out.println(file + " Not Exist");
			//e.printStackTrace();
			return false;
		}
	}
	
}
