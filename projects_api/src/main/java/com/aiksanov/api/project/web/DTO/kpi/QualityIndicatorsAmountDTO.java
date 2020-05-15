package com.aiksanov.api.project.web.DTO.kpi;


public class QualityIndicatorsAmountDTO {
    private int quality;
    private int backlog;
    private int defects;

    public QualityIndicatorsAmountDTO() {
    }

    public int getQuality() {
        return quality;
    }

    public QualityIndicatorsAmountDTO setQuality(int quality) {
        this.quality = quality;
        return this;
    }

    public int getBacklog() {
        return backlog;
    }

    public QualityIndicatorsAmountDTO setBacklog(int backlog) {
        this.backlog = backlog;
        return this;
    }

    public int getDefects() {
        return defects;
    }

    public QualityIndicatorsAmountDTO setDefects(int defects) {
        this.defects = defects;
        return this;
    }
}