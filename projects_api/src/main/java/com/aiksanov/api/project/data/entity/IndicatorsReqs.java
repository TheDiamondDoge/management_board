package com.aiksanov.api.project.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "prj_indicators_reqs")
public class IndicatorsReqs {
    @Id
    @Column(name = "project_id")
    private int projectID;

    @Column(name = "committed_at_dr1")
    private int committedAtDr1;

    @Column(name = "added_after_dr1")
    private int addedAfterDr1;

    @Column(name = "removed_after_dr1")
    private int removedAfterDr1;

    @Column(name = "modified_after_dr1")
    private int modifiedAfterDr1;

    public IndicatorsReqs() {
    }

    public IndicatorsReqs(int projectID, int committedAtDr1, int addedAfterDr1, int removedAfterDr1, int modifiedAfterDr1) {
        this.projectID = projectID;
        this.committedAtDr1 = committedAtDr1;
        this.addedAfterDr1 = addedAfterDr1;
        this.removedAfterDr1 = removedAfterDr1;
        this.modifiedAfterDr1 = modifiedAfterDr1;
    }

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public int getCommittedAtDr1() {
        return committedAtDr1;
    }

    public void setCommittedAtDr1(int committedAtDr1) {
        this.committedAtDr1 = committedAtDr1;
    }

    public int getAddedAfterDr1() {
        return addedAfterDr1;
    }

    public void setAddedAfterDr1(int addedAfterDr1) {
        this.addedAfterDr1 = addedAfterDr1;
    }

    public int getRemovedAfterDr1() {
        return removedAfterDr1;
    }

    public void setRemovedAfterDr1(int removedAfterDr1) {
        this.removedAfterDr1 = removedAfterDr1;
    }

    public int getModifiedAfterDr1() {
        return modifiedAfterDr1;
    }

    public void setModifiedAfterDr1(int modifiedAfterDr1) {
        this.modifiedAfterDr1 = modifiedAfterDr1;
    }
}
