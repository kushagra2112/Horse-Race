package com.service.provider.service;

import java.util.ArrayList;
import java.util.List;

import com.service.provider.model.HorseDataResult;

public interface FilterConditions {
	
	List<HorseDataResult> isEqual(List<HorseDataResult> horseDetails, String value);
	List<HorseDataResult> isGreatedThan(List<HorseDataResult> horseDetails, String value);
	List<HorseDataResult> isLessThan(List<HorseDataResult> horseDetails, String value);
	List<HorseDataResult> isBetween(List<HorseDataResult> horseDetails, String value);
	List<HorseDataResult> isStringEqual(List<HorseDataResult> horseDetails, String value);
	List<HorseDataResult> isStringOneOf(List<HorseDataResult> horseDetails, String value);

}
