package com.service.provider.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.service.provider.model.HorseDataResult;

public class ClassCapturedFilter implements FilterConditions {

	@Override
	public List<HorseDataResult> isEqual(List<HorseDataResult> horseDetails, String value) {
		return null;
	}

	@Override
	public List<HorseDataResult> isGreatedThan(List<HorseDataResult> horseDetails, String value) {
		return null;
	}

	@Override
	public List<HorseDataResult> isLessThan(List<HorseDataResult> horseDetails, String value) {
		return null;
	}

	@Override
	public List<HorseDataResult> isBetween(List<HorseDataResult> horseDetails, String value) {
		return null;
	}

	@Override
	public List<HorseDataResult> isStringEqual(List<HorseDataResult> horseDetails, String value) {
		
		String localValue = value;
		if(localValue.split(",").length > 1) {
			List<String> classCaptured = new ArrayList<String>(Arrays.asList(value.split("[,]")));
			return horseDetails.stream()
					.filter(d -> classCaptured.contains(d.getClassCaptured()))
					.collect(Collectors.toList());
		}else {
			return horseDetails.stream()
					.filter(d -> d.getClassCaptured().equalsIgnoreCase(localValue))
					.collect(Collectors.toList());
		}

	}

	@Override
	public List<HorseDataResult> isStringOneOf(List<HorseDataResult> horseDetails, String value) {

		return null;
	}

}
