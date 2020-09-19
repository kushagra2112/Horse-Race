package com.service.provider.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.service.provider.model.DailyAnalysisResultReport;

@Repository
@Transactional
public interface DailyAnalysisRepository extends JpaRepository<DailyAnalysisResultReport, Long>{

}
