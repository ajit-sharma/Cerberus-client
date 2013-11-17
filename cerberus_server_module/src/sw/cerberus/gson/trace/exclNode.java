package sw.cerberus.gson.trace;
//3
public class exclNode implements ChildFactory{
	
	private String index = "N/I";
	private String total = "0";
	private int usec = 0;

	public void setMain(String _index, String _total,
			String _usecs) {
		this.index = _index;
		this.total = _total;
		this.usec = Integer.parseInt(_usecs);	
	}

	@Override
	public void setChild(String _self, String _index, String _calls,
			String _usecs, String _name, String _name2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setChild2(String _index, String _calls, String _usecs,
			String _name, String _name2) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public int getIndex() {
		return Integer.parseInt(this.index.substring(1, this.index.length()-1));
	}

	@Override
	public String getSelf() {
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
