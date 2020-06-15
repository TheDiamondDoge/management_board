package com.aiksanov.api.project.data.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@Entity
@Table(name = "prj_jira_params")
public class JiraParams {
    @Id
    @Column(name = "project_id")
    private int projectID;

    @Column(name = "metrics_scope")
    private String metricsScope;

    @Column(name ="rq_release")
    private String rqRelease;
}
