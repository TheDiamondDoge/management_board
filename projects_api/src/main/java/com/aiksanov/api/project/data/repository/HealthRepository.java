package com.aiksanov.api.project.data.repository;

import com.aiksanov.api.project.data.entity.HealthIndicators;
import com.aiksanov.api.project.data.entity.HealthIndicatorsPK;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HealthRepository extends CrudRepository<HealthIndicators, HealthIndicatorsPK>{
    String indicatorsByProjectAndLabel = "SELECT * FROM prj_indicators_health " +
            "WHERE project_id = ?1 AND label = ?2 ORDER BY modification_date DESC LIMIT 2";

    @Query(value = indicatorsByProjectAndLabel, nativeQuery = true)
    List<HealthIndicators> findAllByHealthIndicatorsPK(Integer projectID, String label);
}
