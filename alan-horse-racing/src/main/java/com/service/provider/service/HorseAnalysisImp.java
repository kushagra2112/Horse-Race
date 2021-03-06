package com.service.provider.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.provider.model.AnalysisResponse;
import com.service.provider.model.ConditionReceiver;
import com.service.provider.model.HorseDataResult;
import com.service.provider.model.ListOfWinners;
import com.service.provider.model.PositionAnalysis;
import com.service.provider.model.StrikeInfo;
import com.service.provider.model.WinningPercentage;
import com.service.provider.repository.HorseRaceAnalysisRepository;
import com.service.provider.repository.SpeedFileRepository;

@Service
public class HorseAnalysisImp implements HorseAnalysis {

	@Autowired
	SpeedFileRepository speedFileRepository;

	@Autowired
	HorseRaceAnalysisRepository horseRaceAnalysisRepository;

	public static String raceCondition = "";
	/*
	 * @Override public Map<String, HashMap<String , List<ListOfTopHorses>>>
	 * getListOfTopHorses() {
	 * 
	 * List<String> sheetNames = speedFileRepository.findDistinctSheetName();;
	 * 
	 * Map<String, HashMap<String , List<ListOfTopHorses>>> response = new
	 * LinkedHashMap<String, HashMap<String , List<ListOfTopHorses>>>();
	 * HashMap<String , List<ListOfTopHorses>> subResponse = new HashMap<String ,
	 * List<ListOfTopHorses>>();
	 * 
	 * //String sheet = sheetNames.get(0); Collections.sort(sheetNames); for(String
	 * sheet : sheetNames) { if(response.get(sheet) != null) { subResponse =
	 * response.get(sheet); }else { subResponse = new HashMap<String ,
	 * List<ListOfTopHorses>>(); }
	 * 
	 * subResponse.put("Limit 5 in speed file" ,
	 * getTopHorsesObject(speedFileRepository.findAnalysisForHorse(sheet, 5)));
	 * response.put(sheet, subResponse);
	 * 
	 * } return response;
	 * 
	 * return null;
	 * 
	 * }
	 */

	@Override
	public AnalysisResponse analyzeHorseWithCondition(ConditionReceiver[] conditions) {

		HashMap<String, List<WinningPercentage>> subResponse = new HashMap<String, List<WinningPercentage>>();
		AnalysisResponse winnerInfo = new AnalysisResponse();

		StringBuilder conditionString = new StringBuilder();
		for (ConditionReceiver condition : conditions) {
			conditionString.append(condition.responseInString());
		}

		winnerInfo.setCondition(conditionString.toString());

		List<HorseDataResult> sheetHorseDetails = horseRaceAnalysisRepository.findAnalysisForHorse();

		sheetHorseDetails = filterValues(sheetHorseDetails, conditions);

		winnerInfo.setWinRaceStats(getListOfHorsePerformance(sheetHorseDetails, conditions));

		winnerInfo.setWinners(getWinnerInformation(sheetHorseDetails));

		winnerInfo.setDaysReport(getDaysReport(sheetHorseDetails));

		winnerInfo.setStikes(getStrikeReport(sheetHorseDetails));
		
		winnerInfo.setErrorResponse(null);

		return winnerInfo;
	}

	private List<PositionAnalysis> getDaysReport(List<HorseDataResult> sheetHorseDetails) {
		List<PositionAnalysis> daysReport = new ArrayList<PositionAnalysis>();

		PositionAnalysis obj = new PositionAnalysis();
		List<HorseDataResult> horseCompleteDetails = sheetHorseDetails;
		
		obj = new PositionAnalysis();
		{
			obj.setCondition("Last 50 Runners");
			
			sheetHorseDetails = sheetHorseDetails.stream()
					.sorted((p1, p2) -> p1.getRaceDateTime().compareTo(p2.getRaceDateTime()))
					.collect(Collectors.toList());
			if(sheetHorseDetails.size() > 50) {
				sheetHorseDetails = sheetHorseDetails.subList(sheetHorseDetails.size()-50, sheetHorseDetails.size());
			}

			long winCount = sheetHorseDetails.stream().filter(p -> p.getPlacingNumerical() == 1).count();

			obj.setQualifier("" + winCount + " from " + sheetHorseDetails.size() + " ");
			obj.setPositionMarker(sheetHorseDetails.stream()
					.map(HorseDataResult::getPlacingNumerical)
					.map(s -> s > 9 ? 0 : s)
					.collect(Collectors.toList()).toString());
			obj.setPositionMarker(
					obj.getPositionMarker().substring(1, obj.getPositionMarker().length() - 1)
					.replaceAll(" ", "").replaceAll(",",""));
			daysReport.add(obj);
		}
		
		
		obj = new PositionAnalysis();
		{	
			sheetHorseDetails =  horseCompleteDetails;
			Date todayDate = new Date();
			
			obj.setCondition("Last 28 Days");
			sheetHorseDetails = sheetHorseDetails.stream()
					.filter(d -> (int)TimeUnit.DAYS.convert(todayDate.getTime() - d.getRaceDate().getTime(), TimeUnit.MILLISECONDS) < 29)
					.sorted((p1, p2) -> p1.getRaceDateTime().compareTo(p2.getRaceDateTime()))
					.collect(Collectors.toList());
			
			long winCount = sheetHorseDetails.stream().filter(p -> p.getPlacingNumerical() == 1).count();

			obj.setQualifier("" + winCount + " from " + sheetHorseDetails.size() + " ");
			
			String positionMarker =  sheetHorseDetails.stream()
					.map(HorseDataResult::getPlacingNumerical)
					.map(s -> s > 9 ? 0 : s)
					.collect(Collectors.toList()).toString();
			
			positionMarker = positionMarker
					.substring(1, positionMarker.length() - 1)
					.replaceAll(" ", "").replaceAll(",","");
			int space = 80;
			while(space < positionMarker.length()) {
				positionMarker = positionMarker.substring(0, space) + " " + positionMarker.substring(space);
				space += 80;
			}
			
			obj.setPositionMarker(positionMarker);
			daysReport.add(obj);
		}
		
		obj = new PositionAnalysis();
		{	
			sheetHorseDetails =  horseCompleteDetails;
			obj.setCondition("Complete placing order");
			obj.setQualifier("Total :: " +  sheetHorseDetails.size());
			String positionMarker = sheetHorseDetails.stream()
					.sorted((p1, p2) -> p1.getRaceDateTime().compareTo(p2.getRaceDateTime()))
					.map(HorseDataResult::getPlacingNumerical)
					.map(s -> s > 9 ? 0 : s)
					.collect(Collectors.toList()).toString();
			positionMarker = positionMarker.substring(1, positionMarker.length() - 1)
					.replaceAll(" ", "").replaceAll(",","");
			
			obj.setPositionMarker(positionMarker);
			daysReport.add(obj);
		}
		return daysReport;
	}

	private List<StrikeInfo> getStrikeReport(List<HorseDataResult> sheetHorseDetails) {
		List<StrikeInfo> strikeReport = new ArrayList<StrikeInfo>();
		StrikeInfo strikeData = new StrikeInfo();
		
		
		String[] strikes = {"11", "111" , "1111", "11111", "111111", "1111111", "11111111", "111111111"};
		List<Integer> strikesArray = new ArrayList<Integer>();
		
		String positionMarker = sheetHorseDetails.stream()
				.sorted((p1, p2) -> p1.getRaceDateTime().compareTo(p2.getRaceDateTime()))
				.map(HorseDataResult::getPlacingNumerical)
				.map(s -> s > 9 ? 0 : s)
				.collect(Collectors.toList()).toString();
		positionMarker = positionMarker.substring(1, positionMarker.length() - 1)
				.replaceAll(" ", "").replaceAll(",","");
	
		int strikeCount = 0;
		int index = 2;
		
		for(int s = strikes.length-1; s>=0;s--) {
			strikesArray.add((positionMarker.length() - 
					positionMarker.replaceAll(strikes[s], "").length())/strikes[s].length());
			positionMarker = positionMarker.replaceAll(strikes[s], "");
		}
		int intermediateCount = 0;
		int smallIndex = 0 ;
		for(int s = 0 ; s <strikes.length; s++) {
			
			strikeCount = 0 ;
			smallIndex =strikes.length -1;
			
			for(int strik : strikesArray) {
				intermediateCount = (strikes[smallIndex].length() - strikes[s].length() + 1) * strik;
				strikeCount +=  intermediateCount < 0 ? 0 : intermediateCount;
				smallIndex--;
			}
			
			strikeData = new StrikeInfo();
			strikeData.setStrikeString(index + "+ Streaks");
			strikeData.setStrikeCount(strikeCount);
			
			strikeReport.add(strikeData);
			index += 1;
			
		}

		return strikeReport;
	}

	private WinningPercentage getListOfHorsePerformance(List<HorseDataResult> sheetHorseDetails,
			ConditionReceiver[] conditions) {
		WinningPercentage horseData = new WinningPercentage();

		if (sheetHorseDetails.size() != 0) {

			horseData.setQualifiedRace(sheetHorseDetails.stream().count());
			horseData.setTotalWinCount(sheetHorseDetails.stream().filter(d -> d.getPlacingNumerical() == 1)
					.count());
			horseData.setWinPercentage(
					((float) horseData.getTotalWinCount() / (float) horseData.getQualifiedRace()) * 100 + "");

			horseData.setBfspTotal(sheetHorseDetails.stream().filter(d -> d.getPlacingNumerical() == 1)
					.map(HorseDataResult::getBfsp).mapToDouble(Double::doubleValue).sum());
			
			horseData.setProfitBfsp(horseData.getBfspTotal() - (double) horseData.getQualifiedRace());

			horseData.setBfspPlaceTotal(
					sheetHorseDetails.stream().filter(d -> d.getPlacingNumerical() <= d.getBfPlcsPaid())
							.map(HorseDataResult::getBfspPlace)
							.mapToDouble(Double::doubleValue).sum());

			horseData.setBfspPlaceProfit(horseData.getBfspPlaceTotal() - (double) horseData.getQualifiedRace());

			if (Arrays.asList(conditions).stream()
					.filter(e -> e.getConditionParameter().equalsIgnoreCase("bfspPlace"))
					.count() > 0) {
				horseData.setBfspPlaceWin(
						sheetHorseDetails.stream().filter(d -> d.getPlacingNumerical() <= d.getBfPlcsPaid()).count());
				horseData.setBfspPlaceWinPercnt(
						((float) horseData.getBfspPlaceWin() / (float) horseData.getQualifiedRace()) * 100);
			} else {
				horseData.setBfspPlaceWin(0);
				horseData.setBfspPlaceWinPercnt(0);
			}

		} else {
			horseData.setQualifiedRace(0);
			horseData.setTotalWinCount(0);
			horseData.setWinPercentage("0");
			horseData.setBfspTotal(0);
			horseData.setProfitBfsp(0);
			horseData.setBfspPlaceProfit(0);
			horseData.setBfspPlaceTotal(0);
			horseData.setBfspPlaceWin(0);
			horseData.setBfspPlaceWinPercnt(0);
		}
		return horseData;
	}

	private List<ListOfWinners> getWinnerInformation(List<HorseDataResult> sheetHorseDetails) {

		List<ListOfWinners> winnerInfo = new ArrayList<ListOfWinners>();
		if (sheetHorseDetails.size() != 0) {
			sheetHorseDetails = sheetHorseDetails.stream().filter(d -> d.getPlacingNumerical() == 1)
					.collect(Collectors.toList());
			ListOfWinners winner = new ListOfWinners();

			for (HorseDataResult horse : sheetHorseDetails) {
				winner = new ListOfWinners();
				winner.setHorseName(horse.getHorse());
				winner.setRaceDate(new SimpleDateFormat("yyyy-MM-dd").format(horse.getRaceDate()));
				winner.setTrack(horse.getCourse());
				winner.setBfsp(horse.getBfsp());
				winner.setDistance(horse.getDistFurlongs());
				winnerInfo.add(winner);
			}
		}
		return winnerInfo;
	}


	private List<HorseDataResult> filterValues(List<HorseDataResult> sheetHorseDetails,
			ConditionReceiver[] conditions) {

		FilterExecution filterExecution = new FilterExecution();

		for (ConditionReceiver condition : conditions) {

			if (condition.getConditionParameter().equalsIgnoreCase("daySinceRun")) {
				FilterConditions filterCondition = new DaySinceRunFilter();
				sheetHorseDetails = filterExecution.executuefilter(filterCondition, sheetHorseDetails, condition);
			} else if (condition.getConditionParameter().equalsIgnoreCase("course")) {
				FilterConditions filterCondition = new CourseFilter();
				sheetHorseDetails = filterExecution.executuefilter(filterCondition, sheetHorseDetails, condition);
			} else if (condition.getConditionParameter().equalsIgnoreCase("raceDistance")) {
				FilterConditions filterCondition = new RaceDistanceFilter();
				sheetHorseDetails = filterExecution.executuefilter(filterCondition, sheetHorseDetails, condition);
			} else if (condition.getConditionParameter().equalsIgnoreCase("officialGoing")) {
				FilterConditions filterCondition = new OfficialGoingFilter();
				sheetHorseDetails = filterExecution.executuefilter(filterCondition, sheetHorseDetails, condition);
			} else if (condition.getConditionParameter().equalsIgnoreCase("classCaptured")) {
				FilterConditions filterCondition = new ClassCapturedFilter();
				sheetHorseDetails = filterExecution.executuefilter(filterCondition, sheetHorseDetails, condition);
			} else if (condition.getConditionParameter().equalsIgnoreCase("raceSurface")) {
				FilterConditions filterCondition = new RaceSurfaceFilter();
				sheetHorseDetails = filterExecution.executuefilter(filterCondition, sheetHorseDetails, condition);
			} else if (condition.getConditionParameter().equalsIgnoreCase("speedPoint")) {
				FilterConditions filterCondition = new SpeedPointFilter();
				sheetHorseDetails = filterExecution.executuefilter(filterCondition, sheetHorseDetails, condition);
			} else if (condition.getConditionParameter().equalsIgnoreCase("distanceFurlongs")) {
				FilterConditions filterCondition = new DistanceFurlongsFilter();
				sheetHorseDetails = filterExecution.executuefilter(filterCondition, sheetHorseDetails, condition);
			} else if (condition.getConditionParameter().equalsIgnoreCase("rank")) {
				FilterConditions filterCondition = new RankFilter();
				sheetHorseDetails = filterExecution.executuefilter(filterCondition, sheetHorseDetails, condition);
			} else if (condition.getConditionParameter().equalsIgnoreCase("finishingPosition")) {
				FilterConditions filterCondition = new FinishingPositionFilter();
				sheetHorseDetails = filterExecution.executuefilter(filterCondition, sheetHorseDetails, condition);
			} else if (condition.getConditionParameter().equalsIgnoreCase("favorite")) {
				FilterConditions filterCondition = new FavoriteHorseFilter();
				sheetHorseDetails = filterExecution.executuefilter(filterCondition, sheetHorseDetails, condition);
			} else if (condition.getConditionParameter().equalsIgnoreCase("bfsp")) {
				FilterConditions filterCondition = new BfspFilter();
				sheetHorseDetails = filterExecution.executuefilter(filterCondition, sheetHorseDetails, condition);
			} else if (condition.getConditionParameter().equalsIgnoreCase("bfspPlace")) {
				FilterConditions filterCondition = new BfspPlaceFilter();
				sheetHorseDetails = filterExecution.executuefilter(filterCondition, sheetHorseDetails, condition);
			} else if (condition.getConditionParameter().equalsIgnoreCase("plscPlace")) {
				FilterConditions filterCondition = new plscPaidFilter();
				sheetHorseDetails = filterExecution.executuefilter(filterCondition, sheetHorseDetails, condition);
			} else if (condition.getConditionParameter().equalsIgnoreCase("bfPlscPaid")) {
				FilterConditions filterCondition = new BfPlscPaidFilter();
				sheetHorseDetails = filterExecution.executuefilter(filterCondition, sheetHorseDetails, condition);
			} else if (condition.getConditionParameter().equalsIgnoreCase("raceType")) {
				FilterConditions filterCondition = new RaceTypeFilter();
				sheetHorseDetails = filterExecution.executuefilter(filterCondition, sheetHorseDetails, condition);
			} else if (condition.getConditionParameter().equalsIgnoreCase("cardNumber")) {
				FilterConditions filterCondition = new CardNumberFilter();
				sheetHorseDetails = filterExecution.executuefilter(filterCondition, sheetHorseDetails, condition);
			}

		}

		return sheetHorseDetails;
	}

}
