package com.service.provider.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.provider.controller.AllRequestController;
import com.service.provider.model.ConditionReceiver;
import com.service.provider.model.DailyAnalysisResultReport;
import com.service.provider.model.HorseDataResult;
import com.service.provider.repository.DailyAnalysisRepository;

@Service
public class CompleteDataAnalysisImp implements CompleteDataAnalysis {
	
	
	@Autowired
	CompleteDataAnalysis completeDataAnalysis;
	
	@Override
	public void filterAndAnalysisData(List<HorseDataResult> sheetHorseDetails, List<String> filters, String condition) {
	
		String mainFilter  =  filters.get(0);
		List<String> uniqueValues  =  new ArrayList<String>();
		uniqueValues =  getUniqueValues(sheetHorseDetails, mainFilter);
		
		for (String value : uniqueValues) {
			List<HorseDataResult> filteredSheetHorseDetails =  getFilteredData(mainFilter, value , sheetHorseDetails);
			if(filteredSheetHorseDetails.size() > 15) {
				DailyAnalysisResultReport winningPercentage = new DailyAnalysisResultReport();
				winningPercentage =  getListOfHorsePerformance(filteredSheetHorseDetails);
				String newCondition =  condition.trim().equalsIgnoreCase("")?(mainFilter + " = " + value) : (condition + " + " + mainFilter + " = " + value);
				if(Double.parseDouble(winningPercentage.getWinPercentage()) > 50 || 
					winningPercentage.getProfitBfsp() > 30) {
					//System.out.print(newCondition.replaceAll(" ", "") + "  ");
					winningPercentage.setConditionString(newCondition);
					winningPercentage.setConditionName(newCondition.substring(0, newCondition.indexOf("=")).trim());
					try {
						AllRequestController.data_analysis.add(winningPercentage);
					}catch(Exception e) {
						System.out.println(e.getMessage());
					}
				}
				if(filters.size() > 1) {
					completeDataAnalysis.filterAndAnalysisData(filteredSheetHorseDetails, filters.subList(1, filters.size()) , newCondition);
				}
			}
		}
		//System.out.println("");
	}
 
	@Override
	public List<HorseDataResult> getFilteredData(String conditionParameter, String conditionValue, List<HorseDataResult> sheetHorseDetails) {
		ConditionReceiver condition = new ConditionReceiver();
		FilterExecution filterExecution = new FilterExecution();
		
		condition.setConditionParameter(conditionParameter);
		condition.setConditionValue(conditionValue);
		condition.setConditionStatement((conditionValue.indexOf(" and ") != -1) ? "between" :"=");
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

		return sheetHorseDetails;
	}

	@Override
	public List<String> getUniqueValues(List<HorseDataResult> sheetHorseDetails, String condition) {
		FilterExecution filterExecution = new FilterExecution();
		
		if (condition.equalsIgnoreCase("daySinceRun")) {
			FilterConditions filterCondition = new DaySinceRunFilter();
			return filterExecution.findUnique(filterCondition, sheetHorseDetails);
		} else if (condition.equalsIgnoreCase("course")) {
			FilterConditions filterCondition = new CourseFilter();
			return filterExecution.findUnique(filterCondition, sheetHorseDetails);
		} else if (condition.equalsIgnoreCase("raceDistance")) {
			FilterConditions filterCondition = new RaceDistanceFilter();
			return filterExecution.findUnique(filterCondition, sheetHorseDetails);
		} else if (condition.equalsIgnoreCase("officialGoing")) {
			FilterConditions filterCondition = new OfficialGoingFilter();
			return filterExecution.findUnique(filterCondition, sheetHorseDetails);
		} else if (condition.equalsIgnoreCase("classCaptured")) {
			FilterConditions filterCondition = new ClassCapturedFilter();
			return filterExecution.findUnique(filterCondition, sheetHorseDetails);
		} else if (condition.equalsIgnoreCase("raceSurface")) {
			FilterConditions filterCondition = new RaceSurfaceFilter();
			return filterExecution.findUnique(filterCondition, sheetHorseDetails);
		} else if (condition.equalsIgnoreCase("speedPoint")) {
			FilterConditions filterCondition = new SpeedPointFilter();
			return filterExecution.findUnique(filterCondition, sheetHorseDetails);
		} else if (condition.equalsIgnoreCase("distanceFurlongs")) {
			FilterConditions filterCondition = new DistanceFurlongsFilter();
			return filterExecution.findUnique(filterCondition, sheetHorseDetails);
		} else if (condition.equalsIgnoreCase("rank")) {
			FilterConditions filterCondition = new RankFilter();
			return filterExecution.findUnique(filterCondition, sheetHorseDetails);
		} else if (condition.equalsIgnoreCase("finishingPosition")) {
			FilterConditions filterCondition = new FinishingPositionFilter();
			return filterExecution.findUnique(filterCondition, sheetHorseDetails);
		} else if (condition.equalsIgnoreCase("favorite")) {
			FilterConditions filterCondition = new FavoriteHorseFilter();
			return filterExecution.findUnique(filterCondition, sheetHorseDetails);
		} else if (condition.equalsIgnoreCase("bfsp")) {
			FilterConditions filterCondition = new BfspFilter();
			return filterExecution.findUnique(filterCondition, sheetHorseDetails);
		} else if (condition.equalsIgnoreCase("bfspPlace")) {
			FilterConditions filterCondition = new BfspPlaceFilter();
			return filterExecution.findUnique(filterCondition, sheetHorseDetails);
		} else if (condition.equalsIgnoreCase("plscPlace")) {
			FilterConditions filterCondition = new plscPaidFilter();
			return filterExecution.findUnique(filterCondition, sheetHorseDetails);
		} else if (condition.equalsIgnoreCase("bfPlscPaid")) {
			FilterConditions filterCondition = new BfPlscPaidFilter();
			return filterExecution.findUnique(filterCondition, sheetHorseDetails);
		} else if (condition.equalsIgnoreCase("raceType")) {
			FilterConditions filterCondition = new RaceTypeFilter();
			return filterExecution.findUnique(filterCondition, sheetHorseDetails);
		} else if (condition.equalsIgnoreCase("cardNumber")) {
			FilterConditions filterCondition = new CardNumberFilter();
			return filterExecution.findUnique(filterCondition, sheetHorseDetails);
		}
		
		return null;
	}
	
	private DailyAnalysisResultReport getListOfHorsePerformance(List<HorseDataResult> sheetHorseDetails) {
		DailyAnalysisResultReport horseData = new DailyAnalysisResultReport();

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
			horseData.setBfspPlaceWin(0);
			horseData.setBfspPlaceWinPercnt(0);
		

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

}
