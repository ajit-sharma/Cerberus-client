package sw.gson.trace;

import java.util.ArrayList;

public class GsonTrace {

	private ArrayList<MainTrace> methodsec = new ArrayList<MainTrace>();
	private ArrayList<MainTree> methodtree = new ArrayList<MainTree>();
	private ArrayList<MainClass> methodclass = new ArrayList<MainClass>();
	
	public void setMaintrace(MainTrace _methodsec) {
		this.methodsec.add(_methodsec);
	}

	public void setTree(MainTree _node) {
		this.methodtree.add(_node);
	}
	
	public void setClassMethod(MainClass _class) {
		this.methodclass.add(_class);
	}
	

}
