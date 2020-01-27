package com.aiksanov.api.project.data.repository;

import com.aiksanov.api.project.data.entity.ActionRelatedRisks;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActionRelatedRisksRepo extends CrudRepository<ActionRelatedRisks, Integer> {

}