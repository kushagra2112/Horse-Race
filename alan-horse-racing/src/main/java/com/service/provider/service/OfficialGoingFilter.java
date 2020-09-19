package com.service.provider.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.service.provider.model.DailyHorseDataResult;
import com.service.provider.model.HorseDataResult;

public class OfficialGoingFilter implements FilterConditions {

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
			List<String> officialGoingList = new ArrayList<String>(Arrays.asList(value.split("[,]")));
			return horseDetails.stream()
					.filter(d -> officialGoingList.contains(d.getOfficialGoing()))
					.collect(Collectors.toList());
		}else {
			return horseDetails.stream()
					.filter(d -> d.getOfficialGoing().equalsIgnoreCase(localValue))
					.collect(Collectors.toList());
		}

	}

	@Override
	public List<HorseDataResult> isStringOneOf(List<HorseDataResult> horseDetails, String value) {

		return null;
	}

	@Override
	public List<String> getUniqueValues(List<HorseDataResult> horseDetails) {
		
		List<String> unique =  horseDetails.stream()
				.map(HorseDataResult :: getOfficialGoing)
				.map(s -> (s.equalsIgnoreCase("Standard") || s.equalsIgnoreCase("Standard To Slow"))? "Standard,Standard To Slow" : s)
				.distinct()
				.collect(Collectors.toList());
		return unique;
		
	}

	@Override
	public List<DailyHorseDataResult> isDailyEqual(List<DailyHorseDataResult> horseDetails, String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DailyHorseDataResult> isDailyGreatedThan(List<DailyHorseDataResult> horseDetails, String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DailyHorseDataResult> isDailyLessThan(List<DailyHorseDataResult> horseDetails, String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DailyHorseDataResult> isDailyBetween(List<DailyHorseDataResult> horseDetails, String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DailyHorseDataResult> isDailyStringEqual(List<DailyHorseDataResult> horseDetails, String value) {
		
		String localValue = value;
		if(localValue.split(",").length > 1) {
			List<String> officialGoingList = new ArrayList<String>(Arrays.asList(value.split("[,]")));
			return horseDetails.stream()
					.filter(d -> officialGoingList.contains(d.getOfficialGoing()))
					.collect(Collectors.toList());
		}else {
			return horseDetails.stream()
					.filter(d -> d.getOfficialGoing().equalsIgnoreCase(localValue))
					.collect(Collectors.toList());
		}

	}

	@Override
	public List<DailyHorseDataResult> isDailyStringOneOf(List<DailyHorseDataResult> horseDetails, String value) {
		// TODO Auto-generated method stub
		return null;
	}

}
