package com.aiksanov.api.project.web.DTO;


public class IndicatorsDr4KpiDTO {
    private int year;
    private Float scheduleAdherence;
    private Float contentAdherence;
    private Float rqsChange;
    private Float costAdherence;

    public IndicatorsDr4KpiDTO() {
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Float getScheduleAdherence() {
        return scheduleAdherence;
    }

    public void setScheduleAdherence(Float scheduleAdherence) {
        this.scheduleAdherence = scheduleAdherence;
    }

    public Float getContentAdherence() {
        return contentAdherence;
    }

    public void setContentAdherence(Float contentAdherence) {
        this.contentAdherence = contentAdherence;
    }

    public Float getRqsChange() {
        return rqsChange;
    }

    public void setRqsChange(Float rqsChange) {
        this.rqsChange = rqsChange;
    }

    public Float getCostAdherence() {
        return costAdherence;
    }

    public void setCostAdherence(Float costAdherence) {
        this.costAdherence = costAdherence;
    }
}
