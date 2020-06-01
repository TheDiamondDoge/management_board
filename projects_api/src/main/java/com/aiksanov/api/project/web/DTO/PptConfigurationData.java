package com.aiksanov.api.project.web.DTO;

import com.aiksanov.api.project.web.DTO.healthIndicators.HealthIndicatorsDTO;
import com.aiksanov.api.project.web.DTO.reports.PptImageFile;
import com.aiksanov.api.project.web.DTO.risks.RisksDTO;
import com.aiksanov.api.project.web.DTO.summary.ProjectGeneral;

import java.util.List;

public class PptConfigurationData {
    private ProjectGeneral general;
    private List<MilestoneDTO> milestones;
    private List<RisksDTO> risks;
    private List<RequirementsDTO> requirements;
    private HealthIndicatorsDTO indicators;
    private String executionSummary;
    private String projectDetails;
    private List<String> flags;
    private List<PptImageFile> images;

    public PptConfigurationData() {
    }

    public ProjectGeneral getGeneral() {
        return general;
    }

    public PptConfigurationData setGeneral(ProjectGeneral general) {
        this.general = general;
        return this;
    }

    public List<MilestoneDTO> getMilestones() {
        return milestones;
    }

    public PptConfigurationData setMilestones(List<MilestoneDTO> milestones) {
        this.milestones = milestones;
        return this;
    }

    public List<String> getFlags() {
        return flags;
    }

    public PptConfigurationData setFlags(List<String> flags) {
        this.flags = flags;
        return this;
    }

    public String getExecutionSummary() {
        return executionSummary;
    }

    public PptConfigurationData setExecutionSummary(String executionSummary) {
        this.executionSummary = executionSummary;
        return this;
    }

    public List<RisksDTO> getRisks() {
        return risks;
    }

    public PptConfigurationData setRisks(List<RisksDTO> risks) {
        this.risks = risks;
        return this;
    }

    public String getProjectDetails() {
        return projectDetails;
    }

    public PptConfigurationData setProjectDetails(String projectDetails) {
        this.projectDetails = projectDetails;
        return this;
    }

    public List<RequirementsDTO> getRequirements() {
        return requirements;
    }

    public PptConfigurationData setRequirements(List<RequirementsDTO> requirements) {
        this.requirements = requirements;
        return this;
    }

    public HealthIndicatorsDTO getIndicators() {
        return indicators;
    }

    public PptConfigurationData setIndicators(HealthIndicatorsDTO indicators) {
        this.indicators = indicators;
        return this;
    }

    public List<PptImageFile> getImages() {
        return images;
    }

    public PptConfigurationData setImages(List<PptImageFile> images) {
        this.images = images;
        return this;
    }
}
