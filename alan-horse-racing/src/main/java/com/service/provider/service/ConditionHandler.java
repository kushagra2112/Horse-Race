package com.service.provider.service;

public interface ConditionHandler {
	String conditionNamePresent(String condition);
	String conditionPresent(String condition);
	boolean saveCondition(String condtionName, String ConditionValue);
}
