package sw.cerberus.gson.hprof;

import java.util.ArrayList;

public class RefObject {

	private String instancename = "N/V";
	private long size = 0;
	private String instanceid = "N/V";
	private ArrayList<RefObject> children_attributes = new ArrayList<RefObject>();
	// String[]>();

	public void addRef(RefObject _obj)
	{
		this.children_attributes.add(_obj);
	}
	
	public RefObject(String _name, long _size) {
		this.instancename = _name;
		this.size = _size;
	}

	public RefObject(String _name, long _size, String _id) {
		this.instancename = _name;
		this.size = _size;
		this.instanceid = _id;
	}
	public RefObject() {
		// TODO Auto-generated constructor stub
	}

	public void setObjectChain(String _name, long _size) {
		this.instancename = _name;
		this.size = _size;
	}
	
	public void setObjectChain(String _name, long _size, String _id) {
		this.instancename = _name;
		this.size = _size;
		this.instanceid = _id;
	}

	// public void setObjectChain(String _name, String _id, String[] _reference)
	// {
	// this.name = _name;
	// this.id = _id;
	// this.setRef(_id,_reference);
	// }

	public String getName() {
		return instancename;
	}

	public void setName(String name) {
		this.instancename = name;
	}
	
	public void setId(String _id)
	{
		this.instanceid = _id;
	}

	//
	// public Map<String, String[]> getReference() {
	// return children_attributes;
	// }
	//
	//
	//
	// public void setReference(Map<String, String[]> children_attributes) {
	// this.reference = children_attributes;
	// }
	//
	//
	//
	// public void setRef(String srcId, String[] destId)
	// {
	// this.reference.put(srcId, destId);
	// }
	//

}
