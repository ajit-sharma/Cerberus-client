package sw.cerberus.gson.trace;

import java.util.ArrayList;

public interface IndexFactory {
	
	public void setMain(String _index, String _total, String _calls,
			String _usecs, String _name, String _name2);
	public void setMain2(String _index, String _total, String _calls,
			String _usecs, String _name);
	
	public String getClsName();
	public void setExcl(String _self, String _excl);
	public String getTotal();
	public String getSelf();
	public String getCalls();
	public int getUsec();
	public int getIndex();
	public int getExcl();
	public String getMethodName();
}
