package com.service.provider.service;

import java.util.List;

import com.service.provider.model.ConditionSaver;

public interface ConditionHandler {
	String conditionNamePresent(String condition);
	String conditionPresent(String condition);
	boolean saveCondition(String condtionName, String ConditionValue);
	List<ConditionSaver> getAllConditions();
}
