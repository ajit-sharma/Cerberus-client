package org.cerberus.client.network;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

public class NetworkWorker extends Thread {

	private static final String SERVER_URL = "";
	
	private static BlockingQueue<Map> bQueue = new ArrayBlockingQueue<Map>(100);
	
	private Thread t = null;

	@Override
	public synchronized void start() {
		// TODO Auto-generated method stub
		super.start();
		
		this.t = this;
	}
	
	public Thread getThread() {
		return t;
	}

	public void run() {
		
		while(this.interrupted()) {
			try {
//				Map map = bQueue.take();
//				
//				String uri = (String) map.get("uri");
//				Object data = map.get("data");
//
//				HttpClient client = new DefaultHttpClient();
//				
//				HttpPost post = new HttpPost(SERVER_URL + uri);
//				
//				List params = new ArrayList();
//				
//				JsonParcer p = new JsonParcer();		
//				
//				Map m = (Map) data;
//				
//				Set keyset = m.keySet();
//				
//				Iterator<String> it = keyset.iterator();
//				
//				while(it.hasNext()) {
//					String key = it.next();
//				
//					params.add(new BasicNameValuePair(key, (String) m.get(key) ));
//					
//					UrlEncodedFormEntity encodEntity = new UrlEncodedFormEntity(params, HTTP.UTF_8);
//					post.setEntity(encodEntity);
//
//				}
//				
//				HttpResponse response = client.execute(post);

				System.out.println("-------------------");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	public static synchronized void sendHttpPost(String uri, Map data) throws Exception{
		Map map = new HashMap<String, Object>();
		map.put("uri", uri);
		map.put("data", data);
		
		bQueue.add(map);
	}
	
	
}
