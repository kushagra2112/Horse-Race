package com.service.provider.model;


import java.util.Date;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DailyHorseDataResult {
	
	@Id
	private long entityId;
	private Date raceDate;
	private Date raceDateTime;
	private String horse ;
	private String course ;
	private long horse_rank ;
	private long daySinceRun ;
	private long speedFigure ;
	private double raceDistance ;
	private String officialGoing ;
	private String classCaptured ;
	private String surface ;
	private String sheetName ;
	private String todaysRaceTrack ;
	private int finishingPosition ;
	private double distFurlongs ;
	private double bfsp ;
	private String trackDirection ;
	private String raceType ;
	private int cardNumber;
	private int speedPoint;
	private String fav;
	
	public String getRaceType() {
		return raceType;
	}
	public void setRaceType(String raceType) {
		this.raceType = raceType;
	}
	public int getFinishingPosition() {
		return finishingPosition;
	}
	public void setFinishingPosition(int finishingPosition) {
		this.finishingPosition = finishingPosition;
	}
	public String getTrackDirection() {
		return trackDirection;
	}
	
	public Date getRaceDateTime() {
		return raceDateTime;
	}
	public void setRaceDateTime(Date raceDateTime) {
		this.raceDateTime = raceDateTime;
	}
	public String getFav() {
		return fav;
	}
	public void setFav(String fav) {
		this.fav = fav;
	}
	public long getHorse_rank() {
		return horse_rank;
	}
	public void setHorse_rank(long horse_rank) {
		this.horse_rank = horse_rank;
	}
	public Date getRaceDate() {
		return raceDate;
	}
	public void setRaceDate(Date raceDate) {
		this.raceDate = raceDate;
	}
	public int getSpeedPoint() {
		return speedPoint;
	}
	public void setSpeedPoint(int speedPoint) {
		this.speedPoint = speedPoint;
	}
	
	public long getEntityId() {
		return entityId;
	}
	public void setEntityId(long entityId) {
		this.entityId = entityId;
	}
	public long getSpeedFigure() {
		return speedFigure;
	}
	public void setSpeedFigure(long speedFigure) {
		this.speedFigure = speedFigure;
	}
	public int getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getHorse() {
		return horse;
	}
	public void setHorse(String horse) {
		this.horse = horse;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public long getDaySinceRun() {
		return daySinceRun;
	}
	public void setDaySinceRun(long daySinceRun) {
		this.daySinceRun = daySinceRun;
	}
	public double getRaceDistance() {
		return raceDistance;
	}
	public void setRaceDistance(double raceDistance) {
		this.raceDistance = raceDistance;
	}
	public String getOfficialGoing() {
		return officialGoing;
	}
	public void setOfficialGoing(String officialGoing) {
		this.officialGoing = officialGoing;
	}
	public String getClassCaptured() {
		return classCaptured;
	}
	public void setClassCaptured(String classCaptured) {
		this.classCaptured = classCaptured;
	}
	public String getSurface() {
		return surface;
	}
	public void setSurface(String surface) {
		this.surface = surface;
	}
	public String getSheetName() {
		return sheetName;
	}
	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}
	public String getTodaysRaceTrack() {
		return todaysRaceTrack;
	}
	public void setTodaysRaceTrack(String todaysRaceTrack) {
		this.todaysRaceTrack = todaysRaceTrack;
	}
	
	public double getDistFurlongs() {
		return distFurlongs;
	}
	public void setDistFurlongs(double distFurlongs) {
		this.distFurlongs = distFurlongs;
	}
	public double getBfsp() {
		return bfsp;
	}
	public void setBfsp(double bfsp) {
		this.bfsp = bfsp;
	}
	public void setTrackDirection(String trackDirection) {
		this.trackDirection = trackDirection;
	}
	@Override
	public String toString() {
		return "DailyHorseDataResult [entityId=" + entityId + ", raceDate=" + raceDate + ", raceDateTime="
				+ raceDateTime + ", horse=" + horse + ", course=" + course + ", horse_rank=" + horse_rank
				+ ", daySinceRun=" + daySinceRun + ", speedFigure=" + speedFigure + ", raceDistance=" + raceDistance
				+ ", officialGoing=" + officialGoing + ", classCaptured=" + classCaptured + ", surface=" + surface
				+ ", sheetName=" + sheetName + ", todaysRaceTrack=" + todaysRaceTrack + ", finishingPosition="
				+ finishingPosition + ", distFurlongs=" + distFurlongs + ", bfsp=" + bfsp + ", trackDirection="
				+ trackDirection + ", raceType=" + raceType + ", cardNumber=" + cardNumber + ", speedPoint="
				+ speedPoint + ", fav=" + fav + "]";
	}
}
