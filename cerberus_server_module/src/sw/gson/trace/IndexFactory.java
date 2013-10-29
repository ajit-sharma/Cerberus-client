package sw.gson.trace;

public interface IndexFactory {
	
	public void setMain(String _index, String _total, String _calls,
			String _usecs, String _name, String _name2);
	public void setMain(String _index, String _total, String _calls,
			String _usecs, String _name);

}
