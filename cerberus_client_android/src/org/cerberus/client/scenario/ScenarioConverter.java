package org.cerberus.client.scenario;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScenarioConverter {

	public static List< Map<String, String> > convert(MotionList list) {
		
		List result = new ArrayList<Map<String, String>>();
		
		for(MotionVO motion : list) {
			
			Map<String, String> obj = new HashMap<String, String>();
			obj.put("act_type", motion.getType());
			obj.put("param", motion.getParam());
			obj.put("view", motion.getId());
			
			result.add(obj);
		}
		
		return result;
		
	}
	
}
