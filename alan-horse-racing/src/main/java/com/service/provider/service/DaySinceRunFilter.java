package com.service.provider.service;


import java.util.List;
import java.util.stream.Collectors;

import com.service.provider.model.DailyHorseDataResult;
import com.service.provider.model.HorseDataResult;

public class DaySinceRunFilter implements FilterConditions {

	@Override
	public List<HorseDataResult> isEqual(List<HorseDataResult> horseDetails, String value) {
		return horseDetails.stream()
				.filter(d -> d.getDaySinceRun() == Long.parseLong(value))
				.collect(Collectors.toList());
	}

	@Override
	public List<HorseDataResult> isGreatedThan(List<HorseDataResult> horseDetails, String value) {
		return horseDetails.stream().filter(d -> d.getDaySinceRun() > Long.parseLong(value))
				.collect(Collectors.toList());
	}

	@Override
	public List<HorseDataResult> isLessThan(List<HorseDataResult> horseDetails, String value) {
		return horseDetails.stream().filter(d -> d.getDaySinceRun() < Long.parseLong(value))
				.collect(Collectors.toList());
	}

	@Override
	public List<HorseDataResult> isBetween(List<HorseDataResult> horseDetails, String value) {
		return horseDetails.stream()
				.filter(d -> (d.getDaySinceRun() >= Long.parseLong(value.toLowerCase().split(" and ")[0])
						&& d.getDaySinceRun() < Long.parseLong(value.toLowerCase().split(" and ")[1])))
				.collect(Collectors.toList());
	}

	@Override
	public List<HorseDataResult> isStringEqual(List<HorseDataResult> horseDetails, String value) {

		return null;
	}

	@Override
	public List<HorseDataResult> isStringOneOf(List<HorseDataResult> horseDetails, String value) {

		return null;
	}

	@Override
	public List<String> getUniqueValues(List<HorseDataResult> horseDetails) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<DailyHorseDataResult> isDailyEqual(List<DailyHorseDataResult> horseDetails, String value) {
		return horseDetails.stream()
				.filter(d -> d.getDaySinceRun() == Long.parseLong(value))
				.collect(Collectors.toList());
	}

	@Override
	public List<DailyHorseDataResult> isDailyGreatedThan(List<DailyHorseDataResult> horseDetails, String value) {
		return horseDetails.stream().filter(d -> d.getDaySinceRun() > Long.parseLong(value))
				.collect(Collectors.toList());
	}

	@Override
	public List<DailyHorseDataResult> isDailyLessThan(List<DailyHorseDataResult> horseDetails, String value) {
		return horseDetails.stream().filter(d -> d.getDaySinceRun() < Long.parseLong(value))
				.collect(Collectors.toList());
	}

	@Override
	public List<DailyHorseDataResult> isDailyBetween(List<DailyHorseDataResult> horseDetails, String value) {
		return horseDetails.stream()
				.filter(d -> (d.getDaySinceRun() >= Long.parseLong(value.toLowerCase().split(" and ")[0])
						&& d.getDaySinceRun() < Long.parseLong(value.toLowerCase().split(" and ")[1])))
				.collect(Collectors.toList());
	}

	@Override
	public List<DailyHorseDataResult> isDailyStringEqual(List<DailyHorseDataResult> horseDetails, String value) {

		return null;
	}

	@Override
	public List<DailyHorseDataResult> isDailyStringOneOf(List<DailyHorseDataResult> horseDetails, String value) {

		return null;
	}


}
