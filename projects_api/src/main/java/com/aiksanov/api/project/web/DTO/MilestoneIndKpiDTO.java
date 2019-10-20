package com.aiksanov.api.project.web.DTO;

public class MilestoneIndKpiDTO {
    private String label;
    private float adherence;
    private long delay;
    private long duration;

    public MilestoneIndKpiDTO() {
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public float getAdherence() {
        return adherence;
    }

    public void setAdherence(float adherence) {
        this.adherence = round(adherence);
    }

    public long getDelay() {
        return delay;
    }

    public void setDelay(long delay) {
        this.delay = delay;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    private float round(float value) {
        return Math.round(value * 100f) / 100f;
    }
}
