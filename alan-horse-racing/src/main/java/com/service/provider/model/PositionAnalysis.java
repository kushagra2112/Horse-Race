package com.service.provider.model;

public class PositionAnalysis {
	private String condition;
	private String qualifier;
	private String positionMarker;
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String getQualifier() {
		return qualifier;
	}
	public void setQualifier(String qualifier) {
		this.qualifier = qualifier;
	}
	public String getPositionMarker() {
		return positionMarker;
	}
	public void setPositionMarker(String positionMarker) {
		this.positionMarker = positionMarker;
	}
	@Override
	public String toString() {
		return "PositionAnalysis [condition=" + condition + ", qualifier=" + qualifier + ", positionMarker="
				+ positionMarker + "]";
	}
	
	
}
