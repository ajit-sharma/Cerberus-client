package org.cerberus.client.runner;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class ProfileService extends Service {

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		
		
		//프로파일링 시작 처리 
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		
		//프로파일링 마무리 
	}
	
	

}
