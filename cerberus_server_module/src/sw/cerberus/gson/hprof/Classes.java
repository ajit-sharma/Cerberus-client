package sw.cerberus.gson.hprof;

import java.util.ArrayList;

public class Classes {

	private String ClassName = "No Name";
	private ArrayList<AllInstance> instances = new ArrayList<AllInstance>();
	
	public void addInstance(AllInstance inst, String id, String name, int size, ArrayList<RefObject> reference_list) {
		inst = new AllInstance();
		inst.setinstance(id, name, size, reference_list);
		instances.add(inst);
		//System.out.println("Inst Added!");
	}

	public void init(String name) {
		// TODO Auto-generated method stub
		this.ClassName = name;
	}

	public void addInstance(AllInstance inst, String id, String name, int size) {
		inst = new AllInstance();
		inst.setinstance(id, name, size);
		instances.add(inst);
		//System.out.println("Inst Added!");
	}
	
}
