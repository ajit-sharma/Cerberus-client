package org.cerberus.client.scenario;

public class MotionVO {

	private String type;
	private String id;
	private String param;
	private String sleep;
	
	
	
	public MotionVO() {
		this.type = "";
		this.id = "";
		this.param = "";
	}
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}
	
	
	public String getSleep() {
		return sleep;
	}


	public void setSleep(String sleep) {
		this.sleep = sleep;
	}


	@Override
	public String toString() {
		return "MotionVO [type=" + type + ", id=" + id + ", param=" + param
				+ "]";
	}
	

}
