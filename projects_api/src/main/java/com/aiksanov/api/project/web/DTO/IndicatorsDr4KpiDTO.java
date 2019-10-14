package com.aiksanov.api.project.web.DTO;


public class IndicatorsDr4KpiDTO {
    private int year;
    private float scheduleAdherence;
    private float contentAdherence;
    private float rqsChange;
    private float costAdherence;

    public IndicatorsDr4KpiDTO() {
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public float getScheduleAdherence() {
        return scheduleAdherence;
    }

    public void setScheduleAdherence(float scheduleAdherence) {
        this.scheduleAdherence = scheduleAdherence;
    }

    public float getContentAdherence() {
        return contentAdherence;
    }

    public void setContentAdherence(float contentAdherence) {
        this.contentAdherence = contentAdherence;
    }

    public float getRqsChange() {
        return rqsChange;
    }

    public void setRqsChange(float rqsChange) {
        this.rqsChange = rqsChange;
    }

    public float getCostAdherence() {
        return costAdherence;
    }

    public void setCostAdherence(float costAdherence) {
        this.costAdherence = costAdherence;
    }
}
