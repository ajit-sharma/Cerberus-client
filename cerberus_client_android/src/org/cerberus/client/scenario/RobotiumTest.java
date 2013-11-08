package org.cerberus.client.scenario;

import android.test.ActivityInstrumentationTestCase2;

import com.jayway.android.robotium.solo.Solo;

public class RobotiumTest extends ActivityInstrumentationTestCase2{

	private Solo solo;
	
	public RobotiumTest() {
		super(null, null);
	}

	@Override
	protected void setUp() throws Exception {

		solo = new Solo(getInstrumentation(), getActivity());
		
	}

	public void testScenario() {
		
//		solo.sleep(5000);
//		
//		solo.clickOnView(solo.getView(R.id.aaa));
//		
//		System.out.println("-adsf-sdf-sad-f");
//		solo.sleep(5000);
//		solo.clickOnView(solo.getView(R.id.adb));
	}
	
	
	
}
