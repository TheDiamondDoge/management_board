package com.aiksanov.api.project.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "prj_status_report_snapshots_info")
public class StatusReportSnapshotInfo {
    @Id
    private int id;
    private int projectId;
    private Date createdOn;
    private String createdBy;
}
