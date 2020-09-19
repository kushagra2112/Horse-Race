package com.service.provider.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import com.service.provider.model.CardFileEntity;

public interface DailyCardFile {
	boolean readJExcel(FileInputStream file) throws FileNotFoundException;
}
