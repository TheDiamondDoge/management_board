package com.aiksanov.api.project.web.DTO.kpi;


public class DefectsDTO extends QualityIndicatorsAbstract {
    private int id;
    private String TSsubmitDate;

    public DefectsDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTSsubmitDate() {
        return TSsubmitDate;
    }

    public void setTSsubmitDate(String TSsubmitDate) {
        this.TSsubmitDate = TSsubmitDate;
    }
}
