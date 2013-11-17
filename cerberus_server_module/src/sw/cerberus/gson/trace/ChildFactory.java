package sw.cerberus.gson.trace;

public interface ChildFactory {
	public void setChild(String _self, String _index, String _calls,
			String _usecs, String _name, String _name2);
	public void setChild2(String _index, String _calls,
			String _usecs, String _name, String _name2);
	public void setMain(String _index, String _total,
			String _usecs);
	public int getIndex();
	public String getSelf();
	public String getCalls();
	public int getUsec();
	public String getMethodName();

}
