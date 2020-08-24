package com.service.provider.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.provider.model.TrackShortName;
import com.service.provider.repository.TrackShortNameRepository;

@Service
public class GetTrackShortNameImp implements GetTrackShortName {
	private static final Logger logger = LogManager.getLogger();
	
	@Autowired
	TrackShortNameRepository trackShortNameRepository;

	@Override
	public List<TrackShortName> gettrackShortName() {
		List<TrackShortName> trackShortNameList = new ArrayList<>();
		try {
			trackShortNameList = trackShortNameRepository.findAll();
			
			logger.info("short name received - " , trackShortNameList.size());
			
		}catch(Exception e) {
			logger.error("Something went wrong while fetching short name for the tracks");
			logger.info(e.getMessage());
		}
		return trackShortNameList;
	}

}
