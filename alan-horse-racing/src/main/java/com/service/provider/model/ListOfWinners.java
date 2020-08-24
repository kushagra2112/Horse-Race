package com.service.provider.model;

public class ListOfWinners {
	
	private String raceDate;
	private String track;
	private String horseName;
	private double bfsp;
	private double distance;

	
	public double getBfsp() {
		return bfsp;
	}
	public void setBfsp(double bfsp) {
		this.bfsp = bfsp;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public String getRaceDate() {
		return raceDate;
	}
	public void setRaceDate(String raceDate) {
		this.raceDate = raceDate;
	}
	public String getTrack() {
		return track;
	}
	public void setTrack(String track) {
		this.track = track;
	}
	public String getHorseName() {
		return horseName;
	}
	public void setHorseName(String horseName) {
		this.horseName = horseName;
	}
	@Override
	public String toString() {
		return "ListOfWinners [raceDate=" + raceDate + ", track=" + track + ", horseName=" + horseName + ", bfsp="
				+ bfsp + ", distance=" + distance + "]";
	}
	

	
}
