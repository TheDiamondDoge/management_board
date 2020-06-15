package com.aiksanov.api.project.web.DTO.kpi;


import java.util.Date;

public class QualityIndicatorsAmountDTO {
    private int quality;
    private int backlog;
    private int defects;
    private Date updatedOn;
    private boolean updateInProcess;

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

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public QualityIndicatorsAmountDTO setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
        return this;
    }

    public boolean isUpdateInProcess() {
        return updateInProcess;
    }

    public QualityIndicatorsAmountDTO setUpdateInProcess(boolean updateInProcess) {
        this.updateInProcess = updateInProcess;
        return this;
    }
}