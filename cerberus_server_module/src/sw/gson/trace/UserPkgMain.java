package sw.gson.trace;

import java.util.ArrayList;

public class UserPkgMain {
	private ArrayList<UserPkg> USER_PACKAGE = new ArrayList<UserPkg>();
	
	public void setUserPkg(UserPkg _pkg)
	{
		this.USER_PACKAGE.add(_pkg);
	}
	public ArrayList getPkg()
	{
		return this.USER_PACKAGE;
	}
}
