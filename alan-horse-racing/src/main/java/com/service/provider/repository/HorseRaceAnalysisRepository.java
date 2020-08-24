package com.service.provider.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.service.provider.model.HorseDataResult;

public interface HorseRaceAnalysisRepository extends JpaRepository<HorseDataResult, Long>{
	
	@Query(value = "select " +
			"d.entityId as entityId, "+
			"d.horse_rank as horse_rank, "+
			"d.raceDate  as raceDate, "+
			"d.raceTime as raceDateTime, " + 
			"d.horseName as horse, " + 
			"d.placingNumerical as placingNumerical," + 
			"d.track as course," + 
			"d.speed_figure as speedFigure," + 
			"d.speed_point as speedPoint,  " + 
			"d.days_since_run as daySinceRun, " + 
			"d.distFurlongs as raceDistance," + 
			"d.goingDescription as officialGoing, " + 
			"d.raceClass as classCaptured, " + 
			"'' as surface, " + 
			"d.track as sheetName, " + 
			"d.track as todaysRaceTrack, " + 
			"d.horseAge as horseAge, " + 
			"d.distFurlongs as distFurlongs, " +
			"d.finishing_position as finishingPosition, " + 
			"d.placingNumerical as placingNumerical, " + 
			"d.bfsp as bfsp, " + 
			"d.bfPlcsPaid as bfPlcsPaid, " + 
			"d.bfspPlace as bfspPlace, " + 
			"d.plcsPaid as plcsPaid, " + 
			"d.trackDirection as trackDirection, " +
			"d.cardNo as cardNumber, " +
			"d.raceType as raceType, " +
			"d.fav as fav " +
			"from ( " + 
			"select b.speed_figure, b.speed_point,b.days_since_run, b.finishing_position, b.horse_rank, " +
			" a.* from result_file_new a " + 
			"join speed_figures b on " + 
			"a.raceDate = b.speed_file_date and "+ 
			"a.raceTime = b.race_timing and " + 
			"a.horseName = b.horse and " + 
			"a.track = b.todays_race_track " + 
			"where a.placingNumerical <> 0 " + 
			"order by b.speed_figure desc ) d "
			+ "order by d.speed_figure desc;", nativeQuery = true)
	List<HorseDataResult> findAnalysisForHorse();
}
