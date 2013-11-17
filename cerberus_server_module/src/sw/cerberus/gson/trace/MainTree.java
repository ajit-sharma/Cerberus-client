package sw.cerberus.gson.trace;

import java.util.ArrayList;
import java.util.Iterator;

public class MainTree {
	
	private ArrayList<ParentFactory> parentset = new ArrayList<ParentFactory>();
	private IndexFactory index_node = new IndexNode();
	private ArrayList<ChildFactory> childset = new ArrayList<ChildFactory>();
	private int whois = 0; // To check start and end of indexnode
	
	public RecursiveNodes ConvertNode(ChildFactory childnode)
	{
		RecursiveNodes node = new RecursiveNodes();
		node.setNode(childnode.getIndex(), "%", childnode.getSelf(), childnode.getCalls(), childnode.getMethodName());
		return node;
	}

	public RecursiveNodes ConvertNode(IndexFactory indexnode)
	{
		RecursiveNodes node = new RecursiveNodes();
		node.setNode(indexnode.getIndex(), indexnode.getTotal(), indexnode.getSelf(), indexnode.getCalls(), indexnode.getMethodName());
		return node;
	}
	
	public ArrayList<ChildFactory> getChildObject()
	{
		return this.childset;
	}
	
	public ArrayList<Integer> getChildIndex()
	{
		ArrayList<Integer> childindex = new ArrayList<Integer>();
		Iterator<ChildFactory> iter = this.childset.iterator();
		while(iter.hasNext())
		{
			childindex.add(iter.next().getIndex());
		}
		return childindex;
	}
	public IndexFactory getIndexNode()
	{
		return this.index_node;
	}
	public int getIndex()
	{
		return this.index_node.getIndex();
	}
	
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
	
	public String getIndexClass()
	{
		return this.index_node.getClsName();
	}


}
