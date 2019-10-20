package com.aiksanov.api.project.web.DTO;

import java.util.List;

public class QualityIndicatorsTableDTO {
    private QualityIndicatorDTO quality;
    private QualityIndicatorDTO defects;
    private QualityIndicatorDTO backlog;
    private List<QualityIndicatorDTO> testExecution;
    private List<QualityIndicatorDTO> testRate;
    private String syncDate;

    public QualityIndicatorsTableDTO() {
    }

    public QualityIndicatorsTableDTO(QualityIndicatorDTO quality,
                                     QualityIndicatorDTO defects,
                                     QualityIndicatorDTO backlog,
                                     List<QualityIndicatorDTO> testExecution,
                                     List<QualityIndicatorDTO> testRate
    ) {
        this.quality = quality;
        this.defects = defects;
        this.backlog = backlog;
        this.testExecution = testExecution;
        this.testRate = testRate;
    }

    public QualityIndicatorDTO getQuality() {
        return quality;
    }

    public void setQuality(QualityIndicatorDTO quality) {
        this.quality = quality;
    }

    public QualityIndicatorDTO getDefects() {
        return defects;
    }

    public void setDefects(QualityIndicatorDTO defects) {
        this.defects = defects;
    }

    public QualityIndicatorDTO getBacklog() {
        return backlog;
    }

    public void setBacklog(QualityIndicatorDTO backlog) {
        this.backlog = backlog;
    }

    public List<QualityIndicatorDTO> getTestExecution() {
        return testExecution;
    }

    public void setTestExecution(List<QualityIndicatorDTO> testExecution) {
        this.testExecution = testExecution;
    }

    public List<QualityIndicatorDTO> getTestRate() {
        return testRate;
    }

    public void setTestRate(List<QualityIndicatorDTO> testRate) {
        this.testRate = testRate;
    }

    public String getSyncDate() {
        return syncDate;
    }

    public void setSyncDate(String syncDate) {
        this.syncDate = syncDate;
    }
}
