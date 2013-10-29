package sw.gson.trace;

public class MethodNode {
	
	private String index;
	private String total;
	private String self;
	private String calls;
	private int usec;
	private String methodName;
	private int whois;
	
	public MethodNode() {
		this.index = "N/V";
		this.total = "N/V";
		this.self = "N/V";
		this.calls = "0/0";
		this.usec = 0;
		this.methodName = "NoName";
		this.whois = 0;
	}	
	
	public void setMethodname(String _name)
	{
		this.methodName = _name;
	}
	
	public void setSelf(String _self)
	{
		this.self = _self;
	}
	
	public void setCalls(String _calls)
	{
		this.calls = _calls;
	}
	
	public void setUsecs(int _usec)
	{
		this.usec = _usec;
	}
	
	public void setIndex(String _index)
	{
		this.index = _index;
	}
	
	public void ascendWhois()
	{
		this.whois++;
	}
	
	public int getWhois()
	{
		return whois;
	}

	public void setTotal(String _total) {
		// TODO Auto-generated method stub
		this.total = _total;
	}

	public void setMain(String _index, String _total, String _calls,
			String _usecs, String _name, String _name2) {
		this.index = _index;
		this.total = _total;
		this.calls = _calls;
		this.usec = Integer.parseInt(_usecs);
		this.methodName = _name + " " + _name2;		
	}
	
	public void setMain(String _index, String _total, String _calls,
			String _usecs, String _name) {
		this.index = _index;
		this.total = _total;
		this.calls = _calls;
		this.usec = Integer.parseInt(_usecs);
		this.methodName = _name;	
	}

	public void setParent(String _index, String _self, String _calls,
			String _usecs, String _name, String _name2) {
		
		this.index = _index;
		this.self = _self;
		this.calls = _calls;
		this.usec = Integer.parseInt(_usecs);
		this.methodName = _name + " " + _name2;		
	}
	
	public void setaParent(String _index, String _calls,
			String _usecs, String _name, String _name2) {
		
		this.index = _index;
		this.calls = _calls;
		this.usec = Integer.parseInt(_usecs);
		this.methodName = _name + " " + _name2;		
	}

	public void setExcl(String _index,  String _self, String _usecs ) {
		
		this.index = _index;
		this.self = _self;
		this.usec = Integer.parseInt(_usecs);
	}
	

}
