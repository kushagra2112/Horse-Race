package com.service.provider.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "result_file_new")
public class ResultFileEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "entityId")
	private long entityId;

	@Column(name = "raceDate")
	private Date raceDate;

	@Column(name = "raceTime")
	private Date raceTime;

	@Column(name = "track" , length = 45)
	private String track;

	@Column(name = "raceName" , length = 300)
	private String raceName;

	@Column(name = "raceRestrictionsAge" , length = 45)
	private String raceRestrictionsAge;

	@Column(name = "raceClass" , length = 45)
	private String raceClass;

	@Column(name = "major" , length = 45)
	private String major;

	@Column(name = "raceDistance" , length = 45)
	private String raceDistance;

	@Column(name = "prizeMoney" , length = 45)
	private String prizeMoney;

	@Column(name = "goingDescription" , length = 300)
	private String goingDescription;

	@Column(name = "numberOfRunners" , length = 45)
	private String numberOfRunners;

	@Column(name = "place" , length = 45)
	private String place;

	@Column(name = "distbt" , length = 45)
	private String distbt;

	@Column(name = "horseName" , length = 300)
	private String horseName;

	@Column(name = "stall" , length = 45)
	private String stall;

	@Column(name = "trainer" , length = 45)
	private String trainer;

	@Column(name = "horseAge" , length = 45)
	private String horseAge;

	@Column(name = "jockeyName" , length = 45)
	private String jockeyName;

	@Column(name = "jockeysClaim" , length = 45)
	private String jockeysClaim;

	@Column(name = "pounds" , length = 45)
	private String pounds;

	@Column(name = "odds" , length = 45)
	private String odds;

	@Column(name = "fav" , length = 45)
	private String fav;

	@Column(name = "officialRating" , length = 45)
	private String officialRating;

	@Column(name = "comptime" , length = 45)
	private String comptime;

	@Column(name = "comtimeNumeric" , length = 45)
	private String comtimeNumeric;

	@Column(name = "totalDstBt" , length = 45)
	private String totalDstBt;

	@Column(name = "medianOr" , length = 45)
	private String medianOr;

	@Column(name = "distFurlongs" , length = 45)
	private String distFurlongs;

	@Column(name = "placingNumerical" , length = 45)
	private String placingNumerical;

	@Column(name = "rCode" , length = 45)
	private String rCode;

	@Column(name = "bfsp" , length = 45)
	private String bfsp;

	@Column(name = "bfspPlace" , length = 45)
	private String bfspPlace;

	@Column(name = "plcsPaid" , length = 45)
	private String plcsPaid;

	@Column(name = "bfPlcsPaid" , length = 45)
	private String bfPlcsPaid;

	@Column(name = "yards" , length = 45)
	private String yards;

	@Column(name = "railMove" , length = 45)
	private String railMove;

	@Column(name = "raceType" , length = 45)
	private String raceType;

	@Column(name = "horseComment" , length = 300)
	private String horseComment;

	@Column(name = "cardNo" , length = 45)
	private String cardNo;

	@Column(name = "stallPositioning" , length = 45)
	private String stallPositioning;

	@Column(name = "trackDirection" , length = 45)
	private String trackDirection;

	@Column(name = "headgear" , length = 45)
	private String headgear;
	
	@Column(name = "speedFigure" )
	private long speedFigure;
	
	
	@Column(name = "speedPoint" )
	private int speedPoint;
	


	public ResultFileEntity() {
		
		this.raceName = "";
		this.raceRestrictionsAge = "";
		this.raceClass = "";
		this.major = "";
		this.raceDistance = "";
		this.prizeMoney = "";
		this.goingDescription = "";
		this.numberOfRunners = "";
		this.place = "";
		this.distbt = "";
		this.horseName = "";
		this.stall = "";
		this.trainer = "";
		this.horseAge = "0";
		this.jockeyName = "";
		this.jockeysClaim = "";
		this.pounds = "";
		this.odds = "";
		this.fav = "";
		this.officialRating = "";
		this.comptime = "";
		this.comtimeNumeric = "";
		this.totalDstBt = "";
		this.medianOr = "";
		this.distFurlongs = "0.0";
		this.placingNumerical = "0.0";
		this.rCode = "";
		this.bfsp = "0.0";
		this.bfspPlace = "0.0";
		this.plcsPaid = "0.0";
		this.bfPlcsPaid = "0.0";
		this.yards = "";
		this.railMove = "";
		this.raceType = "";
		this.horseComment = "";
		this.cardNo = "0";
		this.stallPositioning = "";
		this.trackDirection = "";
		this.headgear = "";
		this.speedFigure = 0;
		this.speedPoint = 0 ;
		
	}

	
	
	public long getSpeedFigure() {
		return speedFigure;
	}

	public void setSpeedFigure(long speedFigure) {
		this.speedFigure = speedFigure;
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

	public Date getRaceDate() {
		return raceDate;
	}

	public void setRaceDate(Date raceDate) {
		this.raceDate = raceDate;
	}

	public Date getRaceTime() {
		return raceTime;
	}

	public void setRaceTime(Date raceTime) {
		this.raceTime = raceTime;
	}

	public String getTrack() {
		return track;
	}

	public void setTrack(String track) {
		this.track = track;
	}

	public String getRaceName() {
		return raceName;
	}

	public void setRaceName(String raceName) {
		this.raceName = raceName;
	}

	public String getRaceRestrictionsAge() {
		return raceRestrictionsAge;
	}

	public void setRaceRestrictionsAge(String raceRestrictionsAge) {
		this.raceRestrictionsAge = raceRestrictionsAge;
	}

	public String getRaceClass() {
		return raceClass;
	}

	public void setRaceClass(String raceClass) {
		this.raceClass = raceClass;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getRaceDistance() {
		return raceDistance;
	}

	public void setRaceDistance(String raceDistance) {
		this.raceDistance = raceDistance;
	}

	public String getPrizeMoney() {
		return prizeMoney;
	}

	public void setPrizeMoney(String prizeMoney) {
		this.prizeMoney = prizeMoney;
	}

	public String getGoingDescription() {
		return goingDescription;
	}

	public void setGoingDescription(String goingDescription) {
		this.goingDescription = goingDescription;
	}

	public String getNumberOfRunners() {
		return numberOfRunners;
	}

	public void setNumberOfRunners(String numberOfRunners) {
		this.numberOfRunners = numberOfRunners;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getDistbt() {
		return distbt;
	}

	public void setDistbt(String distbt) {
		this.distbt = distbt;
	}

	public String getHorseName() {
		return horseName;
	}

	public void setHorseName(String horseName) {
		this.horseName = horseName;
	}

	public String getStall() {
		return stall;
	}

	public void setStall(String stall) {
		this.stall = stall;
	}

	public String getTrainer() {
		return trainer;
	}

	public void setTrainer(String trainer) {
		this.trainer = trainer;
	}

	public String getHorseAge() {
		return horseAge;
	}

	public void setHorseAge(String horseAge) {
		this.horseAge = horseAge;
	}

	public String getJockeyName() {
		return jockeyName;
	}

	public void setJockeyName(String jockeyName) {
		this.jockeyName = jockeyName;
	}

	public String getJockeysClaim() {
		return jockeysClaim;
	}

	public void setJockeysClaim(String jockeysClaim) {
		this.jockeysClaim = jockeysClaim;
	}

	public String getPounds() {
		return pounds;
	}

	public void setPounds(String pounds) {
		this.pounds = pounds;
	}

	public String getOdds() {
		return odds;
	}

	public void setOdds(String odds) {
		this.odds = odds;
	}

	public String getFav() {
		return fav;
	}

	public void setFav(String fav) {
		this.fav = fav;
	}

	public String getOfficialRating() {
		return officialRating;
	}

	public void setOfficialRating(String officialRating) {
		this.officialRating = officialRating;
	}

	public String getComptime() {
		return comptime;
	}

	public void setComptime(String comptime) {
		this.comptime = comptime;
	}

	public String getComtimeNumeric() {
		return comtimeNumeric;
	}

	public void setComtimeNumeric(String comtimeNumeric) {
		this.comtimeNumeric = comtimeNumeric;
	}

	public String getTotalDstBt() {
		return totalDstBt;
	}

	public void setTotalDstBt(String totalDstBt) {
		this.totalDstBt = totalDstBt;
	}

	public String getMedianOr() {
		return medianOr;
	}

	public void setMedianOr(String medianOr) {
		this.medianOr = medianOr;
	}

	public String getDistFurlongs() {
		return distFurlongs;
	}

	public void setDistFurlongs(String distFurlongs) {
		this.distFurlongs = distFurlongs;
	}

	public String getPlacingNumerical() {
		return placingNumerical;
	}

	public void setPlacingNumerical(String placingNumerical) {
		this.placingNumerical = placingNumerical;
	}

	public String getrCode() {
		return rCode;
	}

	public void setrCode(String rCode) {
		this.rCode = rCode;
	}

	public String getBfsp() {
		return bfsp;
	}

	public void setBfsp(String bfsp) {
		this.bfsp = bfsp;
	}

	public String getBfspPlace() {
		return bfspPlace;
	}

	public void setBfspPlace(String bfspPlace) {
		this.bfspPlace = bfspPlace;
	}

	public String getPlcsPaid() {
		return plcsPaid;
	}

	public void setPlcsPaid(String plcsPaid) {
		this.plcsPaid = plcsPaid;
	}

	public String getBfPlcsPaid() {
		return bfPlcsPaid;
	}

	public void setBfPlcsPaid(String bfPlcsPaid) {
		this.bfPlcsPaid = bfPlcsPaid;
	}

	public String getYards() {
		return yards;
	}

	public void setYards(String yards) {
		this.yards = yards;
	}

	public String getRailMove() {
		return railMove;
	}

	public void setRailMove(String railMove) {
		this.railMove = railMove;
	}

	public String getRaceType() {
		return raceType;
	}

	public void setRaceType(String raceType) {
		this.raceType = raceType;
	}

	public String getHorseComment() {
		return horseComment;
	}

	public void setHorseComment(String horseComment) {
		this.horseComment = horseComment;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getStallPositioning() {
		return stallPositioning;
	}

	public void setStallPositioning(String stallPositioning) {
		this.stallPositioning = stallPositioning;
	}

	public String getTrackDirection() {
		return trackDirection;
	}

	public void setTrackDirection(String trackDirection) {
		this.trackDirection = trackDirection;
	}

	public String getHeadgear() {
		return headgear;
	}

	public void setHeadgear(String headgear) {
		this.headgear = headgear;
	}


	@Override
	public String toString() {
		return "ResultFileEntity [entityId=" + entityId + ", raceDate=" + raceDate + ", raceTime=" + raceTime
				+ ", track=" + track + ", raceName=" + raceName + ", raceRestrictionsAge=" + raceRestrictionsAge
				+ ", raceClass=" + raceClass + ", major=" + major + ", raceDistance=" + raceDistance + ", prizeMoney="
				+ prizeMoney + ", goingDescription=" + goingDescription + ", numberOfRunners=" + numberOfRunners
				+ ", place=" + place + ", distbt=" + distbt + ", horseName=" + horseName + ", stall=" + stall
				+ ", trainer=" + trainer + ", horseAge=" + horseAge + ", jockeyName=" + jockeyName + ", jockeysClaim="
				+ jockeysClaim + ", pounds=" + pounds + ", odds=" + odds + ", fav=" + fav + ", officialRating="
				+ officialRating + ", comptime=" + comptime + ", comtimeNumeric=" + comtimeNumeric + ", totalDstBt="
				+ totalDstBt + ", medianOr=" + medianOr + ", distFurlongs=" + distFurlongs + ", placingNumerical="
				+ placingNumerical + ", rCode=" + rCode + ", bfsp=" + bfsp + ", bfspPlace=" + bfspPlace + ", plcsPaid="
				+ plcsPaid + ", bfPlcsPaid=" + bfPlcsPaid + ", yards=" + yards + ", railMove=" + railMove
				+ ", raceType=" + raceType + ", horseComment=" + horseComment + ", cardNo=" + cardNo
				+ ", stallPositioning=" + stallPositioning + ", trackDirection=" + trackDirection + ", headgear="
				+ headgear + ", speedFigure=" + speedFigure + ", speedPoint=" + speedPoint + "]";
	}

	

}
