package org.cerberus.client.api;

import org.cerberus.client.activity.CerberusActivity;
import org.cerberus.client.runner.AlwaysOnTopService;

import android.content.Context;
import android.content.Intent;

public class Cerberus {

	private Context context;
	
	private static boolean PROFILE_MODE = false;
	private static boolean SCENARIO_MODE = false;
	
	
	public void start(Context context) {
		
		this.context = context;
		
		Intent alwaysOnTopService = new Intent(context, AlwaysOnTopService.class);
		context.startService(alwaysOnTopService);
		
		
		if(!PROFILE_MODE ) {
			
			Intent intent = new Intent(context, CerberusActivity.class);
			
			context.startActivity(intent);
			
		}
		
		
		//Profile Service ∏¶ Ω√¿€
//		profileServiceStart();
		
		
	}
	
//	public void finish() {
//		
//	}
	
	
	private void profileServiceStart() {
		
//		Intent intent = new Intent(context, ProfileService.class);
//		
//		context.startService(intent);
		
	}
}
