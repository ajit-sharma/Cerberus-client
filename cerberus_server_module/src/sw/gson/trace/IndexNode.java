package sw.gson.trace;
//6
public class IndexNode implements IndexFactory{
	
	private String index = "N/I";
	private String total;
	private String calls;
	private int usec;
	private String methodName;

	public void setMain(String _index, String _total, String _calls,
			String _usecs, String _name, String _name2) {
		this.index = _index;
		this.total = _total;
		this.calls = _calls;
		this.usec = Integer.parseInt(_usecs);
		this.methodName = _name + " " + _name2;		
	}

	@Override
	public void setMain(String _index, String _total, String _calls,
			String _usecs, String _name) {
		// TODO Auto-generated method stub
		
	}
	

}
