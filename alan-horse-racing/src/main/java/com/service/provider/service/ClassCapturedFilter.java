package com.service.provider.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.service.provider.model.DailyHorseDataResult;
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
		if(localValue.split("[,]").length > 1) {
			List<String> classCaptured = new ArrayList<String>(Arrays.asList(value.split("[,]")));
			return horseDetails.stream()
					.filter(d -> classCaptured.contains(d.getClassCaptured()))
					.collect(Collectors.toList());
		}else {
			return horseDetails.stream()
					.filter(d -> d.getClassCaptured().equalsIgnoreCase(value))
					.collect(Collectors.toList());
		}
	}

	@Override
	public List<HorseDataResult> isStringOneOf(List<HorseDataResult> horseDetails, String value) {

		return null;
	}

	@Override
	public List<String> getUniqueValues(List<HorseDataResult> horseDetails) {
		
		String[] array =  {"Class 1","Class 1,Class 2","Class 1,Class 2,Class 3","Class 1,Class 2,Class 3,Class 4",
				"Class 1,Class 2,Class 3,Class 4,Class 5","Class 1,Class 2,Class 3,Class 4,Class 5,Class 6",
				"Class 1,Class 2,Class 3,Class 4,Class 5,Class 6,Class 7","Class 1,Class 2,Class 3,Class 4,Class 5,Class 6,Class 7,Irish",
				"Class 1,Class 3","Class 1,Class 3,Class 4","Class 1,Class 3,Class 4,Class 5","Class 1,Class 3,Class 4,Class 5,Class 6","Class 1,Class 3,Class 4,Class 5,Class 6,Class 7","Class 1,Class 3,Class 4,Class 5,Class 6,Class 7,Irish","Class 1,Class 4","Class 1,Class 4,Class 5","Class 1,Class 4,Class 5,Class 6","Class 1,Class 4,Class 5,Class 6,Class 7","Class 1,Class 4,Class 5,Class 6,Class 7,Irish","Class 1,Class 5","Class 1,Class 5,Class 6","Class 1,Class 5,Class 6,Class 7","Class 1,Class 5,Class 6,Class 7,Irish","Class 1,Class 6","Class 1,Class 6,Class 7,Irish","Class 1,Class 7","Class 1,Irish","Class 2","Class 2,Class 3","Class 2,Class 3,Class 4","Class 2,Class 3,Class 4,Class 5","Class 2,Class 3,Class 4,Class 5,Class 6","Class 2,Class 3,Class 4,Class 5,Class 6,Class 7","Class 2,Class 3,Class 4,Class 5,Class 6,Class 7,Irish","Class 2,Class 4","Class 2,Class 4,Class 5","Class 2,Class 4,Class 5,Class 6","Class 2,Class 4,Class 5,Class 6,Class 7","Class 2,Class 4,Class 5,Class 6,Class 7,Irish","Class 2,Class 5","Class 2,Class 5,Class 6","Class 2,Class 5,Class 6,Class 7","Class 2,Class 5,Class 6,Class 7,Irish","Class 2,Class 6","Class 2,Class 6,Class 7","Class 2,Class 6,Class 7,Irish","Class 2,Class 7","Class 2,Class 7,Irish","Class 2,Irish","Class 3","Class 3,Class 4","Class 3,Class 4,Class 5","Class 3,Class 4,Class 5,Class 6","Class 3,Class 4,Class 5,Class 6,Class 7","Class 3,Class 4,Class 5,Class 6,Class 7,Irish","Class 3,Class 5","Class 3,Class 5,Class 6","Class 3,Class 5,Class 6,Class 7","Class 3,Class 5,Class 6,Class 7,Irish","Class 3,Class 6","Class 3,Class 6,Class 7","Class 3,Class 6,Class 7,Irish","Class 3,Class 7","Class 3,Class 7,Irish","Class 3,Irish","Class 4","Class 4,Class 5","Class 4,Class 5,Class 6","Class 4,Class 5,Class 6,Class 7","Class 4,Class 5,Class 6,Class 7,Irish","Class 4,Class 6","Class 4,Class 6,Class 7","Class 4,Class 6,Class 7,Irish","Class 4,Class 7","Class 4,Class 7,Irish","Class 4,Irish","Class 5","Class 5,Class 6","Class 5,Class 6,Class 7","Class 5,Class 6,Class 7,Irish","Class 5,Class 7","Class 5,Class 7,Irish","Class 5,Irish","Class 6","Class 6,Class 7","Class 6,Class 7,Irish","Class 6,Irish","Class 7","Class 7,Irish"};
		return new ArrayList<String>(Arrays.asList(array));
//		return horseDetails.stream()
//				.map(HorseDataResult :: getClassCaptured)
//				.distinct()
//				.collect(Collectors.toList());
	}

	@Override
	public List<DailyHorseDataResult> isDailyEqual(List<DailyHorseDataResult> horseDetails, String value) {
		return null;
	}

	@Override
	public List<DailyHorseDataResult> isDailyGreatedThan(List<DailyHorseDataResult> horseDetails, String value) {
		return null;
	}

	@Override
	public List<DailyHorseDataResult> isDailyLessThan(List<DailyHorseDataResult> horseDetails, String value) {
		return null;
	}

	@Override
	public List<DailyHorseDataResult> isDailyBetween(List<DailyHorseDataResult> horseDetails, String value) {
		return null;
	}

	@Override
	public List<DailyHorseDataResult> isDailyStringEqual(List<DailyHorseDataResult> horseDetails, String value) {
		String localValue = value;
		if(localValue.split("[,]").length > 1) {
			List<String> classCaptured = new ArrayList<String>(Arrays.asList(value.split("[,]")));
			return horseDetails.stream()
					.filter(d -> classCaptured.contains(d.getClassCaptured()))
					.collect(Collectors.toList());
		}else {
			return horseDetails.stream()
					.filter(d -> d.getClassCaptured().equalsIgnoreCase(value))
					.collect(Collectors.toList());
		}
	}

	@Override
	public List<DailyHorseDataResult> isDailyStringOneOf(List<DailyHorseDataResult> horseDetails, String value) {

		return null;
	}

}
