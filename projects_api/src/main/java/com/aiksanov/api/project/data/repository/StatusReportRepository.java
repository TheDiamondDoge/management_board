package com.aiksanov.api.project.data.repository;

import com.aiksanov.api.project.data.entity.StatusReport;
import com.aiksanov.api.project.data.entity.pk.StatusReportPK;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StatusReportRepository extends CrudRepository<StatusReport, StatusReportPK> {
    String statusByProjectIdAndLastTimestamp = "SELECT * FROM prj_status_report " +
            "WHERE project_id = ?1 ORDER BY report_id DESC LIMIT 1";

    @Query(value = statusByProjectIdAndLastTimestamp, nativeQuery = true)
    Optional<StatusReport> getLastReportByProjectId(int projectId);

    Optional<List<StatusReport>> findAllByPk_ProjectId(int projectId);
}
