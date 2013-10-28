package sw.gson;

import java.util.ArrayList;


public class AllInstance {
	
	//private String ClassName = "No Name";
	private String InstanceId = "0x";
	private String InstanceName = "No Name";
	private int Size = 0;
	//private ArrayList<AllInstance> instances = new ArrayList<AllInstance>();
	
	//public void setClassName(String _cname) { this.ClassName = _cname; }
	public void setInsName(String _name) { this.InstanceName = _name; }
	public void setSize(int _size) { this.Size = _size; }
	public void setInsId(String _id) { this.InstanceId = _id; }
	
	
	public void setinstance(AllInstance inst, String id, String name, int size) {
		this.InstanceId = id;
		this.InstanceName = name;
		this.Size = size;
		
//		inst = new AllInstance();
//	//	inst.setClassName(cname);
//		inst.setInsId(id);
//		inst.setInsName(name);
//		inst.setSize(size);
//		instances.add(inst);
	}

}
