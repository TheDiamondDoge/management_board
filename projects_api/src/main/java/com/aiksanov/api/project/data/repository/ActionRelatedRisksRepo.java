package com.aiksanov.api.project.data.repository;

import com.aiksanov.api.project.data.entity.ActionRelatedRisks;
import com.aiksanov.api.project.data.entity.pk.ActionRelatedRisksPK;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActionRelatedRisksRepo extends CrudRepository<ActionRelatedRisks, ActionRelatedRisksPK> {
    void deleteAllByActionId(int actionId);
    List<ActionRelatedRisks> findByActionId(int actionId);
}