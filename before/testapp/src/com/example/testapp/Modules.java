package com.example.testapp;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Debug;
import android.os.RemoteException;
import android.util.Log;
import android.widget.LinearLayout;
import edu.umich.PowerTutor.service.ICounterService;
import edu.umich.PowerTutor.service.UMLoggerService;
import edu.umich.PowerTutor.service.UidInfo;
import edu.umich.PowerTutor.util.Counter;
import edu.umich.PowerTutor.util.SystemInfo;

public class Modules {

	  public static final int KEY_CURRENT_POWER = 0;
	  public static final int KEY_AVERAGE_POWER = 1;
	  public static final int KEY_TOTAL_ENERGY = 2;
	
	 private SharedPreferences prefs;
	 private ICounterService counterService;
	 private int noUidMask;
	 
	 
	public Modules() {
		
//		int keyId = prefs.getInt("topKeyId", KEY_TOTAL_ENERGY);
//
//	      byte[] rawUidInfo;
//		try {
//			rawUidInfo = counterService.getUidInfo(
//			      prefs.getInt("topWindowType", Counter.WINDOW_TOTAL),
//			      noUidMask | prefs.getInt("topIgnoreMask", 0));	
//	   
//	        UidInfo[] uidInfos = (UidInfo[])new ObjectInputStream(
//	            new ByteArrayInputStream(rawUidInfo)).readObject();
//	     for(UidInfo uidInfo : uidInfos) {  
//	        uidInfo.key = uidInfo.totalEnergy;
//            uidInfo.unit = "J";
//            //init(uidInfo);
//	     }
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
	}
	

	public void init(UidInfo uidInfo) {
	      SystemInfo sysInfo = SystemInfo.getInstance();      
	      
	      //PackageManager pm = this.getContext().getPackageManager();
	      //String name = sysInfo.getUidName(uidInfo.uid, pm);
	      String prefix;
	      if(uidInfo.key > 1e12) {
	        prefix = "G";
	        uidInfo.key /= 1e12;
	      } else if(uidInfo.key > 1e9) {
	        prefix = "M";
	        uidInfo.key /= 1e9;
	      } else if(uidInfo.key > 1e6) {
	        prefix = "k";
	        uidInfo.key /= 1e6;
	      } else if(uidInfo.key > 1e3) {
	        prefix = "";
	        uidInfo.key /= 1e3;
	      } else {
	        prefix = "m";
	      }
	      long secs = (long)Math.round(uidInfo.runtime);
	      
//	      textView.setText(String.format("%1$.1f%% [%3$d:%4$02d:%5$02d] %2$s\n" +
//	          "%6$.1f %7$s%8$s",
//	          uidInfo.percentage, name, secs / 60 / 60, (secs / 60) % 60,
//	          secs % 60, uidInfo.key, prefix, uidInfo.unit));
	      
	      Log.i("TAG",String.format("%1$.1f%% [%3$d:%4$02d:%5$02d] %2$s\n" +
	            "%6$.1f %7$s%8$s",
	            uidInfo.percentage, "!!", secs / 60 / 60, (secs / 60) % 60,
	            secs % 60, uidInfo.key, prefix, uidInfo.unit));
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
