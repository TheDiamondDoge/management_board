package com.aiksanov.api.project.web.DTO.healthIndicators;

import com.aiksanov.api.project.data.entity.HealthIndicators;

import java.util.Objects;

public class HealthIndicatorsMinimalDTO {
    private int schedule;
    private int scope;
    private int quality;
    private int cost;

    public HealthIndicatorsMinimalDTO() {
    }

    public HealthIndicatorsMinimalDTO(HealthIndicators indicators) {
        this.setIndicators(indicators);
    }

    private void setIndicators(HealthIndicators indicators) {
        if (Objects.nonNull(indicators)) {
            this.schedule = indicators.getSchedule();
            this.scope = indicators.getScope();
            this.quality = indicators.getQuality();
            this.cost = indicators.getCost();
        }
    }

    public int getSchedule() {
        return schedule;
    }

    public void setSchedule(int schedule) {
        this.schedule = schedule;
    }

    public int getScope() {
        return scope;
    }

    public void setScope(int scope) {
        this.scope = scope;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
