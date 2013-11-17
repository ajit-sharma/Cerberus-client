package sw.cerberus.gson.trace;

import java.util.ArrayList;
import java.util.Iterator;

public class RecursiveNodes {

	private int index = -1;
	private String total;
	private String self;
	private int calls;
//	private int usec;
	private int excl;
	private String methodName;
	private ArrayList<RecursiveNodes> children_attributes = new ArrayList<RecursiveNodes>();
	
	public RecursiveNodes(){}
	
	public void setinitNode(NodeInfo info)
	{
		MainTree tmp = new MainTree();;		
		this.index = 0;
//		try{
		this.total = "0";
//		}
//		catch(Exception e)
//		//{
			//System.out.println(_index);
//		}
		this.self = "0";
		this.calls = 0;
//		this.usec = info.getMainNode(_index).getIndexNode().getUsec();
		this.excl = 0;
		this.methodName = "START";
		
		int indexcnt = 0;
		while(info.stack.size() != 0)
		{
			System.out.println("[] " + info.stack.get(0));
			RecursiveNodes tmpchild = new RecursiveNodes();
			tmp = new MainTree();
			if(Double.parseDouble(info.getMainNode(info.stack.get(0)).getIndexNode().getTotal().split("%")[0]) >= 1.0)
			{
			tmpchild = tmp.ConvertNode(info.getMainNode(info.stack.get(0)).getIndexNode());
			System.out.println(tmpchild.getIndex() + "is added");
			this.children_attributes.add(tmpchild);
			this.children_attributes.get(indexcnt++).setNode(info,info.stack.get(0));
			}
			else
			{
				System.out.println(info.stack.get(0) + "is removed " + info.stack.size());
				info.stack.remove(info.stack.indexOf(info.stack.get(0)));
			}

		}
		
//		RecursiveNodes tmpchild = new RecursiveNodes();
//		tmpchild = tmp.ConvertNode(info.getMainNode(_index).getIndexNode());
////		System.out.println(info.getMainNode(iter_i).getIndexNode().getUsec()  + "==" + this.usec);
//		
//			this.children_attributes.add(tmpchild);
//			this.children_attributes.get(0).setNode(info,_index);
	}
	
	//RECURSIVE TREE SETTING
	public void setNode(NodeInfo info, int _index)
	{		
		if(info.stack.size() > 0 && info.stack.contains(_index))
		{
			info.stack.remove(info.stack.indexOf(_index));
			
			MainTree tmp;		
			this.index = _index;
			this.total = info.getMainNode(_index).getIndexNode().getTotal();
			this.self = info.getMainNode(_index).getIndexNode().getSelf();
			this.calls = Integer.parseInt(info.getMainNode(_index).getIndexNode().getCalls().split("[+]")[0]);
			this.excl = info.getMainNode(_index).getIndexNode().getExcl();
			this.methodName = info.getMainNode(_index).getIndexNode().getMethodName();
		
			ArrayList<Integer> childindex = info.getMainNode(_index).getChildIndex();
			ArrayList<ChildFactory> childobject = info.getMainNode(_index).getChildObject();
			
			Iterator<Integer> iter = childindex.listIterator();
			Iterator<ChildFactory> iter_c = childobject.listIterator();
			int iter_i = 0;
			int iter_i2 = 0;
			ChildFactory iter_c_i;
			while(iter.hasNext())//iter -> child's index
			{			
				iter_i = iter.next();
				iter_c_i = iter_c.next();
				tmp = new MainTree();
				tmp = info.getMainNode(iter_i);
				//iter_i(자식노드)에 해당하는 인덱스노드를 찾아 RecursiveNodes 형태로 변환해준다. 
				if(tmp != null)
				{
					RecursiveNodes tmpchild = new RecursiveNodes();
					tmpchild = tmp.ConvertNode(tmp.getIndexNode());
					if(Double.parseDouble(info.getMainNode(iter_i).getIndexNode().getTotal().split("%")[0]) 
							<= Double.parseDouble(this.total.split("%")[0])
							&& Double.parseDouble(info.getMainNode(iter_i).
									getIndexNode().getTotal().split("%")[0]) >= 1.0)
					{
						System.out.println(tmpchild.getIndex() + "is added , next is " + iter_i);
						this.children_attributes.add(tmpchild);
						this.children_attributes.get(iter_i2++).setNode(info,iter_i);
					}
				}
			}
		}
				
	}
	
	public void setNode(int _index, String _total,String _self, String _calls,
		String _name)
	{
		this.index = _index;
		this.total = _total;
		this.self = _self;
		this.calls = Integer.parseInt(_calls.split("[+]")[0]);
//		this.usec = _usecs;
//		this.excl = _excl;
		this.methodName = _name;	
	
	}
	
	public int getIndex(RecursiveNodes node)
	{
		return this.index;
	}
	public String getTotal(RecursiveNodes node)
	{
		return this.total;
	}
	
	public int getCalls(RecursiveNodes node)
	{
		return this.calls; 
	}
	
//	public int getUsec(RecursiveNodes node)
//	{
//		return this.usec;
//	}
	public String getMethodName(RecursiveNodes node)
	{
		return this.methodName;
	}
	public String getSelf() {
		// TODO Auto-generated method stub
		return this.self;
	}

	public int getExcl() {
		// TODO Auto-generated method stub
		return this.excl;
	}
	
	public int getIndex() {
		// TODO Auto-generated method stub
		return this.index;
	}
	
	
}
