package org.cerberus.client.scenario;

import java.util.ArrayList;

public class MotionList extends ArrayList<MotionVO>{

	private static long addTime=0;
	
	@Override
	public boolean add(MotionVO object) {
		// TODO Auto-generated method stub
		
		int size = size();
		
		long thisTime = System.currentTimeMillis();
		if (size != 0) {
			
			MotionVO vo = super.get(size);
			vo.setType("sleep");
			vo.setParam(new Long(thisTime-addTime).toString());
			super.add(size, vo);
//			super.add(vo);
		}
		addTime = thisTime;
		
		boolean result = super.add(object); 
		
		return result;
	}
	
}
