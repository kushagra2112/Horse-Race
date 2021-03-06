package com.service.provider.service;

import java.util.List;

import com.service.provider.model.ConditionReceiver;
import com.service.provider.model.DailyHorseDataResult;
import com.service.provider.model.HorseDataResult;

public class FilterExecution {
	
	List<HorseDataResult> executuefilter(
			FilterConditions filterConditionsObject,
			List<HorseDataResult> horseDetails,
			ConditionReceiver condition){
		
		if(condition.getConditionValue().indexOf(",") > 0 ) {
			return filterConditionsObject.isStringEqual(horseDetails, condition.getConditionValue());
		}else if(condition.getConditionStatement().equals("=")
				&& !(condition.getConditionParameter().equalsIgnoreCase("course")
						|| condition.getConditionParameter().equalsIgnoreCase("officialGoing")
						|| condition.getConditionParameter().equalsIgnoreCase("raceSurface")
						|| condition.getConditionParameter().equalsIgnoreCase("raceType")
						|| condition.getConditionParameter().equalsIgnoreCase("classCaptured")
						|| condition.getConditionParameter().equalsIgnoreCase("favorite"))) {
			
			return filterConditionsObject.isEqual(horseDetails, condition.getConditionValue());
			
		}else if(condition.getConditionStatement().equals("=") ) {
			
			return filterConditionsObject.isStringEqual(horseDetails, condition.getConditionValue());
			
		}else if(condition.getConditionStatement().equals("<")) {
			
			return filterConditionsObject.isLessThan(horseDetails, condition.getConditionValue());
			
		}else if(condition.getConditionStatement().equals(">")) {
			
			return filterConditionsObject.isGreatedThan(horseDetails, condition.getConditionValue());
			
		}else if(condition.getConditionStatement().equalsIgnoreCase("between")) {
			
			return filterConditionsObject.isBetween(horseDetails, condition.getConditionValue());
			
		}
		
		
		return null;
	}
	
	List<DailyHorseDataResult> executueDailyfilter(
			FilterConditions filterConditionsObject,
			List<DailyHorseDataResult> horseDetails,
			ConditionReceiver condition){
		
		if(condition.getConditionStatement().equals("=")
				&& !(condition.getConditionParameter().equalsIgnoreCase("course")
						|| condition.getConditionParameter().equalsIgnoreCase("officialGoing")
						|| condition.getConditionParameter().equalsIgnoreCase("raceSurface")
						|| condition.getConditionParameter().equalsIgnoreCase("raceType")
						|| condition.getConditionParameter().equalsIgnoreCase("classCaptured")
						|| condition.getConditionParameter().equalsIgnoreCase("favorite"))) {
			
			return filterConditionsObject.isDailyEqual(horseDetails, condition.getConditionValue());
			
		}else if(condition.getConditionStatement().equals("=") ) {
			
			return filterConditionsObject.isDailyStringEqual(horseDetails, condition.getConditionValue());
			
		}else if(condition.getConditionStatement().equals("<")) {
			
			return filterConditionsObject.isDailyLessThan(horseDetails, condition.getConditionValue());
			
		}else if(condition.getConditionStatement().equals(">")) {
			
			return filterConditionsObject.isDailyGreatedThan(horseDetails, condition.getConditionValue());
			
		}else if(condition.getConditionStatement().equalsIgnoreCase("between")) {
			
			return filterConditionsObject.isDailyBetween(horseDetails, condition.getConditionValue());
			
		}
		
		
		return null;
	}
	
	
	List<String> findUnique(FilterConditions filterConditionsObject,
			List<HorseDataResult> horseDetails){
		return filterConditionsObject.getUniqueValues(horseDetails);
	}

}
