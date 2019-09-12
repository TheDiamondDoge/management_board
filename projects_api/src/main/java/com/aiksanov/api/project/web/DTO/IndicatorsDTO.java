package com.aiksanov.api.project.web.DTO;

import java.util.List;
import java.util.Map;

public class IndicatorsDTO {
    private List<MilestoneDTO> milestones;
    private HealthIndicatorsDTO healthIndicators;
    private Map<String, String> requirements;
    private Map<String, String> projectKpi;
    //TODO: TBD
    private List<Object> quality;


    public IndicatorsDTO() {
    }

    public List<MilestoneDTO> getMilestones() {
        return milestones;
    }

    public void setMilestones(List<MilestoneDTO> milestones) {
        this.milestones = milestones;
    }

    public HealthIndicatorsDTO getHealthIndicators() {
        return healthIndicators;
    }

    public void setHealthIndicators(HealthIndicatorsDTO healthIndicators) {
        this.healthIndicators = healthIndicators;
    }

    public Map<String, String> getRequirements() {
        return requirements;
    }

    public void setRequirements(Map<String, String> requirements) {
        this.requirements = requirements;
    }

    public Map<String, String> getProjectKpi() {
        return projectKpi;
    }

    public void setProjectKpi(Map<String, String> projectKpi) {
        this.projectKpi = projectKpi;
    }

    public List<Object> getQuality() {
        return quality;
    }

    public void setQuality(List<Object> quality) {
        this.quality = quality;
    }
}
