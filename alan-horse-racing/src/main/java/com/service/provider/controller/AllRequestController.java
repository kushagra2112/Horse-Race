package com.service.provider.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.provider.model.AnalysisResponse;
import com.service.provider.model.ConditionReceiver;
import com.service.provider.model.ConditionSaver;
import com.service.provider.model.DailyAnalysisResultReport;
import com.service.provider.model.HorseDataResult;
import com.service.provider.model.ResponseData;
import com.service.provider.model.TrackShortName;
import com.service.provider.repository.DailyAnalysisRepository;
import com.service.provider.repository.HorseRaceAnalysisRepository;
import com.service.provider.service.CompleteDataAnalysis;
import com.service.provider.service.ConditionHandler;
import com.service.provider.service.DailyCardFile;
import com.service.provider.service.DailyHorseRaceAnalysis;
import com.service.provider.service.DailySpeedFileReader;
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
	public static List<DailyAnalysisResultReport> winnerStats = new ArrayList<DailyAnalysisResultReport>();
	
	@Autowired
	UploadResultFile uploadResultFile;

	@Autowired
	ResultFileReader resultFileReader;

	@Autowired
	SpeedFileReader speedFileReader;
	
	@Autowired
	DailySpeedFileReader dailySpeedFileReader;
	
	@Autowired
	DailyCardFile dailyCardFile;

	@Autowired
	GetTrackShortName getTrackShortName;
	
	@Autowired
	ConditionHandler conditionHandeler;

	@Autowired
	HorseAnalysis horseAnalysis;
	
	@Autowired
	DailyHorseRaceAnalysis dailyHorseRaceAnalysis;
	
	@Autowired
	CompleteDataAnalysis completeDataAnalysis;
	
	@Autowired
	HorseRaceAnalysisRepository horseRaceAnalysisRepository;

	@Autowired
	DailyAnalysisRepository dailyAnalysisRepository; 

	public static List<DailyAnalysisResultReport> data_analysis = new ArrayList<>();
	
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
				response.setFileName(resultFile.getOriginalFilename());
				if(resultFileReader.readJExcel(inputstream2, date)) {
					response.setStatus("Success");
					response.setMessage("File Uploaded Sucessfully");
					return new ResponseEntity<>(response, HttpStatus.OK);
				}else {
					response.setStatus("Failed");
					response.setMessage("Something went wrong");
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

		
	@PostMapping(value= "getDailyFileAnalysis")
	public ResponseEntity<List<AnalysisResponse>> getDailyFileAnalysis(MultipartFile resultFile,
			MultipartFile speedFile ,  String date) throws IOException {
		ResponseData response = new ResponseData();
		
		List<AnalysisResponse> dailyAnalysisResponse = new ArrayList<AnalysisResponse>();
		AnalysisResponse responsedata = new AnalysisResponse();
		
		//Speed File
		
		FileInputStream inputstream = (FileInputStream) speedFile.getInputStream();
		File currDir = new File(".");
		String path = currDir.getAbsolutePath();
		fileLocation = path.substring(0, path.length() - 1) + speedFile.getOriginalFilename();
		logger.info(fileLocation);

		List<TrackShortName> trackShortNames = getTrackShortName.gettrackShortName();

		if (fileLocation != null) {
			if (fileLocation.endsWith(".xlsx")) {
				dailySpeedFileReader.readAndSaveSpeedFile(inputstream, trackShortNames, date);
			}
		} else {
			response.setMessage("Not a valid excel file!");
			response.setStatus("Failed");
			response.setFileName(speedFile.getOriginalFilename());
			responsedata.setErrorResponse(response);
			dailyAnalysisResponse.add(responsedata);
			return new ResponseEntity<>(dailyAnalysisResponse, HttpStatus.BAD_REQUEST);
		}
		
		// Result File
		
		FileInputStream inputstream2 = (FileInputStream) resultFile.getInputStream();
		currDir = new File(".");
		path = currDir.getAbsolutePath();
		fileLocation = path.substring(0, path.length() - 1) + resultFile.getOriginalFilename();
		logger.info(fileLocation);

		if (fileLocation != null) {
			if (fileLocation.endsWith(".xlsx")) {
				
				if(dailyCardFile.readJExcel(inputstream2)) {
					logger.info("card file parsed successfully");
				}else {
					response.setStatus("Failed");
					response.setMessage("Something went wrong");
					response.setFileName(resultFile.getOriginalFilename());
					responsedata.setErrorResponse(response);
					dailyAnalysisResponse.add(responsedata);
					return new ResponseEntity<>(dailyAnalysisResponse, HttpStatus.BAD_REQUEST);
				}
			} else {
				response.setMessage("Not a valid excel file!");
				response.setStatus("Failed");
				response.setFileName(resultFile.getOriginalFilename());
				responsedata.setErrorResponse(response);
				dailyAnalysisResponse.add(responsedata);
				return new ResponseEntity<>(dailyAnalysisResponse, HttpStatus.BAD_REQUEST);
			}
		} else {
			response.setMessage("Not a valid excel file!");
			response.setStatus("Failed");
			response.setFileName(resultFile.getOriginalFilename());
			responsedata.setErrorResponse(response);
			dailyAnalysisResponse.add(responsedata);
			return new ResponseEntity<>(dailyAnalysisResponse, HttpStatus.BAD_REQUEST);
		}
		
		dailyAnalysisResponse = dailyHorseRaceAnalysis.analyzeHorseWithCondition();
		logger.info("Got the result for analysis " +  dailyAnalysisResponse.size());
		return new ResponseEntity<>(dailyAnalysisResponse, HttpStatus.OK);
	}
	
	
	
	
	
	
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
	
	
	@GetMapping(value = "getSavedConditions")
	public ResponseEntity<List<ConditionSaver>> getSavedCondition(){
		List<ConditionSaver> conditions = new ArrayList<ConditionSaver>();
		conditions = conditionHandeler.getAllConditions();
		return new ResponseEntity<>(conditions, HttpStatus.OK);
	}
	
	
	@GetMapping(value = "getCompleteDataAnalysis")
	public ResponseEntity<String> getCompleteDataAnalysis() {
		
		List<HorseDataResult> sheetHorseDetails = horseRaceAnalysisRepository.findAnalysisForHorse();
		String[] filterValues = {"course", "distanceFurlongs", "rank", "bfsp", "favorite", "cardNumber", "raceType", "speedPoint", "officialGoing", "classCaptured"};
		//String[] filterValues = {"rank", "bfsp", "favorite", "cardNumber", "raceType", "speedPoint", "officialGoing", "classCaptured"};
		//String[] filterValues = {"distanceFurlongs"};
		List<String> filters = new ArrayList<>(Arrays.asList(filterValues));
		for(int i = 0 ; i < filters.size(); i++) {
			logger.info("processing for :: " + filters.get(i));
			completeDataAnalysis.filterAndAnalysisData(sheetHorseDetails, filters.subList(i, filters.size()), "");
			logger.info("processing completed :: " + filters.get(i));
			dailyAnalysisRepository.saveAll(data_analysis);
			logger.info("data saved completed :: ");
			data_analysis = new ArrayList<>();
			logger.info("List Refreshed ");
			System.gc();
			logger.info("Garbage value collected");
		}
		return new ResponseEntity<>("Done", HttpStatus.OK);
	}

}
