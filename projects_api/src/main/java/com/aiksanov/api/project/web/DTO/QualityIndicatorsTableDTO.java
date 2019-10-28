package com.aiksanov.api.project.web.DTO;

import java.util.List;

public class QualityIndicatorsTableDTO {
    private List<QualityIndicatorDTO> quality;
    private List<QualityIndicatorDTO> defects;
    private List<QualityIndicatorDTO> backlog;
    private List<QualityIndicatorDTO> testExecution;
    private List<QualityIndicatorDTO> testRate;
    private String syncDate;

    public QualityIndicatorsTableDTO() {
    }


    public QualityIndicatorsTableDTO(List<QualityIndicatorDTO> quality,
                                     List<QualityIndicatorDTO> defects,
                                     List<QualityIndicatorDTO> backlog,
                                     List<QualityIndicatorDTO> testExecution,
                                     List<QualityIndicatorDTO> testRate,
                                     String syncDate) {
        this.quality = quality;
        this.defects = defects;
        this.backlog = backlog;
        this.testExecution = testExecution;
        this.testRate = testRate;
        this.syncDate = syncDate;
    }

    public List<QualityIndicatorDTO> getQuality() {
        return quality;
    }

    public void setQuality(List<QualityIndicatorDTO> quality) {
        this.quality = quality;
    }

    public List<QualityIndicatorDTO> getDefects() {
        return defects;
    }

    public void setDefects(List<QualityIndicatorDTO> defects) {
        this.defects = defects;
    }

    public List<QualityIndicatorDTO> getBacklog() {
        return backlog;
    }

    public void setBacklog(List<QualityIndicatorDTO> backlog) {
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
