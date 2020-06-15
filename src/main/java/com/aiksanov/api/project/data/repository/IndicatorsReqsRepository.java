package com.aiksanov.api.project.data.repository;

import com.aiksanov.api.project.data.entity.IndicatorsReqs;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndicatorsReqsRepository extends CrudRepository<IndicatorsReqs, Integer> {
}
