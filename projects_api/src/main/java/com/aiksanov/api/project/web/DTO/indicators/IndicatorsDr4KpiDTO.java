package com.aiksanov.api.project.web.DTO.indicators;


import java.util.Objects;

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
        this.scheduleAdherence = nanToNull(scheduleAdherence);
    }

    public Float getContentAdherence() {
        return contentAdherence;
    }

    public void setContentAdherence(Float contentAdherence) {
        this.contentAdherence = nanToNull(contentAdherence);
    }

    public Float getRqsChange() {
        return rqsChange;
    }

    public void setRqsChange(Float rqsChange) {
        this.rqsChange = nanToNull(rqsChange);
    }

    public Float getCostAdherence() {
        return costAdherence;
    }

    public void setCostAdherence(Float costAdherence) {
        this.costAdherence = nanToNull(costAdherence);
    }

    private Float nanToNull(Float f) {
        if (Objects.nonNull(f)) {
            return f.isNaN() ? null : f;
        } else {
            return f;
        }
    }
}
