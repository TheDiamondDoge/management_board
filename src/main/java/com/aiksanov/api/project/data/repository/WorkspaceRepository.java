package com.aiksanov.api.project.data.repository;

import com.aiksanov.api.project.data.entity.WorkspaceInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkspaceRepository extends CrudRepository<WorkspaceInfo, Integer> {
    List<WorkspaceInfo> findAllByStatus(String status);
}
