package com.aiksanov.api.project.web.DTO.quality;

import java.util.Date;
import java.util.List;

public class QualityIndicatorsTableDTO {
    private List<QualityIndicatorDTO> quality;
    private List<QualityIndicatorDTO> defects;
    private List<QualityIndicatorDTO> backlog;
    private List<QualityIndicatorDTO> testExecution;
    private List<QualityIndicatorDTO> testRate;
    private Date syncDate;
    private boolean updateInProcess;

    public QualityIndicatorsTableDTO() {
    }

    public QualityIndicatorsTableDTO(List<QualityIndicatorDTO> quality,
                                     List<QualityIndicatorDTO> defects,
                                     List<QualityIndicatorDTO> backlog,
                                     List<QualityIndicatorDTO> testExecution,
                                     List<QualityIndicatorDTO> testRate,
                                     Date syncDate,
                                     boolean updateInProcess
    ) {
        this.quality = quality;
        this.defects = defects;
        this.backlog = backlog;
        this.testExecution = testExecution;
        this.testRate = testRate;
        this.syncDate = syncDate;
        this.updateInProcess = updateInProcess;
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

    public Date getSyncDate() {
        return syncDate;
    }

    public void setSyncDate(Date syncDate) {
        this.syncDate = syncDate;
    }

    public boolean isUpdateInProcess() {
        return updateInProcess;
    }

    public void setUpdateInProcess(boolean updateInProcess) {
        this.updateInProcess = updateInProcess;
    }
}