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
import org.springframework.stereotype.Service;

import com.service.provider.model.ResultFileEntity;


@Service
public class ResultFileReaderImp implements ResultFileReader {

	private static final Logger logger = LogManager.getLogger();

	public String[] headers = { "raceDate", "raceTime", "track", "raceName", "raceRestrictionsAge", "raceClass",
			"major", "raceDistance", "prizeMoney", "goingDescription", "numberOfRunners", "place", "distbt",
			"horseName", "stall", "trainer", "horseAge", "jockeyName", "jockeysClaim", "pounds", "odds", "fav",
			"officialRating", "comptime", "comtimeNumeric", "totalDstBt", "medianOr", "distFurlongs",
			"placingNumerical", "rCode", "bfsp", "bfspPlace", "plcsPaid", "bfPlcsPaid", "yards", "railMove", "raceType",
			"horseComment", "cardNo", "stallPositioning", "trackDirection", "headgear" };

	public List<String> headerList = new ArrayList<>(Arrays.asList(headers));

	@Override
	public List<ResultFileEntity> readJExcel(FileInputStream file)
			throws FileNotFoundException {

		Workbook workbook;
		List<ResultFileEntity> resultFile = new ArrayList<ResultFileEntity>();
		try {
			workbook = new XSSFWorkbook(file);

			Sheet sheet = workbook.getSheetAt(0);
			int i = 0;
			for (Row row : sheet) {
				if (i == 0) {
					i++;
					continue;
				} else {
					if (row.getCell(1).getDateCellValue() + "" == "") {
						break;
					}
					resultFile.add(getRowDataJson(row));
				}
			}

			workbook.close();

		} catch (IOException e1) {
			logger.error(e1.getMessage());
		}

		return resultFile;

	}

	public ResultFileEntity getRowDataJson(Row row) {

		ResultFileEntity resultObject = new ResultFileEntity();
		
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

		resultObject.setPlacingNumerical(resultObject.getPlacingNumerical().replaceAll("RR", "0").replaceAll("RO", "0")
				.replaceAll("CO", "0").replaceAll("Non Runner", "0").replaceAll("DSQ", "0"));
		resultObject.setPlacingNumerical(
				resultObject.getPlacingNumerical().toUpperCase().replaceAll("PU", "0").replaceAll("F", "0")
						.replaceAll("UR", "0").replaceAll("SU", "0").replaceAll("BD", "0").replaceAll(".0", ""));

		try {
			resultObject.setPlacingNumerical(Integer.parseInt(resultObject.getPlacingNumerical()) + "");
		} catch (Exception e) {
			resultObject.setPlacingNumerical("0");
		}

		if (resultObject.getBfsp() == "" || resultObject.getBfsp() == "0") {
			resultObject.setBfsp("0.0");
		}
		if (resultObject.getBfspPlace() == "" || resultObject.getBfspPlace() == "0") {
			resultObject.setBfspPlace("0.0");
		}
		if (resultObject.getPlcsPaid() == "" || resultObject.getPlcsPaid() == "0") {
			resultObject.setPlcsPaid("0.0");
		}
		if (resultObject.getBfPlcsPaid() == "" || resultObject.getBfPlcsPaid() == "0") {
			resultObject.setBfPlcsPaid("0.0");
		}
		if (resultObject.getHorseAge() == "" || resultObject.getHorseAge() == null) {
			resultObject.setHorseAge("0");
		}

		if (resultObject.getCardNo() == "" || resultObject.getCardNo() == null) {
			resultObject.setCardNo("0");
		}
		
		if(resultObject.getFav() == null || resultObject.getFav() == "" ) {
			resultObject.setFav("");
		}
		
		return resultObject;
		
	}

}
