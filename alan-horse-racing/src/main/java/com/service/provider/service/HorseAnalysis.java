package com.service.provider.service;

import com.service.provider.model.AnalysisResponse;
import com.service.provider.model.ConditionReceiver;

public interface HorseAnalysis {
	//Map<String, HashMap<String , List<ListOfTopHorses>>> getListOfTopHorses();
	AnalysisResponse analyzeHorseWithCondition(ConditionReceiver[] conditions);
}
