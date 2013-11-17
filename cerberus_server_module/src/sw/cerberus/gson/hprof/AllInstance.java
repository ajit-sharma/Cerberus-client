package sw.cerberus.gson.hprof;

import java.util.ArrayList;


public class AllInstance implements Comparable{
	
	private String instanceid = "0x";
	private String instancename = "No Name";
	private int size = 0;
	private String leak_suspect = "0%";
	private ArrayList<RefObject> children_attributes = new ArrayList<RefObject>();
	
	public void setInsName(String _name) { this.instancename = _name; }
	public void setSize(int _size) { this.size = _size; }
	public void setInsId(String _id) { this.instanceid = _id; }
	
	public void setleaksuspect(long sumedSize) {
		
		this.leak_suspect = String.format("%.2f", (double)this.size / (double)sumedSize * 100)+"%";
	}
	public void setRef(ArrayList<RefObject> reference_list) {
		this.children_attributes = reference_list;		
	}
	
	public String getId()
	{
		return this.instanceid;
	}
	public String getName()
	{
		return this.instancename;
	}
	
	public void setinstance(String id, String name, int size, ArrayList<RefObject> reference_list) {
		this.instanceid = id;
		this.instancename = name;
		this.size = size;
		this.children_attributes = reference_list;
	}
		
	public void setinstance(String id, String name, int size) {
		this.instanceid = id;
		this.instancename = name;
		this.size = size;
	}
	public int getSize()
	{
		return this.size;
	}
	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	

}
