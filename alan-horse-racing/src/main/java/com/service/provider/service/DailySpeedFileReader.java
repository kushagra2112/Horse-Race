package com.service.provider.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import com.service.provider.model.ResponseData;
import com.service.provider.model.SpeedFileEntity;
import com.service.provider.model.TrackShortName;

public interface DailySpeedFileReader {
	void readAndSaveSpeedFile(FileInputStream file, List<TrackShortName> trackShortNames, String date ) throws FileNotFoundException;
}
