package com.service.provider.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.service.provider.model.HorseDataResult;

public class RaceTypeFilter implements FilterConditions {

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
			List<String> officialGoingList = new ArrayList<String>(Arrays.asList(localValue.split("[,]")));
			return horseDetails.stream()
					.filter(d -> officialGoingList.contains(d.getRaceType()))
					.collect(Collectors.toList());
		}else {
			return horseDetails.stream()
					.filter(d -> d.getRaceType().equalsIgnoreCase(localValue))
					.collect(Collectors.toList());
		}
	}

	@Override
	public List<HorseDataResult> isStringOneOf(List<HorseDataResult> horseDetails, String value) {

		return null;
	}
}
