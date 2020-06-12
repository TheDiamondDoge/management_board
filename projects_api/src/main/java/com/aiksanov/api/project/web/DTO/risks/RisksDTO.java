package com.aiksanov.api.project.web.DTO.risks;

import com.aiksanov.api.project.data.entity.Risk;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class RisksDTO {
    private int riskId;
    private String riskDisplayId;
    private String impact;
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
    private java.util.Date updatedOn;
    private String updatedBy;
    private boolean report;

    public RisksDTO(Risk risk) {
        if (Objects.nonNull(risk)) {
            this.mapRiskToDto(risk);
        }
    }

    private void mapRiskToDto(Risk risk) {
        this.riskId = risk.getRiskId();
        this.riskDisplayId = risk.getRiskDisplayId();
        this.impact = risk.getImpact();
        this.probability = risk.getProbability();
        this.rating = risk.getRating();
        this.previous = risk.getPrevious();
        this.initial = risk.getInitial();
        this.riskDescription = risk.getRiskDescription();
        this.impactDescription = risk.getImpactDescription();
        this.businessImpact = risk.getBusinessImpact();
        this.riskResponse = risk.getRiskResponse();
        this.mitigation = risk.getMitigation();
        this.decisionDate = risk.getDecisionDate();
        this.estimatedCost = risk.getEstimatedCost();
        this.provisionBudget = risk.getProvisionBudget();
        this.responsible = risk.getResponsible();
        this.target = risk.getTarget();
        this.done = risk.getDone();
        this.result = risk.getResult();
        this.updatedOn = risk.getUpdatedOn();
        this.updatedBy = risk.getUpdatedBy();
        this.report = risk.isReport();
    }

    public Risk createRiskObjWOProjectId() {
        Risk risk = new Risk();
        risk.setRiskDisplayId(this.riskDisplayId);
        risk.setRiskId(this.riskId);
        risk.setImpact(this.impact);
        risk.setProbability(this.probability);
        risk.setRating(this.getNullIfZero(this.rating));
        risk.setPrevious(this.previous);
        risk.setInitial(this.initial);
        risk.setRiskDescription(this.riskDescription);
        risk.setImpactDescription(this.impactDescription);
        risk.setBusinessImpact(this.businessImpact);
        risk.setRiskResponse(this.riskResponse);
        risk.setMitigation(this.mitigation);
        risk.setDecisionDate(this.decisionDate);
        risk.setEstimatedCost(this.estimatedCost);
        risk.setProvisionBudget(this.provisionBudget);
        risk.setResponsible(this.responsible);
        risk.setTarget(this.target);
        risk.setDone(this.done);
        risk.setResult(this.result);
        risk.setUpdatedOn(new java.util.Date());
        //TODO Add csl from session or whatever
        risk.setUpdatedBy("aiksanov");
        risk.setReport(this.report);

        return risk;
    }

    private Float getNullIfZero(Float value) {
        return Math.signum(value) == 0 ? null : value;
    }
}
