package com.aiksanov.api.project.web.DTO.kpi;

import java.util.List;

public class QualityIndicatorsDTO {
    private List<DefectsDTO> defects;
    private List<QualityDTO> qualities;
    private List<BacklogDTO> backlogs;

    public List<DefectsDTO> getDefects() {
        return defects;
    }

    public QualityIndicatorsDTO setDefects(List<DefectsDTO> defects) {
        this.defects = defects;
        return this;
    }

    public List<QualityDTO> getQualities() {
        return qualities;
    }

    public QualityIndicatorsDTO setQualities(List<QualityDTO> qualities) {
        this.qualities = qualities;
        return this;
    }

    public List<BacklogDTO> getBacklogs() {
        return backlogs;
    }

    public QualityIndicatorsDTO setBacklogs(List<BacklogDTO> backlogs) {
        this.backlogs = backlogs;
        return this;
    }
}