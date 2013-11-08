package org.cerberus.client.test;

import org.cerberus.client.api.Cerberus;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		Cerberus cerberus = new Cerberus();
		cerberus.start(this);
//		
	}

	
	
}
