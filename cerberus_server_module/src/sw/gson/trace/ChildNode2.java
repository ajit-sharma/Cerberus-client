package sw.gson.trace;

//5

public class ChildNode2 implements ChildFactory{
	
	private String index;
	private String calls;
	private int usec;
	private String methodName;

	public void setChild2(String _index, String _calls,
			String _usecs, String _name, String _name2) {
		
		this.index = _index;
		this.calls = _calls;
		this.usec = Integer.parseInt(_usecs);
		this.methodName = _name + " " + _name2;		
	}

	@Override
	public void setChild(String _self, String _index, String _calls,
			String _usecs, String _name, String _name2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setMain(String _index, String _total, String _usecs) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public int getIndex() {
		return Integer.parseInt(this.index.substring(1, this.index.length()-1));
	}

}
