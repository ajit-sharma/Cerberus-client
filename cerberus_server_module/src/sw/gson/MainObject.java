package sw.gson;

import java.util.ArrayList;

public class MainObject {
	
	private ArrayList<HistoObject> histogram = new ArrayList<HistoObject>();
	private ArrayList<Classes> classes = new ArrayList<Classes>();
	//private Classes _classes;
	
	
	public void MainObject() {
		
	}
	
	public void setClass(Classes cls){
		classes.add(cls);
	}

	public void setHisto(HistoObject histo, String name, int cnt,
			long total) {
		histo = new HistoObject();
		histo.setClassName(name);
		histo.setInstanceCnt(cnt);
		histo.setTotalSize(total);
		histogram.add(histo);
	}
	
	
	public void setInstance(Classes cls, AllInstance inst, String id, String name, int size) {
		inst = new AllInstance();
		cls.addInstance(inst, id, name, size);
		//classes.add(inst);
	}

}
