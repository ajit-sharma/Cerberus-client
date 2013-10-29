package org.cerberus.sdk.profile.network;

public class NetworkVO {

	private Long latency;
	private String methodName;
	private String className;
	private Integer lineNumber;
	private String url;

	public Long getLatency() {
		return latency;
	}

	public void setLatency(Long latency) {
		this.latency = latency;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Integer getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(Integer lineNumber) {
		this.lineNumber = lineNumber;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "NetworkVO [latency=" + latency + ", methodName=" + methodName
				+ ", className=" + className + ", lineNumber=" + lineNumber
				+ ", url=" + url + "]";
	}

}
