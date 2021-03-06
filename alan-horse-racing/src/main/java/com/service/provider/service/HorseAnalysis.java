package com.service.provider.service;

import com.service.provider.model.AnalysisResponse;
import com.service.provider.model.ConditionReceiver;

public interface HorseAnalysis {
	AnalysisResponse analyzeHorseWithCondition(ConditionReceiver[] conditions);
}
