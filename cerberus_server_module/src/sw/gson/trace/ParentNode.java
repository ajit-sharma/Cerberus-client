package sw.gson.trace;

//6

public class ParentNode implements ParentFactory {
	
	private String index;
	private String self;
	private String calls;
	private int usec;
	private String methodName;

	public void setParent(String _self, String _index, String _calls,
			String _usecs, String _name, String _name2) {
		
		this.index = _index;
		this.self = _self;
		this.calls = _calls;
		this.usec = Integer.parseInt(_usecs);
		this.methodName = _name + " " + _name2;		
	}
	
	public void setParent3(String _self, String _index, String _calls,
			String _usecs, String _name) {
		
		this.index = _index;
		this.self = _self;
		this.calls = _calls;
		this.usec = Integer.parseInt(_usecs);
		this.methodName = _name;		
	}

	@Override
	public void setParent2(String _index, String _calls, String _usecs,
			String _name, String _name2) {
		// TODO Auto-generated method stub
		
	}

}
