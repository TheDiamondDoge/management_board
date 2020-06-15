package com.aiksanov.api.project.data.entity;

import com.aiksanov.api.project.data.entity.pk.HealthIndicatorsPK;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
@Table(name = "prj_indicators_health")
public class HealthIndicators implements Serializable {
    @JsonIgnore
    @EmbeddedId
    private HealthIndicatorsPK healthIndicatorsPK;

    @Column(name = "overall")
    private int overall;

    @Column(name = "schedule")
    private int schedule;

    @Column(name = "scope")
    private int scope;

    @Column(name = "quality")
    private int quality;

    @Column(name = "cost")
    private int cost;

    public HealthIndicators(int overall, int schedule, int scope, int quality, int cost) {
        this.overall = overall;
        this.schedule = schedule;
        this.scope = scope;
        this.quality = quality;
        this.cost = cost;
    }
}
