package com.aiksanov.api.project.web.DTO.risks;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class RisksFromFile {
    private String riskDisplayId;
    private int impact;
    private String probability;
    private Float rating;
    private String previous;
    private String initial;
    private String riskDescription;
    private String impactDescription;
    private String businessImpact;
    private String riskResponse;
    private String mitigation;
    private Date decisionDate;
    private String estimatedCost;
    private String provisionBudget;
    private String responsible;
    private String relatedAction;
    private Date target;
    private Date done;
    private Date result;
    private boolean report;
}
