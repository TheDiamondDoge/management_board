package com.aiksanov.api.project.data.repository;

import com.aiksanov.api.project.data.entity.Milestone;
import com.aiksanov.api.project.data.entity.pk.MilestonePK;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MilestoneRepository extends CrudRepository<Milestone, MilestonePK> {
    List<Milestone> findAllByMilestonePK_ProjectID(Integer projectId);
    List<Milestone> findAllByMilestonePK_ProjectIDAndShown(Integer projectId, boolean shown);
    void deleteAllByMilestonePK(List<MilestonePK> pk);
    void deleteAllByMilestonePK_ProjectID(Integer projectID);
}
