package com.service.provider.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "speed_figures")
public class SpeedFileEntity {
	
	/**
	 * speed_figure
	 * finishing_position
	 * number_of_runners
	 * distance_beat
	 * days_since_run
	 * days_since_prev_run
	 * class captured
	 * race_distance
	 * draw
	 * drawn_today
	 */
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "entity_id")				private long entityid ;
	@Column(name = "horse_rank")			private long rank ;
	@Column(name = "horse")					private String horse ;
	@Column(name = "speed_figure")			private int speedfigure ;
	@Column(name = "course")				private String course ;
	@Column(name = "finishing_position")	private String finishingposition ;
	@Column(name = "number_of_runners")		private String numberofrunners ;
	@Column(name = "distance_beat")			private String distancebeat ;
	@Column(name = "days_since_run")		private String dayssincerun ;
	@Column(name = "days_since_prev_run")	private String dayssinceprevrun ;
	@Column(name = "race_distance")			private String racedistance ;
	@Column(name = "official_going")		private String officialgoing ;
	@Column(name = "going_allowance")		private String goingallowance ;
	@Column(name = "description")			private String description ;
	@Column(name = "draw")					private String draw ;
	@Column(name = "drawn_today")			private String drawntoday ;
	@Column(name = "class_captured")		private String classcaptured ;
	@Column(name = "surface")				private String surface ;
	@Column(name = "handicap_rating")		private String handicaprating ;
	@Column(name = "trainer")				private String trainer ;
	@Column(name = "circuit_time")			private String circuittime ;
	@Column(name = "noted_performance")		private String notedperformance ;
	@Column(name = "type_of_race")			private String typeofrace ;
	@Column(name = "todays_race_track")		private String todaysracetrack ;
	@Column(name = "race_timing")			private Date racetiming;
	@Column(name = "sheet_name")			private String sheetname;
	@Column(name = "previous_run_date")		private Date previousrundate;
	@Column(name = "speed_file_date")		private Date sheetfiledate;
	@Column(name = "speed_point")			private int speedpoint;
	
	
	
	
	public SpeedFileEntity() {
		this.rank = 0;
		this.horse = "";
		this.speedfigure = 0;
		this.course = "";
		this.finishingposition = "0";
		this.numberofrunners = "0";
		this.distancebeat = "0";
		this.dayssincerun = "0";
		this.dayssinceprevrun = "0";
		this.racedistance = "0";
		this.officialgoing = "";
		this.goingallowance = "0";
		this.description = "";
		this.draw = "0";
		this.drawntoday = "0";
		this.classcaptured = "0";
		this.surface = "";
		this.handicaprating = "0";
		this.trainer = "";
		this.circuittime = "0";
		this.notedperformance = "";
		this.typeofrace = "";
		this.todaysracetrack = "";
		this.racetiming = null;
		this.sheetname = "";
		this.previousrundate = null;
		this.sheetfiledate = null;
		this.speedpoint = 0 ;
	}

	
	public long getRank() {
		return rank;
	}
	public void setRank(long rank) {
		this.rank = rank;
	}
	public int getSpeedpoint() {
		return speedpoint;
	}
	public void setSpeedpoint(int speedpoint) {
		this.speedpoint = speedpoint;
	}
	public Date getSheetfiledate() {
		return sheetfiledate;
	}
	public void setSheetfiledate(Date sheetfiledate) {
		this.sheetfiledate = sheetfiledate;
	}
	public String getTodaysracetrack() {
		return todaysracetrack;
	}
	public void setTodaysracetrack(String todaysracetrack) {
		this.todaysracetrack = todaysracetrack;
	}
	public long getEntityid() {
		return entityid;
	}
	public void setEntityid(long entityid) {
		this.entityid = entityid;
	}
	public String getHorse() {
		return horse;
	}
	public void setHorse(String horse) {
		this.horse = horse;
	}
	public int getSpeedfigure() {
		return speedfigure;
	}
	public void setSpeedfigure(int speedfigure) {
		this.speedfigure = speedfigure;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public String getFinishingposition() {
		return finishingposition;
	}
	public void setFinishingposition(String finishingposition) {
		this.finishingposition = finishingposition;
	}
	public String getNumberofrunners() {
		return numberofrunners;
	}
	public void setNumberofrunners(String numberofrunners) {
		if(numberofrunners.trim().equalsIgnoreCase("")) {
			this.numberofrunners = "0";
		}else {
			this.numberofrunners = numberofrunners;
		}
	}
	public String getDistancebeat() {
		return distancebeat;
	}
	public void setDistancebeat(String distancebeat) {
		if(distancebeat.trim().equalsIgnoreCase("")) {
			this.distancebeat = "0.0";
		}else {
			this.distancebeat = distancebeat;
		}
	}
	public String getDayssincerun() {
		return dayssincerun;
	}
	public void setDayssincerun(String dayssincerun) {
		if(dayssincerun.trim().equalsIgnoreCase("")) {
			this.dayssincerun = "0";
		}else {
			this.dayssincerun = dayssincerun;
		}
		
	}
	public String getDayssinceprevrun() {
		return dayssinceprevrun;
	}
	public void setDayssinceprevrun(String dayssinceprevrun) {
		if(dayssinceprevrun.trim().equalsIgnoreCase("")) {
			this.dayssinceprevrun = "0";
		}else {
			this.dayssinceprevrun = dayssinceprevrun;
		}
		
	}
	public String getRacedistance() {
		return racedistance;
	}
	public void setRacedistance(String racedistance) {
		if(racedistance.trim().equalsIgnoreCase("")) {
			this.racedistance = "0.0";
		}else {
			this.racedistance = racedistance;
		}
	}
	public String getOfficialgoing() {
		return officialgoing;
	}
	public void setOfficialgoing(String officialgoing) {
		this.officialgoing = officialgoing;
	}
	public String getGoingallowance() {
		return goingallowance;
	}
	public void setGoingallowance(String goingallowance) {
		this.goingallowance = goingallowance;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDraw() {
		return draw;
	}
	public void setDraw(String draw) {
		if(draw.trim().equalsIgnoreCase("")) {
			this.draw = "0";
		}else {
			this.draw = draw;
		}
		
	}
	public String getDrawntoday() {
		return drawntoday;
	}
	public void setDrawntoday(String drawntoday) {
		if(drawntoday.trim().equalsIgnoreCase("")) {
			this.drawntoday = "0";
		}else {
			this.drawntoday = drawntoday;
		}
		
	}
	public String getClasscaptured() {
		return classcaptured;
	}
	public void setClasscaptured(String classcaptured) {
		if(classcaptured.trim().equalsIgnoreCase("")) {
			this.classcaptured = "0";
		}else {
			this.classcaptured = classcaptured;
		}
		
	}
	public String getSurface() {
		return surface;
	}
	public void setSurface(String surface) {
		this.surface = surface;
	}
	public String getHandicaprating() {
		return handicaprating;
	}
	public void setHandicaprating(String handicaprating) {
		this.handicaprating = handicaprating;
	}
	public String getTrainer() {
		return trainer;
	}
	public void setTrainer(String trainer) {
		this.trainer = trainer;
	}
	public String getCircuittime() {
		return circuittime;
	}
	public void setCircuittime(String circuittime) {
		this.circuittime = circuittime;
	}
	public String getNotedperformance() {
		return notedperformance;
	}
	public void setNotedperformance(String notedperformance) {
		this.notedperformance = notedperformance;
	}
	public String getTypeofrace() {
		return typeofrace;
	}
	public void setTypeofrace(String typeofrace) {
		this.typeofrace = typeofrace;
	}
	
	public Date getRacetiming() {
		return racetiming;
	}
	public void setRacetiming(Date racetiming) {
		this.racetiming = racetiming;
	}

	public String getSheetname() {
		return sheetname;
	}
	public void setSheetname(String sheetname) {
		this.sheetname = sheetname;
	}
	
	public Date getPreviousrundate() {
		return previousrundate;
	}
	public void setPreviousrundate(Date previousrundate) {
		this.previousrundate = previousrundate;
	}


	@Override
	public String toString() {
		return "SpeedFileEntity [entityid=" + entityid + ", rank=" + rank + ", horse=" + horse + ", speedfigure="
				+ speedfigure + ", course=" + course + ", finishingposition=" + finishingposition + ", numberofrunners="
				+ numberofrunners + ", distancebeat=" + distancebeat + ", dayssincerun=" + dayssincerun
				+ ", dayssinceprevrun=" + dayssinceprevrun + ", racedistance=" + racedistance + ", officialgoing="
				+ officialgoing + ", goingallowance=" + goingallowance + ", description=" + description + ", draw="
				+ draw + ", drawntoday=" + drawntoday + ", classcaptured=" + classcaptured + ", surface=" + surface
				+ ", handicaprating=" + handicaprating + ", trainer=" + trainer + ", circuittime=" + circuittime
				+ ", notedperformance=" + notedperformance + ", typeofrace=" + typeofrace + ", todaysracetrack="
				+ todaysracetrack + ", racetiming=" + racetiming + ", sheetname=" + sheetname + ", previousrundate="
				+ previousrundate + ", sheetfiledate=" + sheetfiledate + ", speedpoint=" + speedpoint + "]";
	}


	
	
	

}
