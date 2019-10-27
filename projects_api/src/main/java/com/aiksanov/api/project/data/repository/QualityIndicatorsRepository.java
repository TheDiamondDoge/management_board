package com.aiksanov.api.project.data.repository;

import com.aiksanov.api.project.data.entity.QualityIndicators;
import com.aiksanov.api.project.data.entity.pk.QualityIndicatorsPK;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QualityIndicatorsRepository extends CrudRepository<QualityIndicators, QualityIndicatorsPK> {
    List<QualityIndicators> getAllByProjectIDAndKpiID(int projectID, String kpiID);
    void deleteAllByProjectIDAndKpiID(int projectID, String kpiID);
}
