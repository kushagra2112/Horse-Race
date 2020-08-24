package com.service.provider.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.service.provider.exceptions.InvalidValueException;
import com.service.provider.model.SpeedFileEntity;
import com.service.provider.model.TrackShortName;
import com.service.provider.repository.SpeedFileRepository;

@Service
public class SpeedFileReaderImp implements SpeedFileReader {

	private static final Logger logger = LogManager.getLogger();
	public List<String> headerList = new ArrayList<>();
	public List<TrackShortName> trackShortName = new ArrayList<>();

	@Autowired
	SpeedFileRepository speedFileRepository;

	// public Date todaysDate = new Date();
	public Date todaysDate = new Date();

	public String[] columnHeader = { "entityid", "horse", "speedfigure", "course", "finishingposition",
			"numberofrunners", "distancebeat", "dayssincerun", "dayssinceprevrun", "racedistance", "officialgoing",
			"goingallowance", "description", "draw", "drawntoday", "classcaptured", "surface", "handicaprating",
			"trainer", "circuittime", "notedperformance", "typeofrace", "todaysracetrack", "racetiming", "sheetname",
			"previousrundate" };

	public List<String> sqlHeaderList = new ArrayList<>(Arrays.asList(columnHeader));

	long horseRank = 1;

	@Override
	public void readAndSaveSpeedFile(FileInputStream file, List<TrackShortName> trackShortNames, String date)
			throws FileNotFoundException {

		try {
			if (date != null || date != "") {
				todaysDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
			}
		} catch (ParseException e) {
			logger.error(e.getMessage());
		}

		try {
			// String dateString = new SimpleDateFormat("yyyy-MM-dd").format(todaysDate);
			int deletedCount = speedFileRepository.deleteBySheetfiledate(todaysDate);
			logger.info("Old count deleted = " + deletedCount);

		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		Workbook workbook = null;

		this.trackShortName = trackShortNames;
		String dataSheetName = "";
		int i = 0;

		try {
			workbook = new XSSFWorkbook(file);
			for (int sheetNumber = 0; sheetNumber < workbook.getNumberOfSheets(); sheetNumber++) {

				Sheet sheet = workbook.getSheetAt(sheetNumber);
				horseRank = 1;
				logger.info("Reading sheet number = " + sheetNumber + " :: " + sheet.getSheetName());
				i = 0;
				dataSheetName = sheet.getSheetName().toLowerCase().replaceAll("-", "");
				dataSheetName = dataSheetName.substring(0, dataSheetName.length() - 2) + "-"
						+ dataSheetName.substring(dataSheetName.length() - 2);

				String sheetName;
				String raceTime;

				if (dataSheetName.toLowerCase().startsWith("ayr")) {
					sheetName = dataSheetName.substring(0, 3);
					raceTime = dataSheetName.substring(3, dataSheetName.length());
				} else {
					sheetName = dataSheetName.substring(0, 4);
					raceTime = dataSheetName.substring(4, dataSheetName.length());
				}

				if (raceTime.length() == 4) {
					raceTime = Integer.toString(Integer.parseInt(raceTime.split("-")[0]) + 12) + "-"
							+ raceTime.split("-")[1];
				}
				Row secondRow = sheet.getRow(1);

				for (int rowNumber = 2; rowNumber < sheet.getLastRowNum(); rowNumber++) {
					if (sheet.getRow(rowNumber) != null && sheet.getRow(rowNumber).getCell(1) != null
							&& sheet.getRow(rowNumber).getCell(1).getNumericCellValue() + "" != "") {
						try {
							if (!secondRow.getCell(0).getRichStringCellValue().getString().equalsIgnoreCase(
									sheet.getRow(rowNumber).getCell(0).getRichStringCellValue().getString())) {
								secondRow = sheet.getRow(rowNumber);
								break;
							}
						} catch (Exception e) {
							throw new InvalidValueException(
									"Error while parsing the sheet :: " + dataSheetName + " :: for speed points");
						}
					}
				}

				for (Row row : sheet) {
					try {
						if (!(row.getCell(0) != null && row.getCell(0).getRichStringCellValue().getString() != null
								&& row.getCell(0).getRichStringCellValue().getString() + "" != "")) {
							break;
						}
					} catch (Exception e) {

						try {
							workbook.close();
						} catch (IOException e1) {

							logger.info(e1.getMessage() + "");
						}

						logger.info("Complete data saved");

						throw new InvalidValueException(
								"Something Went wrong while fetching the details from speed file");
					}

					if (i == 0) {
						getHeader(row, dataSheetName);
					} else if (i == 1 && (sheet.getRow(2) != null && sheet.getRow(2).getCell(1) != null
							&& sheet.getRow(2).getCell(1).getNumericCellValue() + "" != "")) {
						getRowDataJson(secondRow, row, sheetName, raceTime, dataSheetName, i);
					} else {

						getRowDataJson(row, row, sheetName, raceTime, dataSheetName, i);

					}

					i++;

				}
			}
		} catch (IOException e) {
			throw new InvalidValueException("Error while reading the file details");
		} catch (Exception e1) {
			throw new InvalidValueException(
					"Incorrent value in sheet :: " + dataSheetName + " :: row number " + (i + 1));
		} finally {
			try {
				workbook.close();
			} catch (IOException e) {

				logger.info(e.getMessage() + "");
			}
		}

	}

	private void getHeader(Row row, String dataSheetName) {

		headerList = new ArrayList<String>();

		for (Cell cell : row) {
			try {
				String cellHeader = cell.getRichStringCellValue().getString().trim().replaceAll(" ", "")
						.replaceAll("_", "").toLowerCase().replace("class", "classcaptured");

				headerList.add(cellHeader);

			} catch (Exception e) {
				throw new InvalidValueException("invalid Header value in sheet :: " + dataSheetName);
			}

		}

		logger.info("total number of columns found = " + headerList.size());
	}

	public void getRowDataJson(Row row2, Row row, String sheetName, String raceTime, String dataSheetName,
			int rowNumber) {

		SpeedFileEntity speedObject = new SpeedFileEntity();
		speedObject.setSheetfiledate(todaysDate);

		int index = 0;
		Iterator<Cell> it = row.cellIterator();
//		for (Cell cell : row) {
		try {
			while (it.hasNext()) {
				Cell cell = it.next();
				// logger.info(cell.getAddress().getColumn());
				index = cell.getAddress().getColumn();

				// logger.info(index + " - " + headerList.get(index));
				if (sqlHeaderList.contains(headerList.get(index))) {
					switch (cell.getCellType()) {

					case STRING:
						if (headerList.get(index).equalsIgnoreCase("description")) {
							BeanUtils.setProperty(speedObject, headerList.get(index),
									(cell.getRichStringCellValue().getString().length() > 350
											? cell.getRichStringCellValue().getString().substring(0, 350)
											: cell.getRichStringCellValue().getString()));

						} else {
							BeanUtils.setProperty(speedObject, headerList.get(index),
									(cell.getRichStringCellValue().getString().length() > 100
											? cell.getRichStringCellValue().getString().substring(0, 100)
											: cell.getRichStringCellValue().getString()));
						}
						break;
					case NUMERIC:
						if (DateUtil.isCellDateFormatted(cell)) {
							BeanUtils.setProperty(speedObject, headerList.get(index), cell.getDateCellValue());
						} else {

							if (headerList.get(index).equalsIgnoreCase("speedfigure")) {
								BeanUtils.setProperty(speedObject, headerList.get(index), cell.getNumericCellValue());
							} else {
								BeanUtils.setProperty(speedObject, headerList.get(index),
										cell.getNumericCellValue() + "");
							}
						}
						break;
					case BOOLEAN:
						BeanUtils.setProperty(speedObject, headerList.get(index), cell.getBooleanCellValue() + "");
						break;
					case FORMULA:
						BeanUtils.setProperty(speedObject, headerList.get(index), cell.getCellFormula() + "");
						break;
					default:
						if (headerList.get(index).equalsIgnoreCase("description")) {
							BeanUtils.setProperty(speedObject, headerList.get(index),
									(cell.getRichStringCellValue().getString().length() > 350
											? cell.getRichStringCellValue().getString().substring(0, 350)
											: cell.getRichStringCellValue().getString()));

						} else {
							BeanUtils.setProperty(speedObject, headerList.get(index),
									(cell.getRichStringCellValue().getString().length() > 100
											? cell.getRichStringCellValue().getString().substring(0, 100)
											: cell.getRichStringCellValue().getString()));
						}

					}

				}

			}
		} catch (Exception e) {
			throw new InvalidValueException("Incorrent value in sheet :: " + dataSheetName + " :: row number "
					+ rowNumber + " ::  for " + headerList.get(index));
		}

		try {
			List<String> trackFullName = trackShortName.stream()
					.filter(d -> d.getTrackShortName().equalsIgnoreCase(sheetName)).map(TrackShortName::getTrackName)
					.collect(Collectors.toList());

			Date daysAgo = new DateTime(todaysDate)
					.minusDays((Integer.parseInt(speedObject.getDayssincerun().trim().replace(".0", "")) - 1)).toDate();

			// Date previousRunDate = DateUtils.addDays(new Date() ,
			// (Integer.parseInt(speedObject.getDayssincerun()) +1 )*(-1)) ;

			BeanUtils.setProperty(speedObject, "todaysracetrack",
					(trackFullName.size() > 0) ? trackFullName.get(0) : sheetName);

			String raceDateAndTime = "";
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			raceDateAndTime = dateFormat.format(todaysDate) + " " + raceTime;
			speedObject.setRacetiming(new SimpleDateFormat("yyyy-MM-dd HH-mm").parse(raceDateAndTime));
			speedObject.setRank(horseRank);
			BeanUtils.setProperty(speedObject, "sheetname", (sheetName + raceTime));
			BeanUtils.setProperty(speedObject, "previousrundate", daysAgo);
			
			if (rowNumber == 1) {
				try {
					speedObject.setSpeedpoint(
							(int) row.getCell(1).getNumericCellValue() - (int) row2.getCell(1).getNumericCellValue());
				} catch (Exception e) {
					throw new InvalidValueException(
							"Error while parsing the sheet :: " + dataSheetName + " :: for speed points");
				}
			}

		} catch (IllegalAccessException | InvocationTargetException e) {

			throw new InvalidValueException(
					"Occured an IllegalAccessException | InvocationTargetException while parsing the sheet :: "
							+ dataSheetName + " :: row number " + rowNumber);
		} catch (Exception e) {
			throw new InvalidValueException(
					"Error while parsing the sheet :: " + dataSheetName + " :: row number " + rowNumber);
		}

		try {
			speedFileRepository.saveAndFlush(speedObject);
			horseRank += 1;
		} catch (DataIntegrityViolationException e) {
			if (!e.getCause().toString().startsWith("org.hibernate.exception.ConstraintViolationException")) {
				throw new InvalidValueException("error while saving the value for sheet :: " + dataSheetName
						+ " :: row number " + rowNumber + " :: " + e.getMessage());
			}
		} catch (Exception e) {
			throw new InvalidValueException("error while saving the value for sheet :: " + dataSheetName
					+ " :: row number " + rowNumber + " :: " + e.getMessage());
		}

	}

}
