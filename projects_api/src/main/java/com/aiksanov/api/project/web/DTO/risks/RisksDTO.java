package com.aiksanov.api.project.web.DTO.risks;

import com.aiksanov.api.project.data.entity.Risk;

import java.sql.Date;
import java.util.Objects;

public class RisksDTO {
    private int riskId;
    private String riskDisplayId;
    private int impact;
    private Float probability;
    private Float rating;
    private Float previous;
    private Float initial;
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

    public RisksDTO() {
    }

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
        this.relatedAction = risk.getRelatedAction();
        this.target = risk.getTarget();
        this.done = risk.getDone();
        this.result = risk.getResult();
        this.report = risk.isReport();
    }

    public Risk createRiskObjWOProjectId() {
        Risk risk = new Risk();
        risk.setRiskDisplayId(this.riskDisplayId);
        risk.setRiskId(this.riskId);
        risk.setImpact(this.impact);
        risk.setProbability(this.getNullIfZero(this.probability));
        risk.setRating(this.getNullIfZero(this.rating));
        risk.setPrevious(this.getNullIfZero(this.previous));
        risk.setInitial(this.getNullIfZero(this.initial));
        risk.setRiskDescription(this.riskDescription);
        risk.setImpactDescription(this.impactDescription);
        risk.setBusinessImpact(this.businessImpact);
        risk.setRiskResponse(this.riskResponse);
        risk.setMitigation(this.mitigation);
        risk.setDecisionDate(this.decisionDate);
        risk.setEstimatedCost(this.estimatedCost);
        risk.setProvisionBudget(this.provisionBudget);
        risk.setResponsible(this.responsible);
        risk.setRelatedAction(this.relatedAction);
        risk.setTarget(this.target);
        risk.setDone(this.done);
        risk.setResult(this.result);
        risk.setReport(this.report);

        return risk;
    }

    private Float getNullIfZero(Float value) {
        return Math.signum(value) == 0 ? null : value;
    }

    public int getRiskId() {
        return riskId;
    }

    public void setRiskId(int riskId) {
        this.riskId = riskId;
    }

    public String getRiskDisplayId() {
        return riskDisplayId;
    }

    public void setRiskDisplayId(String riskDisplayId) {
        this.riskDisplayId = riskDisplayId;
    }

    public int getImpact() {
        return impact;
    }

    public void setImpact(int impact) {
        this.impact = impact;
    }

    public Float getProbability() {
        return probability;
    }

    public void setProbability(Float probability) {
        this.probability = probability;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public Float getPrevious() {
        return previous;
    }

    public void setPrevious(Float previous) {
        this.previous = previous;
    }

    public Float getInitial() {
        return initial;
    }

    public void setInitial(Float initial) {
        this.initial = initial;
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

    public String getBusinessImpact() {
        return businessImpact;
    }

    public void setBusinessImpact(String businessImpact) {
        this.businessImpact = businessImpact;
    }

    public String getRiskResponse() {
        return riskResponse;
    }

    public void setRiskResponse(String riskResponse) {
        this.riskResponse = riskResponse;
    }

    public String getMitigation() {
        return mitigation;
    }

    public void setMitigation(String mitigation) {
        this.mitigation = mitigation;
    }

    public Date getDecisionDate() {
        return decisionDate;
    }

    public void setDecisionDate(Date decisionDate) {
        this.decisionDate = decisionDate;
    }

    public String getEstimatedCost() {
        return estimatedCost;
    }

    public void setEstimatedCost(String estimatedCost) {
        this.estimatedCost = estimatedCost;
    }

    public String getProvisionBudget() {
        return provisionBudget;
    }

    public void setProvisionBudget(String provisionBudget) {
        this.provisionBudget = provisionBudget;
    }

    public String getResponsible() {
        return responsible;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }

    public String getRelatedAction() {
        return relatedAction;
    }

    public void setRelatedAction(String relatedAction) {
        this.relatedAction = relatedAction;
    }

    public Date getTarget() {
        return target;
    }

    public void setTarget(Date target) {
        this.target = target;
    }

    public Date getDone() {
        return done;
    }

    public void setDone(Date done) {
        this.done = done;
    }

    public Date getResult() {
        return result;
    }

    public void setResult(Date result) {
        this.result = result;
    }

    public boolean isReport() {
        return report;
    }

    public void setReport(boolean report) {
        this.report = report;
    }
}
