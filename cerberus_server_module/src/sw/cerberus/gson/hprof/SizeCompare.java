package sw.cerberus.gson.hprof;

import java.util.Comparator;

public class SizeCompare implements Comparator{

	public int compare(AllInstance ins1, AllInstance ins2) {
		return (ins1.getSize() < ins2.getSize() ? 1:0);
	}

	@Override
	public int compare(Object o1, Object o2) {
		// TODO Auto-generated method stub
		return 0;
	}

}
