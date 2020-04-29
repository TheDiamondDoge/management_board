package com.aiksanov.api.project.data.repository;

import com.aiksanov.api.project.data.entity.StatusReportSnapshot;
import com.aiksanov.api.project.data.entity.pk.StatusReportPK;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ReportSnapshotRepository extends CrudRepository<StatusReportSnapshot, StatusReportPK> {
    String statusByProjectId = "SELECT * FROM prj_status_report_snapshots " +
            "WHERE project_id = ?1 ORDER BY report_id DESC LIMIT 1";

    @Query(value = statusByProjectId, nativeQuery = true)
    Optional<StatusReportSnapshot> getLastSnapshotByProjectId(int projectId);

    Optional<List<StatusReportSnapshot>> findAllByPk_ProjectId(int projectId);
}
