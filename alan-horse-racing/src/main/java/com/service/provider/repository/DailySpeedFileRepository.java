package com.service.provider.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.service.provider.model.DailySpeedFileEntity;

@Repository
@Transactional
public interface DailySpeedFileRepository  extends JpaRepository<DailySpeedFileEntity, Long>{
	@Modifying
	int deleteBySheetfiledate(Date dateValue);

}
