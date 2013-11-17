package sw.cerberus.gson.hprof;

public class HistoObject {

	private String classname = "No Name";
	private int InstanceCnt = 0;
	private long TotalSize = 0;
	private int befInstanceCnt = 0;
	private long befTotalSize = 0;
	
	public void setClassName(String _name) { this.classname = _name; }
	public void setInstanceCnt(int _cnt) { this.InstanceCnt = _cnt; }
	public void setTotalSize(long _size) { this.TotalSize = _size; }
	public void setbefInstanceCnt(int _cnt) { this.befInstanceCnt = _cnt; }
	public void setbefTotalSize(long _size) { this.befTotalSize = _size; }
	
	public String getClassName() { return this.classname; }
	public int getInstanceCnt() { return this.InstanceCnt; }
	public long getTotalSize() { return this.TotalSize; }
	public int getbefInstanceCnt() { return this.befInstanceCnt; }
	public long getbefTotalSize() { return this.befTotalSize; }	
}
