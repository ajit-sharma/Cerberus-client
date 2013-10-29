package org.cerberus.sdk.network;

import java.util.Collection;

import com.google.gson.Gson;

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
	
	
	
}
