package com.service.provider.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.service.provider.model.CardFileEntity;

@Repository
@Transactional
public interface DailyCardFileRepositpry  extends JpaRepository<CardFileEntity, Long>{
	
	@Modifying
	int deleteByRaceDate(Date dateValue);
	CardFileEntity findByRaceDate(Date raceDate);

}
