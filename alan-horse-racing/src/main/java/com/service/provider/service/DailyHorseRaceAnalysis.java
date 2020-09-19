package com.service.provider.service;

import java.util.List;

import com.service.provider.model.AnalysisResponse;
import com.service.provider.model.ConditionReceiver;

public interface DailyHorseRaceAnalysis {
	List<AnalysisResponse> analyzeHorseWithCondition();
}
