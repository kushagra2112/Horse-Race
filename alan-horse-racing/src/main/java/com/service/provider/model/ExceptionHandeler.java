package com.service.provider.model;

public class ExceptionHandeler {
	private int status;
	private String errorMsg;
	private long timestamp;
	
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	
	
	@Override
	public String toString() {
		return "ExceptionHandeler [status=" + status + ", errorMsg=" + errorMsg + ", timestamp=" + timestamp + "]";
	}
	
	
	
}
