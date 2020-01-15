package com.aiksanov.api.project.web.DTO.information;

import com.aiksanov.api.project.data.entity.EcmaBacklogTarget;

public class EcmaBacklogTargetDTO {
    private String milestone;
    private int value;

    public EcmaBacklogTargetDTO() {
    }

    public EcmaBacklogTargetDTO(EcmaBacklogTarget target) {
        this.milestone = target.getLabel();
        this.value = target.getValue();
    }

    public String getMilestone() {
        return milestone;
    }

    public void setMilestone(String milestone) {
        this.milestone = milestone;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public EcmaBacklogTarget getEcmaBacklogTargetObj(int projectId) {
        return new EcmaBacklogTarget(projectId, milestone, value);
    }
}
