package com.example.testapp;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import android.os.Debug;
import android.util.Log;
import edu.umich.PowerTutor.service.UMLoggerService;

public class Modules {

	public Modules(){
		
	}
	
	public void start()
	{
		Debug.startMethodTracing("/sdcard/dmtrace");// Method Utilization(CPU)
		try {
			try {
				BufferedWriter bfw = new BufferedWriter(new FileWriter("/sdcard/PowerTrace.log",true));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			UMLoggerService.class.getMethod("onCreate");
			Log.i("TAG","power success");
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.i("TAG","power error");
		}
		//power.onCreate();
	}
	
	public void stop() throws Exception
	{
		Debug.stopMethodTracing();
		Debug.dumpHprofData("/sdcard/dump.hprof");// Method Profiling
	}
	
}
