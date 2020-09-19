package com.service.provider.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "daily_result_file")
public class CardFileEntity {
	@Id@GeneratedValue(strategy = GenerationType.IDENTITY)@Column(name = "entityId") private long entityId;
	@Column(name = "raceDate") private Date raceDate;
	@Column(name = "raceTime") private Date raceTime;
	@Column(name = "track" , length = 45) private String track;
	@Column(name = "raceName" , length = 300) private String raceName;
	@Column(name = "raceDistance" , length = 45) private String raceDistance;
	@Column(name = "distFurlongs" , length = 45) private String distFurlongs;
	@Column(name = "raceClass" , length = 45) private String raceClass;
	@Column(name = "goingDescription" , length = 300) private String goingDescription;
	@Column(name = "prizeMoney" , length = 45) private String prizeMoney;
	@Column(name = "numberOfRunners" , length = 45) private String numberOfRunners;
	@Column(name = "trackDirection" , length = 45) private String trackDirection;
	@Column(name = "cardNo" , length = 45) private String cardNo;
	@Column(name = "horseName" , length = 300) private String horseName;
	@Column(name = "horseAge" , length = 45) private String horseAge;
	@Column(name = "jockeyName" , length = 45) private String jockeyName;
	@Column(name = "jockeysClaim" , length = 45) private String jockeysClaim;
	@Column(name = "trainer" , length = 45) private String trainer;
	@Column(name = "stallPositioning" , length = 45) private String stallPositioning;
	@Column(name = "officialRating" , length = 45) private String officialRating;
	@Column(name = "weightpounds") private String weightpounds;
	@Column(name = "bfsp" , length = 45) private String bfsp;
	@Column(name = "form" , length = 45) private String form;
	@Column(name = "days" , length = 45) private String days;
	@Column(name = "headgear" , length = 45) private String headgear;
	@Column(name = "stallion" , length = 45) private String stallion;
	@Column(name = "dam" , length = 45) private String dam;
	@Column(name = "horsetype" , length = 45) private String horsetype;
	@Column(name = "cd" , length = 45) private String cd;
	@Column(name = "fav" , length = 45) private String fav;
	
	
	public CardFileEntity() {
		this.raceDate = null;
		this.raceTime = null;
		this.track = "";
		this.raceName = "";
		this.raceDistance = "";
		this.distFurlongs = "";
		this.raceClass = "";
		this.goingDescription = "";
		this.prizeMoney = "";
		this.numberOfRunners = "";
		this.trackDirection = "";
		this.cardNo = "";
		this.horseName = "";
		this.horseAge = "";
		this.jockeyName = "";
		this.jockeysClaim = "";
		this.trainer = "";
		this.stallPositioning = "";
		this.officialRating = "";
		this.weightpounds = "";
		this.bfsp = "";
		this.form = "";
		this.days = "";
		this.headgear = "";
		this.stallion = "";
		this.dam = "";
		this.horsetype = "";
		this.cd = "";
		this.fav = "Yes";
	}
	public String getFav() {
		return fav;
	}
	public void setFav(String fav) {
		this.fav = fav;
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
	public String getRaceDistance() {
		return raceDistance;
	}
	public void setRaceDistance(String raceDistance) {
		this.raceDistance = raceDistance;
	}
	public String getDistFurlongs() {
		return distFurlongs;
	}
	public void setDistFurlongs(String distFurlongs) {
		this.distFurlongs = distFurlongs;
	}
	public String getRaceClass() {
		return raceClass;
	}
	public void setRaceClass(String raceClass) {
		this.raceClass = raceClass;
	}
	public String getGoingDescription() {
		return goingDescription;
	}
	public void setGoingDescription(String goingDescription) {
		this.goingDescription = goingDescription;
	}
	public String getPrizeMoney() {
		return prizeMoney;
	}
	public void setPrizeMoney(String prizeMoney) {
		this.prizeMoney = prizeMoney;
	}
	public String getNumberOfRunners() {
		return numberOfRunners;
	}
	public void setNumberOfRunners(String numberOfRunners) {
		this.numberOfRunners = numberOfRunners;
	}
	public String getTrackDirection() {
		return trackDirection;
	}
	public void setTrackDirection(String trackDirection) {
		this.trackDirection = trackDirection;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getHorseName() {
		return horseName;
	}
	public void setHorseName(String horseName) {
		this.horseName = horseName;
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
	public String getTrainer() {
		return trainer;
	}
	public void setTrainer(String trainer) {
		this.trainer = trainer;
	}
	public String getStallPositioning() {
		return stallPositioning;
	}
	public void setStallPositioning(String stallPositioning) {
		this.stallPositioning = stallPositioning;
	}
	public String getOfficialRating() {
		return officialRating;
	}
	public void setOfficialRating(String officialRating) {
		this.officialRating = officialRating;
	}
	public String getWeightpounds() {
		return weightpounds;
	}
	public void setWeightpounds(String weightpounds) {
		this.weightpounds = weightpounds;
	}
	public String getBfsp() {
		return bfsp;
	}
	public void setBfsp(String bfsp) {
		this.bfsp = bfsp;
	}
	public String getForm() {
		return form;
	}
	public void setForm(String form) {
		this.form = form;
	}
	public String getDays() {
		return days;
	}
	public void setDays(String days) {
		this.days = days;
	}
	public String getHeadgear() {
		return headgear;
	}
	public void setHeadgear(String headgear) {
		this.headgear = headgear;
	}
	public String getStallion() {
		return stallion;
	}
	public void setStallion(String stallion) {
		this.stallion = stallion;
	}
	public String getDam() {
		return dam;
	}
	public void setDam(String dam) {
		this.dam = dam;
	}
	public String getHorsetype() {
		return horsetype;
	}
	public void setHorsetype(String horsetype) {
		this.horsetype = horsetype;
	}
	public String getCd() {
		return cd;
	}
	public void setCd(String cd) {
		this.cd = cd;
	}
	@Override
	public String toString() {
		return "CardFileEntity [entityId=" + entityId + ", raceDate=" + raceDate + ", raceTime=" + raceTime + ", track="
				+ track + ", raceName=" + raceName + ", raceDistance=" + raceDistance + ", distFurlongs=" + distFurlongs
				+ ", raceClass=" + raceClass + ", goingDescription=" + goingDescription + ", prizeMoney=" + prizeMoney
				+ ", numberOfRunners=" + numberOfRunners + ", trackDirection=" + trackDirection + ", cardNo=" + cardNo
				+ ", horseName=" + horseName + ", horseAge=" + horseAge + ", jockeyName=" + jockeyName
				+ ", jockeysClaim=" + jockeysClaim + ", trainer=" + trainer + ", stallPositioning=" + stallPositioning
				+ ", officialRating=" + officialRating + ", weightpounds=" + weightpounds + ", bfsp=" + bfsp + ", form="
				+ form + ", days=" + days + ", headgear=" + headgear + ", stallion=" + stallion + ", dam=" + dam
				+ ", horsetype=" + horsetype + ", cd=" + cd + ", fav=" + fav + "]";
	}
	
	
}
