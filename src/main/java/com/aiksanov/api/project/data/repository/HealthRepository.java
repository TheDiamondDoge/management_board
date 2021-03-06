package com.aiksanov.api.project.data.repository;

import com.aiksanov.api.project.data.entity.HealthIndicators;
import com.aiksanov.api.project.data.entity.pk.HealthIndicatorsPK;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HealthRepository extends CrudRepository<HealthIndicators, HealthIndicatorsPK> {
    String indicatorsByProjectAndLabel = "SELECT * FROM prj_indicators_health " +
            "WHERE project_id = ?1 ORDER BY modification_date DESC LIMIT 2";

    String lastIndicatorById = "SELECT * FROM prj_indicators_health " +
            "WHERE project_id = ?1 ORDER BY modification_date DESC LIMIT 1";

    @Query(value = indicatorsByProjectAndLabel, nativeQuery = true)
    List<HealthIndicators> lastTwoHealthStates(Integer projectID);

    @Query(value = lastIndicatorById, nativeQuery = true)
    HealthIndicators lastHealthState(int projectID);
}
