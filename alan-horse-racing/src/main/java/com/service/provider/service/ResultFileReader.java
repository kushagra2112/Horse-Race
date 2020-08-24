package com.service.provider.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import com.service.provider.model.ResultFileEntity;
import com.service.provider.model.SpeedFileEntity;
import com.service.provider.model.TrackShortName;

public interface ResultFileReader {
	List<ResultFileEntity> readJExcel(FileInputStream file) throws FileNotFoundException;
}
