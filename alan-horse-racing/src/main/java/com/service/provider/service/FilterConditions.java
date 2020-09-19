package com.service.provider.service;

import java.util.List;

import com.service.provider.model.DailyHorseDataResult;
import com.service.provider.model.HorseDataResult;

public interface FilterConditions {
	
	List<HorseDataResult> isEqual(List<HorseDataResult> horseDetails, String value);
	List<HorseDataResult> isGreatedThan(List<HorseDataResult> horseDetails, String value);
	List<HorseDataResult> isLessThan(List<HorseDataResult> horseDetails, String value);
	List<HorseDataResult> isBetween(List<HorseDataResult> horseDetails, String value);
	List<HorseDataResult> isStringEqual(List<HorseDataResult> horseDetails, String value);
	List<HorseDataResult> isStringOneOf(List<HorseDataResult> horseDetails, String value);
	List<String> getUniqueValues(List<HorseDataResult> horseDetails);
	
	List<DailyHorseDataResult> isDailyEqual(List<DailyHorseDataResult> horseDetails, String value);
	List<DailyHorseDataResult> isDailyGreatedThan(List<DailyHorseDataResult> horseDetails, String value);
	List<DailyHorseDataResult> isDailyLessThan(List<DailyHorseDataResult> horseDetails, String value);
	List<DailyHorseDataResult> isDailyBetween(List<DailyHorseDataResult> horseDetails, String value);
	List<DailyHorseDataResult> isDailyStringEqual(List<DailyHorseDataResult> horseDetails, String value);
	List<DailyHorseDataResult> isDailyStringOneOf(List<DailyHorseDataResult> horseDetails, String value);

	
}
