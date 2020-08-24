package com.service.provider.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.provider.model.ResponseData;
import com.service.provider.model.ResultFileEntity;
import com.service.provider.model.SpeedFileEntity;
import com.service.provider.repository.ResultFileRepository;
import com.service.provider.repository.SpeedFileRepository;

@Service
public class UploadResultFileImp implements UploadResultFile {
	private static final Logger logger = LogManager.getLogger();
	
	@Autowired
	ResultFileRepository resultFileRepository; 
	
	@Autowired
	SpeedFileRepository speedFileRepository; 
	
	

	
	@Override
	public ResponseData uploadResultFileIntoDB(List<ResultFileEntity> allSheetData, String date) {
		ResponseData response = new ResponseData();
		Date todaysDate = new Date();
		
		try {
			if (date != null || date != "") {
				todaysDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
			}
		} catch (ParseException e) {
			logger.error(e.getMessage());
		}
		
		try {
			//String dateString = new SimpleDateFormat("yyyy-MM-dd").format(todaysDate);
			int deletedCount = resultFileRepository.deleteByRaceDate(todaysDate);
			logger.info("Old count deleted from result file = " +  deletedCount);
			
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
		
		try {
			//resultFileRepository.save(allSheetData.get(0));
			resultFileRepository.saveAll(allSheetData);
			logger.info("Complete data saved");
			
			response.setStatus("Success");
			response.setMessage("File Uploaded Sucessfully");
		}catch(Exception e) {
			logger.error(e.getMessage());
			response.setStatus("Failed");
			response.setMessage("Something went wrong");
			response.setError(e.getMessage());
		}
		return response;
	}

	
	@Override
	public ResponseData uploadSpeedFileIntoDB(List<SpeedFileEntity> allSheetData) {
		ResponseData response = new ResponseData();
		try {
			speedFileRepository.saveAll(allSheetData);			
			logger.info("Complete data saved");
			
			response.setStatus("Success");
			response.setMessage("File Uploaded Sucessfully");
		}catch(Exception e) {
			logger.error(e.getMessage());
			response.setStatus("Failed");
			response.setMessage("Something went wrong");
			response.setError(e.getMessage());
		}
		return response;
	}

}
