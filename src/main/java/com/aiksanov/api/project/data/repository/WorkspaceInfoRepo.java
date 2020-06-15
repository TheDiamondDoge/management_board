package com.aiksanov.api.project.data.repository;

import com.aiksanov.api.project.data.entity.WorkspaceInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkspaceInfoRepo extends CrudRepository<WorkspaceInfo, Integer> {
}
