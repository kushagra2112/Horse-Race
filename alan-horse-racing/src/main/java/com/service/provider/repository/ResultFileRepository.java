package com.service.provider.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.service.provider.model.ResultFileEntity;


@Repository
@Transactional
public interface ResultFileRepository extends JpaRepository<ResultFileEntity, Long>{
	@Modifying
	int deleteByRaceDate(Date dateValue);
	ResultFileEntity findByRaceDate(Date raceDate);
}
