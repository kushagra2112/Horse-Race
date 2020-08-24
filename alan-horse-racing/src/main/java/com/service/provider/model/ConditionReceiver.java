package com.service.provider.model;

public class ConditionReceiver {
	public String conditionParameter;
	public String conditionStatement;
	public String conditionValue;
	
	public ConditionReceiver() {
		
		
	}
	
	public ConditionReceiver(String conditionParameter, String conditionStatement, String conditionValue) {
		this.conditionParameter = conditionParameter;
		this.conditionStatement = conditionStatement;
		this.conditionValue = conditionValue;
	}
	
	
	public String getConditionParameter() {
		return conditionParameter;
	}
	public void setConditionParameter(String conditionParameter) {
		this.conditionParameter = conditionParameter;
	}
	public String getConditionStatement() {
		return conditionStatement;
	}
	public void setConditionStatement(String conditionStatement) {
		this.conditionStatement = conditionStatement;
	}
	public String getConditionValue() {
		return conditionValue;
	}
	public void setConditionValue(String conditionValue) {
		this.conditionValue = conditionValue;
	}
	
	public String responseInString() {
		return " <br> " +  conditionParameter + " " +  conditionStatement + " " + conditionValue + " ;";
	}
	

	@Override
	public String toString() {
		return "ConditionReceiver [conditionParameter=" + conditionParameter + ", conditionStatement="
				+ conditionStatement + ", conditionValue=" + conditionValue + "]";
	}
	
	
	
}
