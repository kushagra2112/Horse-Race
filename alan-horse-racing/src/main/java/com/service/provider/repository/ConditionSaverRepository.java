package com.service.provider.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.service.provider.model.ConditionSaver;

@Repository
@Transactional
public interface ConditionSaverRepository extends JpaRepository<ConditionSaver, Long> {
	ConditionSaver findByConditionName(String conditionName);
	ConditionSaver findByConditionValue(String conditionValue);
}
