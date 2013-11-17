package flurry_log_parser;

import java.util.ArrayList;

public class FlurryObject {
	
	private int index = -1;
	private String event = "N/V";
	private String version = "N/V";
	private String device = "N/V";
	private ArrayList<FlurryObject> children = new ArrayList<FlurryObject>();
	
	public FlurryObject() {
		
	}
	
	
	
	public FlurryObject(String index, String event,
			String version, String device) {		
		this.index = Integer.parseInt(index);
		this.event = event;
		this.version = version;
		this.device = device;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(String _index) {
		this.index = Integer.parseInt(_index);
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String _event) {
		this.event = _event;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String _version) {
		this.version = _version;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String _device) {
		this.device = _device;
	}

	public String toString() {
		return "FlurryObject [index=" + index
				+ ", event=" + event + ", version=" + version + ", device="
				+ device + "]";
	}

}
