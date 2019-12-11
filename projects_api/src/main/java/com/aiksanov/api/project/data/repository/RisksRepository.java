package com.aiksanov.api.project.data.repository;

import com.aiksanov.api.project.data.entity.Risk;
import com.aiksanov.api.project.data.entity.pk.RiskPK;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RisksRepository extends CrudRepository<Risk, RiskPK> {
    List<Risk> findAllByProjectId(int projectId);
}
