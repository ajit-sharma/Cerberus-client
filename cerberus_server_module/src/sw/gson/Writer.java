package sw.gson;

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
//			// ���۸��� �����Ǵ� ��Ʈ��
//			BufferedInputStream bis = null;
//			BufferedOutputStream bos = null;
//			InputStream is ;
//			
//			// ���۸� ���� ��Ʈ��
//			bis = new BufferedInputStream();
//			bos = new BufferedOutputStream(new FileOutputStream(new File(filename + ".json")));
//
////			while (true) {
////				int x = bis.read();
////				if (x == -1) {
////					break;
////				}
////				bos.write(x);
////				System.out.println(bis.toString());
////			}
//
//			bis.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
