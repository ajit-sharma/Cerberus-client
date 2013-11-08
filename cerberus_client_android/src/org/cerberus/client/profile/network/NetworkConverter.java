package org.cerberus.client.profile.network;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NetworkConverter {

	public static List<Map<String,String>> convert(NetworkList list) {
		
		List<Map<String,String>> result = new ArrayList<Map<String,String>>();
		
		for(NetworkVO vo : list) {
			
			Map obj = new HashMap();
			
			obj.put("class_name", vo.getClassName());
			obj.put("name", vo.getMethodName());
			obj.put("latency", vo.getLatency());
			obj.put("line_number", vo.getLineNumber());
			obj.put("request_url", vo.getUrl());
			
			result.add(obj);
			
		}
		
		return result;
	}
	
}
