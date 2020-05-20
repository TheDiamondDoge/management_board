package com.aiksanov.api.project.data.repository;

import com.aiksanov.api.project.data.entity.Milestone;
import com.aiksanov.api.project.data.entity.pk.MilestonePK;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MilestoneRepository extends CrudRepository<Milestone, MilestonePK> {
    String maxMilestoneActualDate =
            "SELECT * FROM prj_milestones " +
                    "WHERE project_id IN (?1) AND actual_date IS NOT NULL ORDER BY actual_date DESC LIMIT 1";

    String minMilestoneActualDate =
            "SELECT * FROM prj_milestones " +
                    "WHERE project_id IN (?1) AND actual_date IS NOT NULL ORDER BY actual_date ASC LIMIT 1";

    String keyDrQuery = "SELECT * FROM prj_milestones " +
            "WHERE project_id = ?1 AND label IN ('DR0', 'DR1', 'DR4', 'DR5') ORDER BY label DESC";

    @Query(value = maxMilestoneActualDate, nativeQuery = true)
    Optional<Milestone> highestActualDate(List<Integer> ids);

    @Query(value = minMilestoneActualDate, nativeQuery = true)
    Optional<Milestone> lowestActualDate(List<Integer> ids);

    @Query(value = keyDrQuery, nativeQuery = true)
    List<Milestone> findSortedKeyDrMilestonesByProjectId(int projectId);

    List<Milestone> findAllByMilestonePK_ProjectIDOrderByActualDateAsc(Integer projectId);
    List<Milestone> findAllByMilestonePK_ProjectIDAndShown(Integer projectId, boolean shown);
    void deleteAllByMilestonePK(List<MilestonePK> pk);
    void deleteAllByMilestonePK_ProjectID(Integer projectID);
}
