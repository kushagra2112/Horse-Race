package com.service.provider.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.provider.model.CardFileEntity;
import com.service.provider.repository.DailyCardFileRepositpry;
@Service
public class DailyCardFileImp implements DailyCardFile {
	
	@Autowired
	DailyCardFileRepositpry dailyCardFileRepositpry;
	
	
	private static final Logger logger = LogManager.getLogger();
	public String[] headers = {"raceDate","raceTime","track","raceName","raceDistance","distFurlongs",
			"raceClass","goingDescription","prizeMoney","numberOfRunners","trackDirection","cardNo","horseName",
			"horseAge","jockeyName","jockeysClaim","trainer","stallPositioning","officialRating","weightpounds",
			"bfsp","form","days","headgear","stallion","dam","horsetype","cd","fav"};

	public String[] raceTypesArray = {"Handicap Novices Chase","Handicap Novices Hurdle","Maiden NH Flat",
			"Handicap Seller Hurdle","Maiden Hunters Chase","Novices Seller Hurdle","Handicap Seller Chase",
			"Novices Hunters Chase","Handicap Hunters Chase","Handicap Nursery","Handicap Hurdle",
			"Novices Hurdle","Novices Chase","Handicap Chase","NH Flat","Beginners Chase","Handicap Claimer",
			"Maiden Hurdle","Claimer Chase","Seller Hurdle","Maiden Claimer","Hunters Chase","Handicap Seller",
			"Claimer Hurdle","Handicap Maiden","Handicap","Maiden","Non-Handicap","Novices","Hurdle","Seller",
			"Chase","Claimer", "Novice" , "Selling"};
	
	public List<String> headerList = new ArrayList<>(Arrays.asList(headers));
	public List<String> raceTypeList =  new ArrayList<>(Arrays.asList(raceTypesArray));

	@Override
	public boolean readJExcel(FileInputStream file)
			throws FileNotFoundException {

		Workbook workbook;
		CardFileEntity resultFile = new CardFileEntity();
		try {
			workbook = new XSSFWorkbook(file);
			dailyCardFileRepositpry.deleteAll();
			logger.info("Card File old data deleted");
			
			Sheet sheet = workbook.getSheetAt(0);
			int i = 0;
			try {
				for (Row row : sheet) {
					if (i == 0) {
						i++;
						continue;
					} else {
						if (row.getCell(1).getDateCellValue() + "" == "") {
							break;
						}
						resultFile = getRowDataJson(row);
						dailyCardFileRepositpry.saveAndFlush(resultFile);
						i++;
					}
				}
			}catch(Exception e) {
				logger.info("Card File :: Something wrong in line number " + (i+1));
				return false;
			}finally {
				workbook.close();
			}
		} catch (IOException e1) {
			logger.error(e1.getMessage());
		}
		return true;
	}

	public CardFileEntity getRowDataJson(Row row) {

		CardFileEntity resultObject = new CardFileEntity();
		
		String raceDateAndTime = "";

		int index = 0;
		Iterator<Cell> it = row.cellIterator();
//		for (Cell cell : row) {
		while (it.hasNext()) {
			Cell cell = it.next();

			index = cell.getAddress().getColumn();
			try {

				switch (cell.getCellType()) {
				case STRING:
					if (headerList.get(index).equalsIgnoreCase("raceName")
							|| headerList.get(index).equalsIgnoreCase("goingDescription")
							|| headerList.get(index).equalsIgnoreCase("horseName")
							|| headerList.get(index).equalsIgnoreCase("horseComment")) {
						BeanUtils.setProperty(resultObject, headerList.get(index),
								(cell.getRichStringCellValue().getString().length() > 300
										? cell.getRichStringCellValue().getString().substring(0, 300)
										: cell.getRichStringCellValue().getString()));

					} else {
						BeanUtils.setProperty(resultObject, headerList.get(index),
								(cell.getRichStringCellValue().getString().length() > 45
										? cell.getRichStringCellValue().getString().substring(0, 45)
										: cell.getRichStringCellValue().getString()));
					}
					break;
				case NUMERIC:
					if (DateUtil.isCellDateFormatted(cell)) {
						
						if(index ==0) {
							DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
							raceDateAndTime = dateFormat.format(cell.getDateCellValue());
							resultObject.setRaceDate(new SimpleDateFormat("yyyy-MM-dd").parse(raceDateAndTime));
							
						}else if(index == 1) {
							DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
							raceDateAndTime = raceDateAndTime + " " + dateFormat.format(cell.getDateCellValue());  
							resultObject.setRaceTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(raceDateAndTime));
						}else {
							BeanUtils.setProperty(resultObject, headerList.get(index), cell.getDateCellValue());	
						}
					} else {
						BeanUtils.setProperty(resultObject, headerList.get(index), cell.getNumericCellValue() + "");
					}
					break;
				case BOOLEAN:
					BeanUtils.setProperty(resultObject, headerList.get(index), cell.getBooleanCellValue() + "");

					break;
				case FORMULA:
					BeanUtils.setProperty(resultObject, headerList.get(index), cell.getCellFormula() + "");

					break;
				default:
					if (headerList.get(index).equalsIgnoreCase("raceName")
							|| headerList.get(index).equalsIgnoreCase("goingDescription")
							|| headerList.get(index).equalsIgnoreCase("horseName")
							|| headerList.get(index).equalsIgnoreCase("horseComment")) {
						BeanUtils.setProperty(resultObject, headerList.get(index),
								(cell.getRichStringCellValue().getString().length() > 300
										? cell.getRichStringCellValue().getString().substring(0, 300)
										: cell.getRichStringCellValue().getString()));

					} else {
						BeanUtils.setProperty(resultObject, headerList.get(index),
								(cell.getRichStringCellValue().getString().length() > 45
										? cell.getRichStringCellValue().getString().substring(0, 45)
										: cell.getRichStringCellValue().getString()));
					}

				}
			} catch (Exception e) {

				try {
					BeanUtils.setProperty(resultObject, headerList.get(index), "");
				} catch (IllegalAccessException | InvocationTargetException e1) {
					logger.error(e1.getMessage());
				}
				logger.error(e.getMessage());
			}
		}

		resultObject.setTrack((resultObject.getTrack() != null) ? resultObject.getTrack().trim().toLowerCase() : "");
		resultObject.setHorseName(
				(resultObject.getHorseName() != null) ? resultObject.getHorseName().split("[(]")[0].trim() : "");

		if (resultObject.getBfsp() == "" || resultObject.getBfsp() == "0") {
			resultObject.setBfsp("0.0");
		}
		
		if (resultObject.getHorseAge() == "" || resultObject.getHorseAge() == null) {
			resultObject.setHorseAge("0");
		}

		if (resultObject.getCardNo() == "" || resultObject.getCardNo() == null) {
			resultObject.setCardNo("0");
		}
		
		if(resultObject.getFav() == null) {
			resultObject.setFav("");
		}
		
		if(resultObject.getRaceClass() == null) {
			resultObject.setRaceClass("");
		}
		
		for(String raceType :  raceTypeList) {
			if(resultObject.getRaceName().toLowerCase().indexOf(raceType.toLowerCase()) >0) {
				if(raceType.equalsIgnoreCase("novice")) {
					resultObject.setRaceName("Novices");
				}else if(raceType.equalsIgnoreCase("selling")) {
					resultObject.setRaceName("Seller");
				}else {
					resultObject.setRaceName(raceType);
				}
				break;
			}
		}
		
		return resultObject;
		
	}

}
