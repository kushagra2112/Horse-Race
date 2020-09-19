package com.service.provider.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.service.provider.model.DailyHorseDataResult;
import com.service.provider.model.HorseDataResult;

public class FavoriteHorseFilter implements FilterConditions {

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
		if(value.equalsIgnoreCase("yes")) {
			return horseDetails.stream()
					.filter(d -> !d.getFav().equalsIgnoreCase(""))
					.collect(Collectors.toList());
		}else {
			return horseDetails.stream()
					.filter(d -> d.getFav().equalsIgnoreCase(""))
					.collect(Collectors.toList());
		}
	}

	@Override
	public List<HorseDataResult> isStringOneOf(List<HorseDataResult> horseDetails, String value) {

		return null;
	}

	@Override
	public List<String> getUniqueValues(List<HorseDataResult> horseDetails) {
		List<String> unique = new ArrayList<String>();
		unique.add("yes");
		unique.add("No");
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
		if(value.equalsIgnoreCase("yes")) {
			return horseDetails.stream()
					.filter(d -> !d.getFav().equalsIgnoreCase(""))
					.collect(Collectors.toList());
		}else {
			return horseDetails.stream()
					.filter(d -> d.getFav().equalsIgnoreCase(""))
					.collect(Collectors.toList());
		}
	}

	@Override
	public List<DailyHorseDataResult> isDailyStringOneOf(List<DailyHorseDataResult> horseDetails, String value) {
		// TODO Auto-generated method stub
		return null;
	}

}
