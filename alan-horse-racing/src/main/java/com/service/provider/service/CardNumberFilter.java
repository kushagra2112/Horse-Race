package com.service.provider.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.service.provider.model.DailyHorseDataResult;
import com.service.provider.model.HorseDataResult;

public class CardNumberFilter implements FilterConditions {

	@Override
	public List<HorseDataResult> isEqual(List<HorseDataResult> horseDetails, String value) {
		return horseDetails.stream().filter(d -> d.getCardNumber() == Integer.parseInt(value))
				.collect(Collectors.toList());
	}

	@Override
	public List<HorseDataResult> isGreatedThan(List<HorseDataResult> horseDetails, String value) {
		return horseDetails.stream().filter(d -> d.getCardNumber() > Integer.parseInt(value))
				.collect(Collectors.toList());
	}

	@Override
	public List<HorseDataResult> isLessThan(List<HorseDataResult> horseDetails, String value) {
		return horseDetails.stream().filter(d -> d.getCardNumber() < Integer.parseInt(value))
				.collect(Collectors.toList());
	}

	@Override
	public List<HorseDataResult> isBetween(List<HorseDataResult> horseDetails, String value) {
		return horseDetails.stream()
				.filter(d -> (d.getCardNumber() >= Integer.parseInt(value.toLowerCase().split(" and ")[0].trim())
						&& d.getCardNumber() <= Integer.parseInt(value.toLowerCase().split(" and ")[1].trim())))
				.collect(Collectors.toList());
	}

	@Override
	public List<HorseDataResult> isStringEqual(List<HorseDataResult> horseDetails, String value) {
		String[] array = value.split("[,]");
		List<Integer> arrayList = new ArrayList<Integer>();
		for (String num : array) {
			arrayList.add(Integer.parseInt(num));
		}

		return horseDetails.stream().filter(d -> arrayList.contains(d.getCardNumber())).collect(Collectors.toList());
	}

	@Override
	public List<HorseDataResult> isStringOneOf(List<HorseDataResult> horseDetails, String value) {

		return null;
	}

	@Override
	public List<String> getUniqueValues(List<HorseDataResult> horseDetails) {

//		String[] array = {"1","1,2","1,2,3","1,2,3,4","1,2,3,4,5","1,2,3,4,5,6","1,2,3,4,5,6,7",
//				"1,3","1,3,4","1,3,4,5","1,3,4,5,6","1,3,4,5,6,7","1,4","1,4,5","1,4,5,6","1,4,5,6,7",
//				"1,5","1,5,6","1,5,6,7","1,6","1,6,7","1,7","2","2,3","2,3,4","2,3,4,5","2,3,4,5,6",
//				"2,3,4,5,6,7","2,4","2,4,5","2,4,5,6","2,4,5,6,7","2,5","2,5,6","2,5,6,7","2,6","2,6,7","2,7",
//				"3","3,4","3,4,5","3,4,5,6","3,4,5,6,7","3,5","3,5,6","3,5,6,7","3,6","3,6,7","3,7","4","4,5",
//				"4,5,6","4,5,6,7","4,6","4,6,7","4,6,7","4,7","4,7","5","5,6","5,6,7","5,7","6","6,7","7"};
//		
		String[] array = { "1", "1 and 2", "1 and 3", "1 and 4", "1 and 5", "1 and 6", "1 and 7", "1 and 100", "2",
				"2 and 3", "2 and 4", "2 and 5", "2 and 6", "2 and 7", "2 and 100", "3", "3 and 4", "3 and 5",
				"3 and 6", "3 and 7", "3 and 100", "4", "4 and 5", "4 and 6", "4 and 7", "4 and 100" };

		return new ArrayList<String>(Arrays.asList(array));
//		
//		return horseDetails.stream()
//				.map(HorseDataResult :: getCardNumber)
//				.map(s -> s.toString())
//				.distinct()
//				.collect(Collectors.toList());
	}

	@Override
	public List<DailyHorseDataResult> isDailyEqual(List<DailyHorseDataResult> horseDetails, String value) {
		return horseDetails.stream().filter(d -> d.getCardNumber() == Integer.parseInt(value))
				.collect(Collectors.toList());
	}

	@Override
	public List<DailyHorseDataResult> isDailyGreatedThan(List<DailyHorseDataResult> horseDetails, String value) {
		return horseDetails.stream().filter(d -> d.getCardNumber() > Integer.parseInt(value))
				.collect(Collectors.toList());
	}

	@Override
	public List<DailyHorseDataResult> isDailyLessThan(List<DailyHorseDataResult> horseDetails, String value) {
		return horseDetails.stream().filter(d -> d.getCardNumber() < Integer.parseInt(value))
				.collect(Collectors.toList());
	}

	@Override
	public List<DailyHorseDataResult> isDailyBetween(List<DailyHorseDataResult> horseDetails, String value) {
		return horseDetails.stream()
				.filter(d -> (d.getCardNumber() >= Integer.parseInt(value.toLowerCase().split(" and ")[0])
						&& d.getCardNumber() < Integer.parseInt(value.toLowerCase().split(" and ")[1])))
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