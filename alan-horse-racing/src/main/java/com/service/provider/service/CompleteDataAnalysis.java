package com.service.provider.service;

import java.util.List;

import com.service.provider.model.HorseDataResult;

public interface CompleteDataAnalysis {
	void filterAndAnalysisData(List<HorseDataResult> sheetHorseDetails,
			List<String> filters , String condition);
	
	List<HorseDataResult> getFilteredData(String conditionParameter, String conditionValue, List<HorseDataResult> sheetHorseDetails);
	
	List<String> getUniqueValues(List<HorseDataResult> sheetHorseDetails, String condition);
	
}
