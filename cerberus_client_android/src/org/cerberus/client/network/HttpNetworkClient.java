package org.cerberus.client.network;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

public class HttpNetworkClient {

	public interface Method {
		public static final int GET = 1;
		public static final int POST = 2;
		public static final int PUT = 3;
		public static final int DELETE = 4;
	}
	
	public HttpResponse execute(String url, Map data) throws Exception{
		return execute(url, data, Method.POST);
	}
	
	public HttpResponse execute(String url, Map data, int method) throws Exception{
		HttpClient client = new DefaultHttpClient();
		
		System.out.println("------------------");
		
		HttpResponse result = null;
		
		if( method == Method.POST) {
			System.out.println("1------------------");
			HttpPost post = new HttpPost(url);
			
			List params = new ArrayList();
			
			JsonParcer p = new JsonParcer();		
			
			Map m = (Map) data;
			
			Set keyset = m.keySet();
			
			Iterator<String> it = keyset.iterator();
			
			while(it.hasNext()) {
				String key = it.next();
			
				params.add(new BasicNameValuePair(key, (String) m.get(key) ));
				
				UrlEncodedFormEntity encodEntity = new UrlEncodedFormEntity(params, HTTP.UTF_8);
				post.setEntity(encodEntity);
	
			}
			
			HttpResponse response = client.execute(post);
			result = response;
		} else if (method == Method.PUT) {
			System.out.println("2------------------");
			HttpPut post = new HttpPut(url);
			
			List params = new ArrayList();
			
			JsonParcer p = new JsonParcer();		
			
			Map m = (Map) data;
			
			Set keyset = m.keySet();
			
			Iterator<String> it = keyset.iterator();
			
			while(it.hasNext()) {
				String key = it.next();
			
				params.add(new BasicNameValuePair(key, (String) m.get(key) ));
				
				UrlEncodedFormEntity encodEntity = new UrlEncodedFormEntity(params, HTTP.UTF_8);
				post.setEntity(encodEntity);
	
			}
			
			HttpResponse response = client.execute(post);
			result = response;		
		}
		
		
		return result;
	}
	
}
