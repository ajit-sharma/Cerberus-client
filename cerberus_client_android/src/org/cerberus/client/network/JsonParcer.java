package org.cerberus.client.network;

import java.util.Collection;
import java.util.HashMap;

import com.google.gson.*;

public class JsonParcer<T> {

	private Gson gson;
	
	
	
	public JsonParcer() {
		
		gson = new Gson();
	
	}
 
	public String toJson(Collection<T> list) {
		
		return gson.toJson( list );
		
	}

	public String toJson(T obj) {
		
		return gson.toJson(obj);
		
	}
//
//	@Override
//	public String toString() {
//		return gson.toString();
//	}
	
	public Gson fromJson(String str) {
		
		Gson gson = new Gson();
		
		gson.fromJson(str, HashMap.class);
		
		return gson;
		
	}
	
	
}
