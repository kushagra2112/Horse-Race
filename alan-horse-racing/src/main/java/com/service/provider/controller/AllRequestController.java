package com.service.provider.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.provider.model.AnalysisResponse;
import com.service.provider.model.ConditionReceiver;
import com.service.provider.model.ResponseData;
import com.service.provider.model.ResultFileEntity;
import com.service.provider.model.SpeedFileEntity;
import com.service.provider.model.TrackShortName;
import com.service.provider.service.ConditionHandler;
import com.service.provider.service.GetTrackShortName;
import com.service.provider.service.HorseAnalysis;
import com.service.provider.service.ResultFileReader;
import com.service.provider.service.SpeedFileReader;
import com.service.provider.service.UploadResultFile;

@Controller
@ResponseBody
@RequestMapping(path = "/horse-racing/")
public class AllRequestController {

	private String fileLocation;
	private static final Logger logger = LogManager.getLogger();

	@Autowired
	UploadResultFile uploadResultFile;

	@Autowired
	ResultFileReader resultFileReader;

	@Autowired
	SpeedFileReader speedFileReader;

	@Autowired
	GetTrackShortName getTrackShortName;
	
	@Autowired
	ConditionHandler conditionHandeler;

	@Autowired
	HorseAnalysis horseAnalysis;

	ObjectMapper mapper = new ObjectMapper();

	@PostMapping(value = "readResultFile")
	public ResponseEntity<ResponseData> readResultFile(MultipartFile resultFile,
			MultipartFile speedFile ,  String date) throws IOException {
		
		ResponseData response = new ResponseData();
		
		
		//Speed File
		
		FileInputStream inputstream = (FileInputStream) speedFile.getInputStream();
		File currDir = new File(".");
		String path = currDir.getAbsolutePath();
		fileLocation = path.substring(0, path.length() - 1) + speedFile.getOriginalFilename();
		logger.info(fileLocation);

		List<TrackShortName> trackShortNames = getTrackShortName.gettrackShortName();

		if (fileLocation != null) {
			if (fileLocation.endsWith(".xlsx")) {
				speedFileReader.readAndSaveSpeedFile(inputstream, trackShortNames, date);
			}
		} else {
			response.setMessage("Not a valid excel file!");
			response.setStatus("Failed");
			response.setFileName(speedFile.getOriginalFilename());
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		
		
		
		// Result File
		
		FileInputStream inputstream2 = (FileInputStream) resultFile.getInputStream();
		currDir = new File(".");
		path = currDir.getAbsolutePath();
		fileLocation = path.substring(0, path.length() - 1) + resultFile.getOriginalFilename();
		logger.info(fileLocation);

		if (fileLocation != null) {
			if (fileLocation.endsWith(".xlsx")) {

				List<ResultFileEntity> sheetData = resultFileReader.readJExcel(inputstream2);
				
				response = uploadResultFile.uploadResultFileIntoDB(sheetData, date);
				response.setFileName(resultFile.getOriginalFilename());
				
				if (response.getStatus().equals("Success")) {
					return new ResponseEntity<>(response, HttpStatus.OK);
				} else {
					return new ResponseEntity<>(response, HttpStatus.CONFLICT);
				}
				
			} else {
				response.setMessage("Not a valid excel file!");
				response.setStatus("Failed");
				response.setFileName(resultFile.getOriginalFilename());
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}
		} else {
			response.setMessage("Not a valid excel file!");
			response.setStatus("Failed");
			response.setFileName(resultFile.getOriginalFilename());
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	/*@PostMapping(value = "readSpeedFile")
	public ResponseEntity<ResponseData> readSpeedFile(MultipartFile file, String date) throws IOException {
		
		ResponseData response = new ResponseData();
		FileInputStream inputstream = (FileInputStream) file.getInputStream();
		File currDir = new File(".");
		String path = currDir.getAbsolutePath();
		fileLocation = path.substring(0, path.length() - 1) + file.getOriginalFilename();
		logger.info(fileLocation);

		List<TrackShortName> trackShortNames = getTrackShortName.gettrackShortName();

		if (fileLocation != null) {
			if (fileLocation.endsWith(".xlsx")) {
				response = speedFileReader.readAndSaveSpeedFile(inputstream, trackShortNames, date);
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				response.setMessage("Not a valid excel file!");
				response.setStatus("Failed");
				response.setFileName(file.getOriginalFilename());
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}
		} else {
			response.setMessage("Not a valid excel file!");
			response.setStatus("Failed");
			response.setFileName(file.getOriginalFilename());
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}*/

	/*@GetMapping(value = "getData")
	public Map<String, HashMap<String, List<ListOfTopHorses>>> getTopHorseRaceData() {
		// Map<String, ResultFileEntity> response = new HashMap<String,
		// ResultFileEntity>();
		Map<String, HashMap<String, List<ListOfTopHorses>>> listOfTopHorses = new HashMap<String, HashMap<String, List<ListOfTopHorses>>>();
		listOfTopHorses = horseAnalysis.getListOfTopHorses();
		return listOfTopHorses;
	}
	*/
	@PostMapping(value = "addCondition")
	public AnalysisResponse addCondition(
			String conditionString) {
		ConditionReceiver[] conditions = null;
		try {
			conditions = mapper.readValue(conditionString, ConditionReceiver[].class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		AnalysisResponse conditionResult = new AnalysisResponse();
		conditionResult = horseAnalysis.analyzeHorseWithCondition(conditions);
		return conditionResult;
	}
	
	@PostMapping(value = "saveCondition")
	public ResponseEntity<String> saveCondition(
			String condition,
			String conditionStatement) {
		String returnValue = null;
		
		if((returnValue =  conditionHandeler.conditionNamePresent(condition)) != null) {
			return new ResponseEntity<>("Condition Name Already present for the condition :: " + returnValue, HttpStatus.BAD_REQUEST);
		}else if((returnValue = conditionHandeler.conditionPresent(conditionStatement)) != null ) {
			return new ResponseEntity<>("Condition Already present with the name ::" + returnValue , HttpStatus.BAD_REQUEST);
		}else {
			if(conditionHandeler.saveCondition(condition, conditionStatement)) {
				return new ResponseEntity<>("Condition Saved Successfully", HttpStatus.OK);
			}else {
				return new ResponseEntity<>("Something went Wrong", HttpStatus.BAD_REQUEST);
			}
		}
	}
	

}
