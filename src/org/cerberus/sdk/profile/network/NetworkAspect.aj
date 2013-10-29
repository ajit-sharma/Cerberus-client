package org.cerberus.sdk.profile.network;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;


public aspect NetworkAspect {

//	pointcut execute() : execution(* HttpClient.execute(..));
	
//	pointcut executeNetwork() : execution(* *.execute(..));

//	before() : executeNetwork() {
//		System.out.println("call......");
//	}
//	
//
//	after() : executeNetwork() {
//		System.out.println("call......");
//	}
	
	
//	HttpResponse around() 
//	: execution(* *.doInBackground(..)) {
//								
//		long startTime = System.currentTimeMillis();
//		HttpResponse response = proceed();
//		long finishTime = System.currentTimeMillis();
//		
//		System.out.println(finishTime - startTime);
//		
//		return response;
//		
//		
//	}
	
	HttpResponse around() throws IOException , ClientProtocolException
	: call(HttpResponse execute(..)) {
		
		Object[] args = thisJoinPoint.getArgs();
		
		String url = null;
		for(Object param : args) {
			
			if(param instanceof HttpPost) {
				HttpPost post = (HttpPost)param;
				
				url = post.getURI().getHost()  + post.getURI().getPath();
				break;
			} else if (param instanceof HttpGet) {
				HttpGet get = (HttpGet) param;
				url = get.getURI().getHost()   + get.getURI().getPath();
				break;
			}
			
		}
		
		long startTime = System.currentTimeMillis();
		HttpResponse response = proceed();
		long finishTime = System.currentTimeMillis();
		
//		System.out.println("call Time - " + (finishTime - startTime));
//		System.out.println(Thread.currentThread().getStackTrace()[3].getMethodName());
//		System.out.println(Thread.currentThread().getStackTrace()[3].getClassName());
//		System.out.println(Thread.currentThread().getStackTrace()[3].getLineNumber());
//		System.out.println(Arrays.toString(Thread.currentThread().getStackTrace()));
//		System.out.println(url);
		
		NetworkVO network = new NetworkVO();
		network.setLatency(finishTime - startTime);
		network.setMethodName(Thread.currentThread().getStackTrace()[3].getMethodName());
		network.setClassName(Thread.currentThread().getStackTrace()[3].getClassName());
		network.setLineNumber(Thread.currentThread().getStackTrace()[3].getLineNumber());
		
		NetworkManager.getInstance().add(network);
		
		return response;
		
	}
//	
//	HttpResponse around() throws IOException , ClientProtocolException
//	: call(HttpResponse execute(..)) {
//		
//		long startTime = System.currentTimeMillis();
//		HttpResponse response = proceed();
//		long finishTime = System.currentTimeMillis();
//		
//		System.out.println("call Time - " + (finishTime - startTime));
//		
//		System.out.println(Thread.currentThread().getStackTrace()[3].getMethodName());
//		System.out.println(Thread.currentThread().getStackTrace()[3].getClassName());
//		System.out.println(Thread.currentThread().getStackTrace()[3].getLineNumber());
//
////		System.out.println(Arrays.toString(Thread.currentThread().getStackTrace()));
//		
//		return response;
//		
//	}
}
