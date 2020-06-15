package com.aiksanov.api.project.data.repository;

import com.aiksanov.api.project.data.entity.Cost;
import com.aiksanov.api.project.data.entity.pk.CostPK;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CostRepository extends CrudRepository<Cost, CostPK> {
    List<Cost> findAllByProjectId(int projectId);
}
