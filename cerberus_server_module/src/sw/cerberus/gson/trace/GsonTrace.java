package sw.cerberus.gson.trace;

import java.util.ArrayList;

public class GsonTrace {

	private ArrayList<MainTrace> METHOD_SEC = new ArrayList<MainTrace>();
	private ArrayList<MainTree> METHOD_TREE = new ArrayList<MainTree>();
	private ArrayList<MainClass> CLASS_DETAIL = new ArrayList<MainClass>();
	private ArrayList<MainMethod> METHOD_DETAIL = new ArrayList<MainMethod>();
	
	public void setMaintrace(MainTrace _methodsec) {
		this.METHOD_SEC.add(_methodsec);
	}

	public void setTree(MainTree _node) {
		this.METHOD_TREE.add(_node);
	}
	
	public void setClassDetail(MainClass _class) {
		this.CLASS_DETAIL.add(_class);
	}
	
	public void setMethodDetail(MainMethod _method) {
		this.METHOD_DETAIL.add(_method);
	}

}
