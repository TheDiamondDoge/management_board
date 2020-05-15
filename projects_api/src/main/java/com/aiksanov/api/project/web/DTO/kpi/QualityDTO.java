package com.aiksanov.api.project.web.DTO.kpi;


public class QualityDTO extends QualityIndicatorsAbstract{
    private int id;
    private String type;
    private String TSsubmitDate;

    public QualityDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTSsubmitDate() {
        return TSsubmitDate;
    }

    public void setTSsubmitDate(String TSsubmitDate) {
        this.TSsubmitDate = TSsubmitDate;
    }
}
