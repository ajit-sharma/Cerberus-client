package sw.gson.trace;

import java.util.ArrayList;

public class UserPkg {
	
	private String PACKAGE = "N/V";
	private ArrayList CLASS = new ArrayList();
	
	public void setPackage(String pkg)
	{
		this.PACKAGE = pkg;
	}
	
	public void setCls(String cls)
	{
		this.CLASS.add(cls);
	}
	
	public String getPackage()
	{
		return this.PACKAGE;
	}
	
	public ArrayList getCls()
	{
		return this.CLASS;
	}
	

}
