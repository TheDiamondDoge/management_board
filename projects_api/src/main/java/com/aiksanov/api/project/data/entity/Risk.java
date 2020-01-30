package com.aiksanov.api.project.data.entity;

import com.aiksanov.api.project.data.entity.pk.RiskPK;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
@Table(name = "prj_risks")
@IdClass(RiskPK.class)
public class Risk {
    @Id
    @Column(name = "project_id")
    @JsonIgnore
    private int projectId;

    @Id
    @Column(name = "risk_id")
    private float riskId;

    @Column(name = "impact")
    private int impact;

    @Column(name = "probability")
    private String probability;

    @Column(name = "rating")
    private Float rating;

    @Column(name = "previous")
    private Float previous;

    @Column(name = "initial")
    private Float initial;

    @Column(name = "risk_description")
    private String riskDescription;

    @Column(name = "impact_description")
    private String impactDescription;

    @Column(name = "business_impact")
    private String businessImpact;

    @Column(name = "risk_response")
    private String riskResponse;

    @Column(name = "mitigation")
    private String mitigation;

    @Column(name = "decision_date")
    private Date decisionDate;

    @Column(name = "estimated_cost")
    private String estimatedCost;

    @Column(name = "provision_budget")
    private String provisionBudget;

    @Column(name = "responsible")
    private String responsible;

    @Column(name = "related_action")
    private String relatedAction;

    @Column(name = "target")
    private Date target;

    @Column(name = "done")
    private Date done;

    @Column(name = "result")
    private Date result;

    @Column(name = "report")
    private boolean report;

    @ManyToMany(mappedBy = "actions")
    private Set<Actions> relatedActions;

    public Risk() {
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public float getRiskId() {
        return riskId;
    }

    public void setRiskId(float riskId) {
        this.riskId = riskId;
    }

    public int getImpact() {
        return impact;
    }

    public void setImpact(int impact) {
        this.impact = impact;
    }

    public String getProbability() {
        return probability;
    }

    public void setProbability(String probability) {
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

    public Set<Actions> getRelatedActions() {
        return relatedActions;
    }

    public void setRelatedActions(Set<Actions> relatedActions) {
        this.relatedActions = relatedActions;
    }
}
