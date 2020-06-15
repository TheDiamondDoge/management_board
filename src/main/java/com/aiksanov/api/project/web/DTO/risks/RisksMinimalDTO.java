package com.aiksanov.api.project.web.DTO.risks;

import com.aiksanov.api.project.data.entity.Risk;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class RisksMinimalDTO {
    private Float rating;
    private String riskDescription;
    private String impactDescription;
    private String mitigation;


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
}
