package org.cerberus.client.profile.battery.service;

public class PowerEstimatorCheck {

	public static Integer RUN = 0;
	public static Integer DIE = 1;
	
	public static Integer state;
	
	public static void updateState(int stateCode){
		state = stateCode;
	}
	
	public static Integer getState() {
		return state;
	}
}
