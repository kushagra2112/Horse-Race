package com.service.provider.repository;


import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.service.provider.model.SpeedFileEntity;

@Repository
@Transactional
public interface SpeedFileRepository extends JpaRepository<SpeedFileEntity, Long> {
	
	//@Query("delete from speed_figures where speed_file_date=:dateValue")
	@Modifying
	int deleteBySheetfiledate(Date dateValue);

}
