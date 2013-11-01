package sw.gson.trace;

import java.util.ArrayList;
import java.util.Iterator;

public class Nodes {

	private int index = -1;
	private String total;
	private String calls;
	private int usec;
	private String methodName;
	private ArrayList<Nodes> child_index = new ArrayList<Nodes>();
	
	public Nodes(){}
	
	public void setNode(NodeInfo info, int _index)
	{
		
		MainTree tmp;
		
		this.index = _index;
		this.total = info.getMainNode(_index).getIndexNode().getTotal();
		this.calls = info.getMainNode(_index).getIndexNode().getCalls();
		this.usec = info.getMainNode(_index).getIndexNode().getUsec();
		this.methodName = info.getMainNode(_index).getIndexNode().getMethodName();
		System.out.println(this.index+ " " + this.methodName);
	
		ArrayList<Integer> childindex = info.getMainNode(_index).getChildIndex();
		Iterator<Integer> iter = childindex.listIterator();
		int iter_i = 0;
		int iter_i2 = 0;
		while(iter.hasNext())//iter -> child's index
		{
			
			iter_i = iter.next();
			tmp = new MainTree();
			tmp = info.getMainNode(iter_i);
			//iter_i(자식노드)에 해당하는 인덱스노드를 찾아 Nodes 형태로 변환해준다. 
			if(tmp != null)
			{
				Nodes tmpchild = new Nodes();
				tmpchild = tmp.ConvertNode(tmp.getIndexNode());
				this.child_index.add(tmpchild);
				this.child_index.get(iter_i2++).setNode(info,iter_i);
			}
		}		
	}
	
	public void setNode(int _index, String _total, String _calls,
			int _usecs, String _name)
	{
		this.index = _index;
		this.total = _total;
		this.calls = _calls;
		this.usec = _usecs;
		this.methodName = _name;		
	}
	
	public int getIndex(Nodes node)
	{
		return this.index;
	}
	public String getTotal(Nodes node)
	{
		return this.total;
	}
	
	public String getCalls(Nodes node)
	{
		return this.calls;
	}
	
	public int getUsec(Nodes node)
	{
		return this.usec;
	}
	public String getMethodName(Nodes node)
	{
		return this.methodName;
	}
	
	
	public int getIndex() {
		// TODO Auto-generated method stub
		return this.index;
	}
	
	
}
