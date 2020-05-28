package com.aiksanov.api.project.data.repository;

import com.aiksanov.api.project.data.entity.StatusReportSnapshotInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportSnapshotInfoRepository extends CrudRepository<StatusReportSnapshotInfo, Integer> {
    String snapshotInfo = "SELECT * FROM prj_status_report_snapshots_info " +
            "WHERE project_id = ?1 ORDER BY created_on DESC LIMIT 6";

    @Query(value = snapshotInfo, nativeQuery = true)
    List<StatusReportSnapshotInfo> getLastSnapshotsInfoByProjectId(int projectId);
}
