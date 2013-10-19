package com.example.testapp;

import android.os.Bundle;
import android.os.Debug;
import android.app.Activity;
import android.view.Menu;


public class MainActivity extends Activity {

	@SuppressWarnings("null")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		Modules modules = new Modules();
		modules.start();
		
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_main);
			
			
		try {
			modules.stop();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
