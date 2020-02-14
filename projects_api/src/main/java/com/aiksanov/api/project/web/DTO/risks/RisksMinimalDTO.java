package com.aiksanov.api.project.web.DTO.risks;

import com.aiksanov.api.project.data.entity.Risk;

import java.util.Objects;

public class RisksMinimalDTO {
    private Float rating;
    private String riskDescription;
    private String impactDescription;
    private String mitigation;

    public RisksMinimalDTO() {
    }

    public RisksMinimalDTO(Risk risk) {
        this.setRisk(risk);
    }

    private void setRisk(Risk risk) {
        if (Objects.nonNull(risk)) {
            this.rating = risk.getRating();
            this.riskDescription = risk.getRiskDescription();
            this.impactDescription = risk.getImpactDescription();
            this.mitigation = risk.getMitigation();
        }
    }

    public String getRiskDescription() {
        return riskDescription;
    }

    public void setRiskDescription(String riskDescription) {
        this.riskDescription = riskDescription;
    }

    public String getImpactDescription() {
        return impactDescription;
    }

    public void setImpactDescription(String impactDescription) {
        this.impactDescription = impactDescription;
    }

    public String getMitigation() {
        return mitigation;
    }

    public void setMitigation(String mitigation) {
        this.mitigation = mitigation;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }
}
