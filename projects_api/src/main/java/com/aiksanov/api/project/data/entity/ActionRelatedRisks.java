package com.aiksanov.api.project.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "prj_actions_related_risks")
public class ActionRelatedRisks implements Serializable {
    @Id
    @Column(name = "action_id")
    private int actionId;

    @Id
    @Column(name = "risks_id")
    private Float risksId;

    @Column(name = "project_id")
    private int projectId;

    public ActionRelatedRisks() {
    }

    public ActionRelatedRisks(int actionId, float risksId, int projectId) {
        this.actionId = actionId;
        this.risksId = risksId;
        this.projectId = projectId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getActionId() {
        return actionId;
    }

    public void setActionId(int actionId) {
        this.actionId = actionId;
    }

    public Float getRisksId() {
        return risksId;
    }

    public void setRisksId(Float risksId) {
        this.risksId = risksId;
    }
}
