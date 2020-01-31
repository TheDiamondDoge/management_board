package com.aiksanov.api.project.data.entity;

import com.aiksanov.api.project.data.entity.pk.ActionRelatedRisksPK;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;

@Entity
@Table(name = "prj_actions_related_risks")
@IdClass(ActionRelatedRisksPK.class)
public class ActionRelatedRisks {
    @Id
    @Column(name = "action_id")
    private int actionId;

    @Id
    @Column(name = "risk_id")
    private int risksId;

    @Id
    @Column(name = "project_id")
    private int projectId;

    public ActionRelatedRisks() {
    }

    public ActionRelatedRisks(int actionId, int risksId, int projectId) {
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

    public int getRisksId() {
        return risksId;
    }

    public void setRisksId(int risksId) {
        this.risksId = risksId;
    }
}
