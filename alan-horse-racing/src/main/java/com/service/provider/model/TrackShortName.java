package com.service.provider.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "track_short_name")
public class TrackShortName {

	@Id 
	@Column(name  = "entity_id")
	private long entity_id;
	
	
	@Column(name  = "track_name")
	private String trackName;
	
	@Column(name  = "track_short_name")
	private String trackShortName;

	public long getEntity_id() {
		return entity_id;
	}

	public void setEntity_id(long entity_id) {
		this.entity_id = entity_id;
	}

	public String getTrackName() {
		return trackName;
	}

	public void setTrackName(String trackName) {
		this.trackName = trackName;
	}

	public String getTrackShortName() {
		return trackShortName;
	}

	public void setTrackShortName(String trackShortName) {
		this.trackShortName = trackShortName;
	}

	
	
	@Override
	public String toString() {
		return "trackShortName [entity_id=" + entity_id + ", trackName=" + trackName + ", trachShortName="
				+ trackShortName + "]";
	}
	
	
	
		
}
