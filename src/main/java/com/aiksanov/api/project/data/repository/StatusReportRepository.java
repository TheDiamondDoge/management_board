package com.aiksanov.api.project.data.repository;

import com.aiksanov.api.project.data.entity.StatusReport;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StatusReportRepository extends CrudRepository<StatusReport, Integer> {
}
