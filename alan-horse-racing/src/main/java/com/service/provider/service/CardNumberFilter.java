package com.service.provider.service;

import java.util.List;
import java.util.stream.Collectors;

import com.service.provider.model.HorseDataResult;

public class CardNumberFilter  implements FilterConditions {

	@Override
	public List<HorseDataResult> isEqual(List<HorseDataResult> horseDetails, String value) {
		return horseDetails.stream()
				.filter(d -> d.getCardNumber() == Integer.parseInt(value))
				.collect(Collectors.toList());
	}

	@Override
	public List<HorseDataResult> isGreatedThan(List<HorseDataResult> horseDetails, String value) {
		return horseDetails.stream()
				.filter(d -> d.getCardNumber() > Integer.parseInt(value))
				.collect(Collectors.toList());
	}
	

	@Override
	public List<HorseDataResult> isLessThan(List<HorseDataResult> horseDetails, String value) {
		return horseDetails.stream()
				.filter(d -> d.getCardNumber() < Integer.parseInt(value))
				.collect(Collectors.toList());
	}

	@Override
	public List<HorseDataResult> isBetween(List<HorseDataResult> horseDetails, String value) {
		return horseDetails.stream()
				.filter(d -> (d.getCardNumber() >= Integer.parseInt(value.toLowerCase().split(" and ")[0]) 
						&& d.getCardNumber() < Integer.parseInt(value.toLowerCase().split(" and ")[1])))
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

}