package com.service.provider.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "data_analysis_report")
public class DailyAnalysisResultReport {
	
	@Id@GeneratedValue(strategy = GenerationType.IDENTITY)@Column(name = "entityId") private long entityId;
	@Column(name = "consitionString") private String conditionString;
	@Column(name = "consitionName") private String conditionName;
	@Column(name = "qualifiedRace") private long qualifiedRace;
	@Column(name = "totalWinCount") private long totalWinCount;
	@Column(name = "winPercentage") private String winPercentage;
	@Column(name = "bfspTotal") private double bfspTotal;
	@Column(name = "profitBfsp") private double profitBfsp;
	@Column(name = "bfspPlaceTotal") private double bfspPlaceTotal;
	@Column(name = "bfspPlaceProfit") private double bfspPlaceProfit;
	@Column(name = "bfspPlaceWin") private long bfspPlaceWin;
	@Column(name = "bfspPlaceWinPercnt") private double bfspPlaceWinPercnt;
	
	
	
	public long getEntityId() {
		return entityId;
	}
	public void setEntityId(long entityId) {
		this.entityId = entityId;
	}
	public String getConditionName() {
		return conditionName;
	}
	public void setConditionName(String conditionName) {
		this.conditionName = conditionName;
	}
	public String getConditionString() {
		return conditionString;
	}
	public void setConditionString(String conditionString) {
		this.conditionString = conditionString;
	}
	public long getBfspPlaceWin() {
		return bfspPlaceWin;
	}
	public void setBfspPlaceWin(long bfspPlaceWin) {
		this.bfspPlaceWin = bfspPlaceWin;
	}
	public double getBfspPlaceWinPercnt() {
		return bfspPlaceWinPercnt;
	}
	public void setBfspPlaceWinPercnt(double bfspPlaceWinPercnt) {
		this.bfspPlaceWinPercnt = bfspPlaceWinPercnt;
	}
	public double getBfspPlaceTotal() {
		return bfspPlaceTotal;
	}
	public void setBfspPlaceTotal(double bfspPlaceTotal) {
		this.bfspPlaceTotal = bfspPlaceTotal;
	}
	public double getBfspPlaceProfit() {
		return bfspPlaceProfit;
	}
	public void setBfspPlaceProfit(double bfspPlaceProfit) {
		this.bfspPlaceProfit = bfspPlaceProfit;
	}
	public double getProfitBfsp() {
		return profitBfsp;
	}
	public void setProfitBfsp(double profitBfsp) {
		this.profitBfsp = profitBfsp;
	}
	public double getBfspTotal() {
		return bfspTotal;
	}
	public void setBfspTotal(double bfspTotal) {
		this.bfspTotal = bfspTotal;
	}
	public long getQualifiedRace() {
		return qualifiedRace;
	}
	public void setQualifiedRace(long qualifiedRace) {
		this.qualifiedRace = qualifiedRace;
	}
	public long getTotalWinCount() {
		return totalWinCount;
	}
	public void setTotalWinCount(long totalWinCount) {
		this.totalWinCount = totalWinCount;
	}
	public String getWinPercentage() {
		return winPercentage;
	}
	public void setWinPercentage(String winPercentage) {
		this.winPercentage = winPercentage;
	}
	@Override
	public String toString() {
		return "DailyAnalysisResultReport [entityId=" + entityId + ", conditionString=" + conditionString
				+ ", conditionName=" + conditionName + ", qualifiedRace=" + qualifiedRace + ", totalWinCount="
				+ totalWinCount + ", winPercentage=" + winPercentage + ", bfspTotal=" + bfspTotal + ", profitBfsp="
				+ profitBfsp + ", bfspPlaceTotal=" + bfspPlaceTotal + ", bfspPlaceProfit=" + bfspPlaceProfit
				+ ", bfspPlaceWin=" + bfspPlaceWin + ", bfspPlaceWinPercnt=" + bfspPlaceWinPercnt + "]";
	}
	
	
}
