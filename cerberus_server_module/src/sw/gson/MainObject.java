package sw.gson;

import java.util.ArrayList;

public class MainObject {
	
	private ArrayList<HistoObject> histogram = new ArrayList<HistoObject>();
	
	public void MainObject() {
		
	}

	public void setHisto(HistoObject histo, String name, int cnt,
			long total) {
		histo = new HistoObject();
		histo.setClassName(name);
		histo.setInstanceCnt(cnt);
		histo.setTotalSize(total);
		histogram.add(histo);
	}

}
