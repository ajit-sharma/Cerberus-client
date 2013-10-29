package sw.gson.trace;

import java.util.ArrayList;

public class MainTree {
	
	private ArrayList<ParentFactory> parentset = new ArrayList<ParentFactory>();
	private IndexFactory index_node = new IndexNode();
	private ArrayList<ChildFactory> childset = new ArrayList<ChildFactory>();
	private int whois; // To check start and end of indexnode
	
	public void setParent(ParentFactory _parent)
	{
		this.parentset.add(_parent);
	}	
	
	public void setChild(ChildFactory _child)
	{
		this.childset.add(_child);
	}
	
	public void setIndex(IndexFactory _index)
	{
		this.index_node = _index;
	}
	
	public void ascendWhois()
	{
		this.whois++;
	}
	
	public int getWhois()
	{
		return whois;
	}


}
