package com.aiksanov.api.project.data.entity;

import com.aiksanov.api.project.data.entity.pk.HealthIndicatorsPK;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Max;
import java.io.Serializable;

@Entity
@Table(name = "prj_indicators_health")
public class HealthIndicators implements Serializable {
    @JsonIgnore
    @EmbeddedId
    private HealthIndicatorsPK healthIndicatorsPK;

    @Column(name = "overall")
    @Max(1)
    private int overall;

    @Column(name = "schedule")
    private int schedule;

    @Column(name = "scope")
    private int scope;

    @Column(name = "quality")
    private int quality;

    @Column(name = "cost")
    private int cost;

    public HealthIndicators() {
    }

    public HealthIndicators(HealthIndicatorsPK healthIndicatorsPK, int overall, int schedule, int scope, int quality, int cost) {
        this.healthIndicatorsPK = healthIndicatorsPK;
        this.overall = overall;
        this.schedule = schedule;
        this.scope = scope;
        this.quality = quality;
        this.cost = cost;
    }

    public HealthIndicatorsPK getHealthIndicatorsPK() {
        return healthIndicatorsPK;
    }

    public void setHealthIndicatorsPK(HealthIndicatorsPK healthIndicatorsPK) {
        this.healthIndicatorsPK = healthIndicatorsPK;
    }

    public int getOverall() {
        return overall;
    }

    public void setOverall(int overall) {
        this.overall = overall;
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
