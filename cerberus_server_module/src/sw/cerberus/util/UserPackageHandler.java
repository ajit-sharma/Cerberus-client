package sw.cerberus.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import sw.cerberus.gson.trace.UserPkg;
import sw.cerberus.gson.trace.UserPkgMain;

public class UserPackageHandler {

	private Map<String,ArrayList> UsrpkgMap = new HashMap<String,ArrayList>();
	private UserPkgMain usrpkg = new UserPkgMain(); 
	
	public UserPackageHandler() {
		
	}
	
	public UserPackageHandler(UserPkgMain usrpkgmain) {
		this.usrpkg = usrpkgmain;
		setMap();
	}

	public void setMap()
	{
		Iterator<UserPkg> iter = usrpkg.getPkg().iterator();
		while(iter.hasNext())
		{
			UserPkg tmp = (UserPkg)iter.next();
			this.UsrpkgMap.put(tmp.getPackage(),tmp.getCls());
//			System.out.println(tmp.getPackage() + " " + tmp.getCls());
		}
		//System.out.println(UsrpkgMap.toString());
	}
	public boolean chkName(String _pkgname)
	{
		return this.UsrpkgMap.containsKey(_pkgname);
	}

	public void removeCerberus() {

		ArrayList<String> strlist = new ArrayList();
		Map<String,ArrayList> tmpusrpkgmap = new HashMap<String,ArrayList>();
		tmpusrpkgmap = this.UsrpkgMap;
		Iterator<String> key = tmpusrpkgmap.keySet().iterator();
		while(key.hasNext())
		{
			String tmp = key.next();
			if(tmp.contains("org.cerberus"))
				strlist.add(tmp);
		}		
		
		Iterator<String> iter = strlist.iterator();
		while(iter.hasNext())
		{
			this.UsrpkgMap.remove(iter.next());
		}
		
		this.UsrpkgMap.remove("android.support.v4.view");
		
	}
	
}
