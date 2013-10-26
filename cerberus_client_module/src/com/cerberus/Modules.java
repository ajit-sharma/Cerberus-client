package com.cerberus;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import android.os.Debug;

public class Modules {
	  public static final int KEY_CURRENT_POWER = 0;
	  public static final int KEY_AVERAGE_POWER = 1;
	  public static final int KEY_TOTAL_ENERGY = 2;	 
	 
	public Modules() {   		
	}	
	
	public void start()
	{		
		Debug.startMethodTracing("/sdcard/dmtrace");// Method Utilization(CPU)
	}
	
	public void stop() 
	{
		Debug.stopMethodTracing();// Method Profiling
		try {
			Debug.dumpHprofData("/sdcard/dump.hprof");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
}
