package com.aiksanov.api.project.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "prj_actions_state")
public class ActionsState {
    @Id
    @Column(name = "state_id")
    private int stateId;

    @Column(name = "state_label")
    private String stateLabel;

    public ActionsState() {
    }

    public ActionsState(int stateId, String stateLabel) {
        this.stateId = stateId;
        this.stateLabel = stateLabel;
    }

    public int getStateId() {
        return stateId;
    }

    public void setStateId(int stateId) {
        this.stateId = stateId;
    }

    public String getStateLabel() {
        return stateLabel;
    }

    public void setStateLabel(String stateLabel) {
        this.stateLabel = stateLabel;
    }
}
