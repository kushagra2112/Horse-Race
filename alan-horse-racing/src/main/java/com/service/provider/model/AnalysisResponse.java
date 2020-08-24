package com.service.provider.model;

import java.util.List;

public class AnalysisResponse {
	private String condition;
	private WinningPercentage winRaceStats;
	private List<ListOfWinners> winners;
	private List<PositionAnalysis> daysReport;
	private List<StrikeInfo> stikes;
	
	public List<PositionAnalysis> getDaysReport() {
		return daysReport;
	}

	public void setDaysReport(List<PositionAnalysis> daysReport) {
		this.daysReport = daysReport;
	}

	public List<StrikeInfo> getStikes() {
		return stikes;
	}

	public void setStikes(List<StrikeInfo> stikes) {
		this.stikes = stikes;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public WinningPercentage getWinRaceStats() {
		return winRaceStats;
	}

	public void setWinRaceStats(WinningPercentage winRaceStats) {
		this.winRaceStats = winRaceStats;
	}

	public List<ListOfWinners> getWinners() {
		return winners;
	}

	public void setWinners(List<ListOfWinners> winners) {
		this.winners = winners;
	}

	@Override
	public String toString() {
		return "AnalysisResponse [condition=" + condition + ", winRaceStats=" + winRaceStats + ", winners=" + winners
				+ ", daysReport=" + daysReport + ", stikes=" + stikes + "]";
	}

		
}
