package sw.gson.trace;
//5
public class IndexNode2 implements IndexFactory{
	
	private String index;
	private String total;
	private String calls;
	private int usec;
	private String methodName;

	public void setMain2(String _index, String _total, String _calls,
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
	
	@Override
	public String getClsName() {
		// TODO Auto-generated method stub
		return this.methodName;
	}
	
	public int getIndex() {
		// TODO Auto-generated method stub
	
		return Integer.parseInt(this.index.substring(1, this.index.length()-1));
	}

	@Override
	public String getTotal() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCalls() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getUsec() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getMethodName() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
