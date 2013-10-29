package sw.gson.trace;
//5
public class IndexNode2 implements IndexFactory{
	
	private String index;
	private String total;
	private String calls;
	private int usec;
	private String methodName;

	public void setMain(String _index, String _total, String _calls,
			String _usecs, String _name) {
		this.index = _index;
		this.total = _total;
		this.calls = _calls;
		this.usec = Integer.parseInt(_usecs);
		this.methodName = _name;	
	}

	@Override
	public void setMain(String _index, String _total, String _calls,
			String _usecs, String _name, String _name2) {
		// TODO Auto-generated method stub
		
	}
	

}
