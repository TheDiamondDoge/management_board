package com.aiksanov.api.project.data.repository;

import com.aiksanov.api.project.data.entity.StatusReportSnapshot;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ReportSnapshotRepository extends CrudRepository<StatusReportSnapshot, Integer> {
    Optional<StatusReportSnapshot> findById(int id);
}
