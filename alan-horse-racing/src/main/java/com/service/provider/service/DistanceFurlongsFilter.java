package com.service.provider.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.service.provider.model.DailyHorseDataResult;
import com.service.provider.model.HorseDataResult;

public class DistanceFurlongsFilter implements FilterConditions {

	@Override
	public List<HorseDataResult> isEqual(List<HorseDataResult> horseDetails, String value) {
		return horseDetails.stream()
				.filter(d -> d.getDistFurlongs() == Double.parseDouble(value))
				.collect(Collectors.toList());
	}

	@Override
	public List<HorseDataResult> isGreatedThan(List<HorseDataResult> horseDetails, String value) {
		return horseDetails.stream()
				.filter(d -> d.getDistFurlongs() > Double.parseDouble(value))
				.collect(Collectors.toList());
	}

	@Override
	public List<HorseDataResult> isLessThan(List<HorseDataResult> horseDetails, String value) {
		return horseDetails.stream()
				.filter(d -> d.getDistFurlongs() < Double.parseDouble(value))
				.collect(Collectors.toList());
	}

	@Override
	public List<HorseDataResult> isBetween(List<HorseDataResult> horseDetails, String value) {
		return horseDetails.stream()
				.filter(d -> (d.getDistFurlongs() >= Double.parseDouble(value.toLowerCase().split(" and ")[0]) 
						&& d.getDistFurlongs() < Double.parseDouble(value.toLowerCase().split(" and ")[1])))
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
		
		String[] filterValues = {"0 and 5", "5 and 7", "7 and 12", "12 and 100"};
		return new ArrayList<>(Arrays.asList(filterValues));
		
		
		
//		return horseDetails.stream()
//				.map(HorseDataResult :: getDistFurlongs)
//				.map(s -> s.toString())
//				.distinct()
//				.collect(Collectors.toList());
	}

	
	@Override
	public List<DailyHorseDataResult> isDailyEqual(List<DailyHorseDataResult> horseDetails, String value) {
		return horseDetails.stream()
				.filter(d -> d.getDistFurlongs() == Double.parseDouble(value))
				.collect(Collectors.toList());
	}

	@Override
	public List<DailyHorseDataResult> isDailyGreatedThan(List<DailyHorseDataResult> horseDetails, String value) {
		return horseDetails.stream()
				.filter(d -> d.getDistFurlongs() > Double.parseDouble(value))
				.collect(Collectors.toList());
	}

	@Override
	public List<DailyHorseDataResult> isDailyLessThan(List<DailyHorseDataResult> horseDetails, String value) {
		return horseDetails.stream()
				.filter(d -> d.getDistFurlongs() < Double.parseDouble(value))
				.collect(Collectors.toList());
	}

	@Override
	public List<DailyHorseDataResult> isDailyBetween(List<DailyHorseDataResult> horseDetails, String value) {
		return horseDetails.stream()
				.filter(d -> (d.getDistFurlongs() >= Double.parseDouble(value.toLowerCase().split(" and ")[0]) 
						&& d.getDistFurlongs() < Double.parseDouble(value.toLowerCase().split(" and ")[1])))
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
