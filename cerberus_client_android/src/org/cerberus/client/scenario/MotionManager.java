package org.cerberus.client.scenario;

public class MotionManager {

	private static boolean profilingMode = true;
	
	private MotionManager() {
	}
	
	private static MotionList instance;

	public static MotionList getInstance() {
		
		if ( instance == null ) {
					instance = new MotionList();
		}
		
		return instance;
		
	}

	public static boolean isProfilingMode() {
		return profilingMode;
	}

	public static void setProfilingMode(boolean profilingMode) {
		MotionManager.profilingMode = profilingMode;
	}
	
	
	
}
