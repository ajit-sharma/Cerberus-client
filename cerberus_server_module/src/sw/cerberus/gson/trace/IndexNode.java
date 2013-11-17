package sw.cerberus.gson.trace;

import java.util.ArrayList;

//6
public class IndexNode implements IndexFactory{
	
	private String index;
	private String total;
	private String self;
	private String calls;
	private int usec;
	private int excl;
	private String methodName = "N/N";

	public void setExcl(String _self, String _excl)
	{
		this.self = _self;
		this.excl = Integer.parseInt(_excl);
	}
	
	
	public void setMain(String _index, String _total, String _calls,
			String _usecs, String _name, String _name2) {
		this.index = _index;
		this.total = _total;
		this.calls = _calls;
		this.usec = Integer.parseInt(_usecs);
		this.methodName = _name + " " + _name2;	
	}

	
	@Override
	public void setMain2(String _index, String _total, String _calls,
			String _usecs, String _name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getClsName() {
		// TODO Auto-generated method stub
		return this.methodName;
	}
	
	public String getTotal()
	{
		return this.total;
	}
	
	public String getCalls()
	{
		return this.calls;
	}
	
	public int getUsec()
	{
		return this.usec;
	}
	public String getMethodName()
	{
		return this.methodName;
	}
	
	public int getIndex() {
		// TODO Auto-generated method stub
	
		return Integer.parseInt(this.index.substring(1, this.index.length()-1));
	}


	@Override
	public String getSelf() {
		// TODO Auto-generated method stub
		return this.self;
	}


	@Override
	public int getExcl() {
		// TODO Auto-generated method stub
		return this.excl;
	}
	
	

}
