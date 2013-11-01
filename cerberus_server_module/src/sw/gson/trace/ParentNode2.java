package sw.gson.trace;

//5

public class ParentNode2 implements ParentFactory {
	
	private String index;
	private String calls;
	private String usec;
	private String methodName;

	public void setParent2(String _index, String _calls,
			String _usecs, String _name, String _name2) {
		
		this.index = _index;
		this.calls = _calls;
		this.usec = _usecs;
		this.methodName = _name + " " + _name2;		
	}
	
	

	@Override
	public void setParent(String _self, String _index, String _calls,
			String _usecs, String _name, String _name2) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void setParent3(String _index, String _calls, String _usecs,
			String _name, String _name2) {
		// TODO Auto-generated method stub
		
	}

}
