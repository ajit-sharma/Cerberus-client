package power_log_parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class FileReading {
	private String filename = "PowerTrace1382413409905.log";
	private PowerObject pwrobj;
	private ArrayList<PowerObject> result  = new ArrayList<PowerObject>();

	public FileReading(String filename) {

		this.filename = filename;

	}

	public ArrayList<PowerObject> readfile() {

		try {

			ArrayList datalist = new ArrayList();
			String data = "";
			int component_chk = 0;

			BufferedReader in = new BufferedReader(new FileReader(this.filename));
			while ((data = in.readLine()) != null) {
				String[] tmparr = data.split(" ");

				if (tmparr[0].compareTo("begin") == 0 && component_chk == 0) {
					component_chk++;
					pwrobj = new PowerObject();
					pwrobj.setBegin(tmparr[1]);
//					System.out.print(tmparr[0] + "	" + tmparr[1]);
				}

				else if (tmparr[0].compareTo("CPU") == 0 && component_chk != 0) {
					component_chk++;
					pwrobj.setCPU(tmparr[1]);
//					System.out.print(tmparr[0] + "	" + tmparr[1]);
				}

				else if (tmparr[0].compareTo("Wifi") == 0 && component_chk != 0) {
					component_chk++;
					pwrobj.setWIFI(tmparr[1]);
//					System.out.print(tmparr[0] + "	" + tmparr[1]);
				}

				else if (tmparr[0].compareTo("3G") == 0 && component_chk != 0) {
					component_chk++;
					pwrobj.setThreeG(tmparr[1]);
//					System.out.print(tmparr[0] + "	" + tmparr[1]);
				}

				else if (tmparr[0].compareTo("GPS") == 0 && component_chk != 0) {
					component_chk++;
					pwrobj.setGPS(tmparr[1]);
//					System.out.print(tmparr[0] + "	" + tmparr[1]);
				}
				
				if(component_chk > 4)
				{
					component_chk = 0;
					result.add(pwrobj);
//					System.out.println();
				}
				// System.out.println(data);
			}

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
