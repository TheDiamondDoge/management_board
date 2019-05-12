package com.aiksanov.api.project.data.repository;

import com.aiksanov.api.project.data.entity.Milestone;
import com.aiksanov.api.project.data.entity.MilestonePK;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.awt.*;
import java.util.List;
import java.util.Optional;

@Repository
public interface MilestoneRepository extends CrudRepository<Milestone, MilestonePK> {
    List<Milestone> findAllByMilestonePK_ProjectID(Integer projectId);
    void deleteAllByMilestonePK(List<MilestonePK> pk);
}
