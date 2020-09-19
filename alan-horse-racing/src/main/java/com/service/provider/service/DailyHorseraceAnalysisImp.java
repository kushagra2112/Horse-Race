package com.service.provider.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.provider.model.AnalysisResponse;
import com.service.provider.model.ConditionReceiver;
import com.service.provider.model.ConditionSaver;
import com.service.provider.model.DailyHorseDataResult;
import com.service.provider.model.HorseDataResult;
import com.service.provider.model.ListOfWinners;
import com.service.provider.model.PositionAnalysis;
import com.service.provider.model.StrikeInfo;
import com.service.provider.model.WinningPercentage;
import com.service.provider.repository.ConditionSaverRepository;
import com.service.provider.repository.DailyHorseRaceAnalysisRepository;
import com.service.provider.repository.HorseRaceAnalysisRepository;
import com.service.provider.repository.SpeedFileRepository;




@Service
public class DailyHorseraceAnalysisImp implements DailyHorseRaceAnalysis {
	private static final Logger logger = LogManager.getLogger();
	
	ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	SpeedFileRepository speedFileRepository;

	@Autowired
	HorseRaceAnalysisRepository horseRaceAnalysisRepository;
	
	@Autowired
	ConditionSaverRepository conditionSaverRepository;
	
	@Autowired
	DailyHorseRaceAnalysisRepository dailyHorseRaceAnalysisRepository;
	
	public static String raceCondition = "";
	
	@Override
	public List<AnalysisResponse> analyzeHorseWithCondition() {
		
		List<AnalysisResponse> completeWinnerInfo = new ArrayList<AnalysisResponse>();
		
		List<ConditionSaver> listOfConditions = new ArrayList<ConditionSaver>();
		
		listOfConditions  =  conditionSaverRepository.findAll();
		
		for(ConditionSaver consitionStringValue : listOfConditions) {
			
			ConditionReceiver[] conditions = null;
			try {
				conditions = mapper.readValue(consitionStringValue.getConditionValue(), ConditionReceiver[].class);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			
			AnalysisResponse winnerInfo = new AnalysisResponse();

			StringBuilder conditionString = new StringBuilder();
			
			for (ConditionReceiver condition : conditions) {
				conditionString.append(condition.responseInString());
			}

			winnerInfo.setCondition(conditionString.toString());
			//logger.info("Started working on condition " + conditionString.toString());
			List<HorseDataResult> sheetHorseDetails = horseRaceAnalysisRepository.findAnalysisForHorse();

			//logger.info("saved data :: " +  sheetHorseDetails.size());
			
			sheetHorseDetails = filterValues(sheetHorseDetails, conditions);
			
			//logger.info("saved data filtered data " +  sheetHorseDetails.size());
			
			winnerInfo.setWinRaceStats(getListOfHorsePerformance(sheetHorseDetails, conditions));

			//logger.info("Completed win race states");
			
			winnerInfo.setDaysReport(getDaysReport(sheetHorseDetails));

			//logger.info("Completed day report");
			winnerInfo.setStikes(getStrikeReport(sheetHorseDetails));
			
			winnerInfo.setErrorResponse(null);
			
			//logger.info("Got the result for analysis " +  sheetHorseDetails.size());
			
			List<DailyHorseDataResult> dailyHorseData = dailyHorseRaceAnalysisRepository.findAnalysisForHorse();
			
			//logger.info("Received the daily data :: " +   dailyHorseData.size());
			dailyHorseData = filterDailyValues(dailyHorseData, conditions);
			
			//logger.info("Received the daily data filterd value :: " +   dailyHorseData.size());
			
			winnerInfo.setWinners(getWinnerInformation(dailyHorseData));
			
			//logger.info("condition result saved successfully");
			
			completeWinnerInfo.add(winnerInfo);
			
		}
		

		return completeWinnerInfo;
	}

	private List<ListOfWinners> getWinnerInformation(List<DailyHorseDataResult> sheetHorseDetails) {

		List<ListOfWinners> winnerInfo = new ArrayList<ListOfWinners>();
		if (sheetHorseDetails != null && sheetHorseDetails.size() != 0) {
			ListOfWinners winner = new ListOfWinners();

			for (DailyHorseDataResult horse : sheetHorseDetails) {
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

	private List<DailyHorseDataResult> filterDailyValues(List<DailyHorseDataResult> sheetHorseDetails,
			ConditionReceiver[] conditions) {
		FilterExecution filterExecution = new FilterExecution();
		for (ConditionReceiver condition : conditions) {
			//logger.info("applying filter for " + condition.toString());
			//logger.info("current data size " + sheetHorseDetails.size());
			if (condition.getConditionParameter().equalsIgnoreCase("daySinceRun")) {
				FilterConditions filterCondition = new DaySinceRunFilter();
				sheetHorseDetails = filterExecution.executueDailyfilter(filterCondition, sheetHorseDetails, condition);
			} else if (condition.getConditionParameter().equalsIgnoreCase("course")) {
				FilterConditions filterCondition = new CourseFilter();
				sheetHorseDetails = filterExecution.executueDailyfilter(filterCondition, sheetHorseDetails, condition);
			} else if (condition.getConditionParameter().equalsIgnoreCase("raceDistance")) {
				FilterConditions filterCondition = new RaceDistanceFilter();
				sheetHorseDetails = filterExecution.executueDailyfilter(filterCondition, sheetHorseDetails, condition);
			} else if (condition.getConditionParameter().equalsIgnoreCase("officialGoing")) {
				FilterConditions filterCondition = new OfficialGoingFilter();
				sheetHorseDetails = filterExecution.executueDailyfilter(filterCondition, sheetHorseDetails, condition);
			} else if (condition.getConditionParameter().equalsIgnoreCase("classCaptured")) {
				FilterConditions filterCondition = new ClassCapturedFilter();
				sheetHorseDetails = filterExecution.executueDailyfilter(filterCondition, sheetHorseDetails, condition);
			} else if (condition.getConditionParameter().equalsIgnoreCase("raceSurface")) {
				FilterConditions filterCondition = new RaceSurfaceFilter();
				sheetHorseDetails = filterExecution.executueDailyfilter(filterCondition, sheetHorseDetails, condition);
			} else if (condition.getConditionParameter().equalsIgnoreCase("speedPoint")) {
				FilterConditions filterCondition = new SpeedPointFilter();
				sheetHorseDetails = filterExecution.executueDailyfilter(filterCondition, sheetHorseDetails, condition);
			} else if (condition.getConditionParameter().equalsIgnoreCase("distanceFurlongs")) {
				FilterConditions filterCondition = new DistanceFurlongsFilter();
				sheetHorseDetails = filterExecution.executueDailyfilter(filterCondition, sheetHorseDetails, condition);
			} else if (condition.getConditionParameter().equalsIgnoreCase("rank")) {
				FilterConditions filterCondition = new RankFilter();
				sheetHorseDetails = filterExecution.executueDailyfilter(filterCondition, sheetHorseDetails, condition);
			} else if (condition.getConditionParameter().equalsIgnoreCase("finishingPosition")) {
				FilterConditions filterCondition = new FinishingPositionFilter();
				sheetHorseDetails = filterExecution.executueDailyfilter(filterCondition, sheetHorseDetails, condition);
			} else if (condition.getConditionParameter().equalsIgnoreCase("favorite")) {
				FilterConditions filterCondition = new FavoriteHorseFilter();
				sheetHorseDetails = filterExecution.executueDailyfilter(filterCondition, sheetHorseDetails, condition);
			}else if (condition.getConditionParameter().equalsIgnoreCase("raceType")) {
				FilterConditions filterCondition = new RaceTypeFilter();
				sheetHorseDetails = filterExecution.executueDailyfilter(filterCondition, sheetHorseDetails, condition);
			} else if (condition.getConditionParameter().equalsIgnoreCase("bfsp")) {
				FilterConditions filterCondition = new BfspFilter();
				sheetHorseDetails = filterExecution.executueDailyfilter(filterCondition, sheetHorseDetails, condition);
			} else if (condition.getConditionParameter().equalsIgnoreCase("cardNumber")) {
				FilterConditions filterCondition = new CardNumberFilter();
				sheetHorseDetails = filterExecution.executueDailyfilter(filterCondition, sheetHorseDetails, condition);
			}
			//logger.info("data after filter size " + sheetHorseDetails.size());
		}

		return sheetHorseDetails;
	}

}
