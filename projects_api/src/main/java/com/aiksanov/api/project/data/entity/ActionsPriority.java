package com.aiksanov.api.project.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "prj_actions_priority")
public class ActionsPriority {
    @Id
    @Column(name = "priority_id")
    private int priorityId;

    @Column(name = "priority_label")
    private String priorityLabel;

    public ActionsPriority() {
    }

    public ActionsPriority(int priorityId, String priorityLabel) {
        this.priorityId = priorityId;
        this.priorityLabel = priorityLabel;
    }

    public int getPriorityId() {
        return priorityId;
    }

    public void setPriorityId(int priorityId) {
        this.priorityId = priorityId;
    }

    public String getPriorityLabel() {
        return priorityLabel;
    }

    public void setPriorityLabel(String priorityLabel) {
        this.priorityLabel = priorityLabel;
    }
}
