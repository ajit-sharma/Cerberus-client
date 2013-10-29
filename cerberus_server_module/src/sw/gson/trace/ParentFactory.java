package sw.gson.trace;

public interface ParentFactory {
	void setParent(String _self, String _index, String _calls,
			String _usecs, String _name, String _name2); 
	void setParent2(String _index, String _calls,
			String _usecs, String _name, String _name2);
}
