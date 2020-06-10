package com.aiksanov.api.project.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@Entity
@Table(name = "prj_status_report")
public class StatusReport {
    @Id
    @Column(name = "project_id")
    private Integer projectId;

    @Column(name = "executive_summary")
    private String executiveSummary;

    @Column(name = "red_flag")
    private String redFlag;

    @Column(name = "orange_flag")
    private String orangeFlag;

    @Column(name = "green_flag")
    private String greenFlag;

    @Column(name = "details")
    private String details;
}