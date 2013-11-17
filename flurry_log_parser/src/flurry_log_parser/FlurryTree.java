package flurry_log_parser;

import java.util.ArrayList;
import java.util.Iterator;

public class FlurryTree {
	
	private int index = -1;
	private String event = "N/V";
	private int count = 1;
	private ArrayList<FlurryTree> children = new ArrayList<FlurryTree>();

	
	public FlurryTree(){
	}
	
	
	public void increaseCnt(){
		this.count++;
	}
	
	public FlurryTree(int _index, String _event){
		this.index = _index;
		this.event = _event;		
	}
	

	public String getEvent() {
		return this.event;
	}

	public int getIndex() {
		return this.index;
	}

	public void setIndex(int index2) {
		this.index = index2;

	}

	public void setEvent(String event2) {
		this.event = event2;
	}

	public FlurryTree setRoot(FlurryTree tmp_ftree) {
		this.index = tmp_ftree.getIndex();
		this.event = tmp_ftree.getEvent();
		System.out.println("Root/"+this.index+"/"+this.event);

		return this;
	}

	public void setChild(FlurryTree tmp_ftree) {
		if(this.index+1 == tmp_ftree.getIndex())
		{
			FlurryTree tmp = new FlurryTree();
			tmp = tmp_ftree;
			this.children.add(tmp);
		}
	}
	
	public int getCnt() {
		// TODO Auto-generated method stub
		return this.count;
	}


	public boolean search(int _index, String _event)
	{
		Iterator<FlurryTree> iter = this.children.iterator();
		while(iter.hasNext())
		{
			
			FlurryTree tmp = iter.next();

			if( tmp.getIndex() == _index && tmp.getEvent().compareTo(_event)==0 )
			{
				return true;
			}
		}
		return false;
	}
	
	public FlurryTree getChild(int _index, String _event)
	{
		
		Iterator<FlurryTree> iter = this.children.iterator();
		while(iter.hasNext())
		{

			FlurryTree tmp = iter.next();
			if( tmp.getIndex() == _index && tmp.getEvent().compareTo(_event)==0 )
			{
				return tmp;
			}
		}
		return new FlurryTree();
	}


	@Override
	public String toString() {
		return "FlurryTree [index=" + index + ", event=" + event + ", count="
				+ count ;
	}
	

//	public FlurryTree(FlurryTreeInfo flrinfo, FlurryTree obj){
//		this.index = obj.getIndex();
//		this.event = obj.getEvent();
//		this.size = flrinfo.getCnt(Integer.toString(this.index), this.event);
//		
//		FlurryTree ftree;
//		try {
//
//			ArrayList<String> tmparr = flrinfo.getChild(this.index + this.event);
//			Iterator<String> iter = tmparr.iterator();
//			
//			while (iter.hasNext()) {
//				String tmp = iter.next();
//				ftree = new FlurryTree();
//				ftree = flrinfo.getTreeObj(tmp);
//				this.children.add(ftree);
//				ftree.setNode(flrinfo, flrinfo.getTreeObj(tmp));
//			}
//		}
//		catch(Exception e)
//		{
//			return;
//		}
//	}
//	public void setNode(FlurryTreeInfo flrinfo, FlurryTree obj) {
//				
//		this.index = obj.getIndex();
//		this.event = obj.getEvent();
//		this.count = flrinfo.getCnt(Integer.toString(this.index), this.event);
//		
//		FlurryTree ftree;
//		try {
//			ArrayList<String> tmparr = flrinfo.getChild(this.index + this.event);
//			Iterator<String> iter = tmparr.iterator();
//			
//			while (iter.hasNext()) {
//				String tmp = iter.next();
//				ftree = new FlurryTree();
//				ftree = flrinfo.getTreeObj(tmp);
//				this.children.add(ftree);
//				ftree.setNode(flrinfo, flrinfo.getTreeObj(tmp));
//			}
//		}
//		catch(Exception e)
//		{
//			return;
//		}
//	}
}
