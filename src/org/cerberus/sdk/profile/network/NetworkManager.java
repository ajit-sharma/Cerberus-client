package org.cerberus.sdk.profile.network;


public class NetworkManager {

	private NetworkManager() {
	}

	private static NetworkList instance;

	public static NetworkList getInstance() {

		if (instance == null) {
			instance = new NetworkList();
		}

		return instance;

	}

}
