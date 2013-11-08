package org.cerberus.client.profile.network;

import java.util.ArrayList;

public class NetworkList extends ArrayList<NetworkVO>{

	private static Integer INDEX = 0;
	
	@Override
	public boolean add(NetworkVO object) {
		// TODO Auto-generated method stub
	
		object.setIndex(INDEX++);
		
		return super.add(object);
	}

	
	
}
