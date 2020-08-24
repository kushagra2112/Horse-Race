package com.service.provider.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.provider.model.ConditionSaver;
import com.service.provider.repository.ConditionSaverRepository;

@Service
public class ConditionHandlerImp implements ConditionHandler{
	
	@Autowired
	ConditionSaverRepository conditionSaverRepository;
	
	private static final Logger logger = LogManager.getLogger();
	
	
	@Override
	public boolean saveCondition(String condtionName, String ConditionValue) {
		try {
			ConditionSaver conditionSaver = new ConditionSaver();
			conditionSaver.setConditionName(condtionName);
			conditionSaver.setConditionValue(ConditionValue);
			conditionSaverRepository.saveAndFlush(conditionSaver);
			return true;
		}catch(Exception e) {
			logger.error(e.getMessage());
			return false;
		}
	}

	@Override
	public String conditionNamePresent(String condition) {
		try {
			ConditionSaver conditionSaver = new ConditionSaver();
			conditionSaver =  conditionSaverRepository.findByConditionName(condition);
			if(conditionSaver == null || conditionSaver.getConditionName() == null ||
					conditionSaver.getConditionName().equalsIgnoreCase("")) {
				return null;
			}else{
				return conditionSaver.getConditionValue();
			}
		}catch(Exception e) {
			logger.error(e.getMessage());
			return "Something went wrong";
		}
	}

	@Override
	public String conditionPresent(String conditionValue) {
		try {
			ConditionSaver conditionSaver = new ConditionSaver();
			conditionSaver =  conditionSaverRepository.findByConditionValue(conditionValue);
			if(conditionSaver == null || conditionSaver.getConditionName() == null ||
					conditionSaver.getConditionName().equalsIgnoreCase("")) {
				return null;
			}else{
				return conditionSaver.getConditionName();
			}
		}catch(Exception e) {
			logger.error(e.getMessage());
			return "Something went wrong";
		}
	}

}
