package flurry_log_parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class FlurryTreeInfo {
	
	
	Map<String,String> parent = new HashMap<String,String>();
	Map<String,Integer> nodeinfo = new HashMap<String,Integer>(); // <INDEXNODE, COUNT>
	Map<String,FlurryObject> nodeobj = new HashMap<String,FlurryObject>(); 
	Map<String,ArrayList<String>> children = new HashMap<String,ArrayList<String>>();
	private FlurryTree ftree = new FlurryTree();
	
	public FlurryTreeInfo(){
	}
	
	public ArrayList<String> getChild(String _indexname)
	{
		return this.children.get(_indexname);
	}
	
	public void setChild(FlurryObject _parent, FlurryObject _child)
	{
	//	_parent.setChild(_child);
	}
	
	public void setChild(String _indexname, String _index2, String _name2)
	{
		String key = _indexname;
		String child = _index2+_name2;
		if(this.children.containsKey(key) && !this.children.get(key).contains(child))
			this.children.get(key).add(child);
		else if(!this.children.containsKey(key))
		{
			this.children.put(key,new ArrayList<String>());
			this.children.get(key).add(child);
		}
	}
	
	public void setObj(String _index, String _name,  FlurryObject obj)
	{
		String key = _index+_name;
		this.nodeobj.put(key, obj);
	}
	
	public FlurryTree getTreeObj(String key)
	{
		this.ftree = new FlurryTree();
		this.ftree.setIndex(this.nodeobj.get(key).getIndex());
		this.ftree.setEvent(this.nodeobj.get(key).getEvent());
		return this.ftree;
	}
	public void setInfo(String _index, String _name, String parent, FlurryObject obj)
	{
		String key = _index+_name;
		if (this.nodeinfo.containsKey(key))
		{			
//			System.out.println(this.nodeinfo.get(key) + 1);
			this.nodeinfo.put(key, this.nodeinfo.get(key) + 1);			
		}
		else
		{
			this.nodeinfo.put(key, 1);
			this.nodeobj.put(key, obj);
		}
		
	
		this.parent.put(key, parent);
	}
	
	public int getCnt(String _index, String _name)
	{
		String key = _index+_name;
		return this.nodeinfo.get(key);
	}


}
