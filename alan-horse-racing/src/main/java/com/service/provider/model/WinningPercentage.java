package com.service.provider.model;

public class WinningPercentage {
	
	private long qualifiedRace;
	private long totalWinCount;
	private String winPercentage;
	private double bfspTotal;
	private double profitBfsp;
	private double bfspPlaceTotal;
	private double bfspPlaceProfit;
	private long bfspPlaceWin;
	private double bfspPlaceWinPercnt;
	
	
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
		return "WinningPercentage [qualifiedRace=" + qualifiedRace + ", totalWinCount=" + totalWinCount
				+ ", winPercentage=" + winPercentage + ", bfspTotal=" + bfspTotal + ", profitBfsp=" + profitBfsp
				+ ", bfspPlaceTotal=" + bfspPlaceTotal + ", bfspPlaceProfit=" + bfspPlaceProfit + ", bfspPlaceWin="
				+ bfspPlaceWin + ", bfspPlaceWinPercnt=" + bfspPlaceWinPercnt + "]";
	}
	
	
}
