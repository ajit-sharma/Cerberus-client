package sw.gson.trace;

import java.util.ArrayList;

public interface IndexFactory {
	
	public void setMain(String _index, String _total, String _calls,
			String _usecs, String _name, String _name2);
	public void setMain2(String _index, String _total, String _calls,
			String _usecs, String _name);
	
	public String getClsName();

	public String getTotal();
	public String getCalls();
	public int getUsec();
	public int getIndex();
	public String getMethodName();
}
