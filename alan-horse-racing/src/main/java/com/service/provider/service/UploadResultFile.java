package com.service.provider.service;

import java.util.List;

import com.service.provider.model.ResponseData;
import com.service.provider.model.ResultFileEntity;
import com.service.provider.model.SpeedFileEntity;

public interface UploadResultFile {
	ResponseData uploadResultFileIntoDB(List<ResultFileEntity> allSheetData , String date);
	ResponseData uploadSpeedFileIntoDB(List<SpeedFileEntity> allSheetData );
}
