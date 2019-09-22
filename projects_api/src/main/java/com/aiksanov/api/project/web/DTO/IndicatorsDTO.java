package com.aiksanov.api.project.web.DTO;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class IndicatorsDTO {
    private List<MilestoneDTO> milestones;
    private HealthIndicatorsDTO healthIndicators;
    private IndicatorsReqDTO requirements;
    private Map<String, String> projectKpi;
    private QualityIndicatorsTableDTO quality;
    private Date synctime;


    public IndicatorsDTO() {
    }

    public IndicatorsDTO(List<MilestoneDTO> milestones, HealthIndicatorsDTO healthIndicators,
                         IndicatorsReqDTO requirements, QualityIndicatorsTableDTO quality, Date synctime) {
        this.milestones = milestones;
        this.healthIndicators = healthIndicators;
        this.requirements = requirements;
        this.quality = quality;
        this.synctime = synctime;
    }

    public List<MilestoneDTO> getMilestones() {
        return milestones;
    }

    public HealthIndicatorsDTO getHealthIndicators() {
        return healthIndicators;
    }

    public IndicatorsReqDTO getRequirements() {
        return requirements;
    }

    public Map<String, String> getProjectKpi() {
        return projectKpi;
    }

    public QualityIndicatorsTableDTO getQuality() {
        return quality;
    }

    public Date getSynctime() {
        return synctime;
    }
}
