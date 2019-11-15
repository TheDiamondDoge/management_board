package com.aiksanov.api.project.data.entity;

import com.aiksanov.api.project.data.entity.pk.EcmaBacklogTargetPK;

import javax.persistence.*;

@Entity
@Table(name = "prj_ecma_backlog_target")
@IdClass(EcmaBacklogTargetPK.class)
public class EcmaBacklogTarget {
    @Id
    @Column(name = "project_id")
    private int projectId;

    @Id
    @Column(name = "milestone_label")
    private String label;

    @Column(name = "value")
    private int value;

    public EcmaBacklogTarget() {
    }

    public EcmaBacklogTarget(int projectId, String label, int value) {
        this.projectId = projectId;
        this.label = label;
        this.value = value;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
