package sw.cerberus.gson.hprof;

public class HistoObjectForJson {

	private String classname = "No Name";
	private int differencecnt = 0;
	private long differencesize = 0;
	
	public HistoObjectForJson(){
		
	}
	
	public HistoObjectForJson(String _name, int _instancecnt, long _totalsize){
		this.classname = _name;
		this.differencecnt = _instancecnt;
		this.differencesize = _totalsize;
	}
	
	public void setClassName(String _name) { this.classname = _name; }
	public void setcnt(int _instancecnt) { this.differencecnt = _instancecnt; }
	public void setsize(long _totalsize) { this.differencesize = _totalsize; }
	
	public String getClassName() { return this.classname; }
	public int getcnte() { return this.differencecnt; }
	public long getsize() { return this.differencesize; }
}
