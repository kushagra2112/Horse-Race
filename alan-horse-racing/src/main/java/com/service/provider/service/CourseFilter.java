package com.service.provider.service;

import java.util.List;
import java.util.stream.Collectors;

import com.service.provider.model.HorseDataResult;

public class CourseFilter implements FilterConditions {

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
		if(localValue.equalsIgnoreCase("todays_race_track")) {
			return horseDetails.stream()
					.filter(d -> d.getCourse().equalsIgnoreCase(d.getTodaysRaceTrack()))
					.collect(Collectors.toList());
		}else {
			return horseDetails.stream()
					.filter(d -> d.getCourse().equalsIgnoreCase(localValue))
					.collect(Collectors.toList());
		}

	}

	@Override
	public List<HorseDataResult> isStringOneOf(List<HorseDataResult> horseDetails, String value) {

		return null;
	}

}
