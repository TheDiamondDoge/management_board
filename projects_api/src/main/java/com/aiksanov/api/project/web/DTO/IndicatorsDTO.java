package com.aiksanov.api.project.web.DTO;

import java.util.List;
import java.util.Map;

public class IndicatorsDTO {
    private List<MilestoneDTO> milestones;
    private HealthIndicatorsDTO healthIndicators;
    private IndicatorsReqDTO requirements;
    private Map<String, String> projectKpi;
    private List<Object> quality;


    public IndicatorsDTO() {
    }

    public IndicatorsDTO(List<MilestoneDTO> milestones, HealthIndicatorsDTO healthIndicators, IndicatorsReqDTO requirements) {
        this.milestones = milestones;
        this.healthIndicators = healthIndicators;
        this.requirements = requirements;
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

    public List<Object> getQuality() {
        return quality;
    }
}
