package sw.cerberus.gson.hprof;

import java.util.ArrayList;

public class MainObject {
	
	private ArrayList<HistoObjectForJson> histogram = new ArrayList<HistoObjectForJson>();
	private ArrayList<Classes> classes = new ArrayList<Classes>();
	//private Classes _classes;

	
	public void MainObject() {
		classes = new ArrayList<Classes>();
		histogram = new ArrayList<HistoObjectForJson>();
	}

	public void addHisto(HistoObjectForJson next) {
		histogram.add(next);		
	}
	
	
	public void setClass(Classes cls){
		classes.add(cls);
	}

	public ArrayList<HistoObjectForJson> getHisto() {
		return this.histogram;
	}
	

//	public HistoObjectForJson setHisto(HistoObjectForJson histo, String name, int cnt, 
//			long total, int befcnt, long beftotal) {
//		histo = new HistoObjectForJson();
//		histo.setClassName(name);
//		histo.setcnt(befcnt-cnt);
//		histo.setsize(beftotal - total);
//		histogram.add(histo);
//		return histo;
//	}
//	
//	public HistoObjectForJson setHisto(HistoObjectForJson histo, String name, int cnt, 
//			long total) {
//		histo = new HistoObjectForJson();
//		histo.setClassName(name);
//		histo.setcnt(0);
//		histo.setsize(0);
//		histogram.add(histo);
//		return histo;
//	}
	
	
	public HistoObject setHisto(HistoObject histo, String name, int cnt, 
			long total, int befcnt, long beftotal) {
		histo = new HistoObject();
		HistoObjectForJson histojson = new HistoObjectForJson(name, cnt-befcnt,total-beftotal);
		histo.setClassName(name);
		histo.setInstanceCnt(cnt);
		histo.setTotalSize(total);
		histo.setbefInstanceCnt(befcnt);
		histo.setbefTotalSize(beftotal);
		histogram.add(histojson);
		return histo;
	}
	
	public HistoObject setHisto(HistoObject histo, String name, int cnt, 
			long total) {
		histo = new HistoObject();
		HistoObjectForJson histojson = new HistoObjectForJson(name, 0, 0);
		histo.setClassName(name);
		histo.setInstanceCnt(cnt);
		histo.setTotalSize(total);
		histogram.add(histojson);
		return histo;
	}
	
	public void setInstance(Classes cls, AllInstance inst, String id, String name, int size) {
		inst = new AllInstance();
		cls.addInstance(inst, id, name,size);
	}


}
