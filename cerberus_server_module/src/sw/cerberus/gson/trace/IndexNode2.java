package sw.cerberus.gson.trace;
//5
public class IndexNode2 implements IndexFactory{
	
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

	@Override
	public String getMethodName() {
		// TODO Auto-generated method stub
		return this.getMethodName();
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
