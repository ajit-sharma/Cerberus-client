package sw.cerberus.gson.trace;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NodeInfo {
	private int min = 100000;
	public ArrayList<Integer> stack = new ArrayList<Integer>();
	private Map<Integer, MainTree> nodeinfo = new HashMap<Integer, MainTree>();
	
	public NodeInfo(){}
	
	public void setNode(MainTree node)
	{
		System.out.println("!!"+node.getIndex());
		if(this.min > node.getIndex())
			this.min = node.getIndex();
		this.nodeinfo.put(node.getIndex(), node);
		this.stack.add(node.getIndex());
		//this.nodeinfo.
	}
	
	
	public int getMin()
	{
		return this.min;
	}
	public MainTree getMainNode(int index)
	{
		return this.nodeinfo.get(index);
	}
	
//	public ArrayList<ChildFactory> getChild(MainTree node)
//	{
//		return node.getChildNode();
//	}

}
