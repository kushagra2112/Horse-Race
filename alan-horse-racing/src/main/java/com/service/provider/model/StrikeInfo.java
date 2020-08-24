package com.service.provider.model;

public class StrikeInfo {
	private String strikeString;
	private float strikeCount;
	public String getStrikeString() {
		return strikeString;
	}
	public void setStrikeString(String strikeString) {
		this.strikeString = strikeString;
	}
	public float getStrikeCount() {
		return strikeCount;
	}
	public void setStrikeCount(float strikeCount) {
		this.strikeCount = strikeCount;
	}
	@Override
	public String toString() {
		return "StrikeInfo [strikeString=" + strikeString + ", strikeCount=" + strikeCount + "]";
	}
	
	
}
