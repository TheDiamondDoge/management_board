package com.aiksanov.api.project.data.repository;

import com.aiksanov.api.project.data.entity.EcmaBacklogTarget;
import com.aiksanov.api.project.data.entity.pk.EcmaBacklogTargetPK;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EcmaBacklogTargetRepo extends CrudRepository<EcmaBacklogTarget, EcmaBacklogTargetPK> {
    List<EcmaBacklogTarget> findAllByProjectId(int projectId);
    void deleteAllByProjectId(int projectID);
}
