package com.aiksanov.api.project.data.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

//TODO: Specific query (???) for fetch only 2 latest modification of each milestone
@Entity
@Table(name = "prj_indicators_health")
public class HealthIndicators implements Serializable {
    @EmbeddedId
    private HealthIndicatorsPK healthIndicatorsPK;

    @Column(name = "status")
    private int status;

    public HealthIndicators() {
    }

    public HealthIndicators(HealthIndicatorsPK healthIndicatorsPK, int status) {
        this.healthIndicatorsPK = healthIndicatorsPK;
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public HealthIndicatorsPK getHealthIndicatorsPK() {
        return healthIndicatorsPK;
    }

    public void setHealthIndicatorsPK(HealthIndicatorsPK healthIndicatorsPK) {
        this.healthIndicatorsPK = healthIndicatorsPK;
    }
}
