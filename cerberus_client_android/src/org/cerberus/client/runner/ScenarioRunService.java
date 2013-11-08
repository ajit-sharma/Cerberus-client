package org.cerberus.client.runner;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class ScenarioRunService extends Service {

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		
		

		Runtime r = Runtime.getRuntime();
		
		Process p = null;
		
		String cmd = "am instrument -w -e class org.cerberus.client.scenario.RobotiumTest#testScenario com.example.servicetest/android.test.InstrumentationTestRunner";
//		"adb","shell",
//		String[] cmdArr = {"adb","shell", "am","instrument","-w","-e","class","com.example.test.TestCase#testScenario","com.example.servicetest/android.test.InstrumentationTestRunner"};
//		
		System.out.println(cmd);
		try {
			p = r.exec(cmd);
//			
			p.waitFor();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			
			
			
			String s=null;
			while((s = br.readLine() ) != null ) {
				System.out.println(s);
			}
//			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	
	
}
