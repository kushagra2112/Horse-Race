package com.service.provider.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.service.provider.model.TrackShortName;

@Repository
public interface TrackShortNameRepository extends JpaRepository<TrackShortName, Long> {

}
