package com.aiksanov.api.project.data.entity;

import com.aiksanov.api.project.data.entity.pk.RiskPK;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "prj_risks")
@IdClass(RiskPK.class)
public class Risk {
    @Id
    @Column(name = "project_id")
    private int projectId;

    @Id
    @Column(name = "risk_id")
    private int riskId;

    @Column(name = "risk_display_id")
    private String riskDisplayId;

    @Column(name = "impact")
    private String impact;

    @Column(name = "probability")
    private String probability;

    @Column(name = "rating")
    private Float rating;

    @Column(name = "previous")
    private String previous;

    @Column(name = "initial")
    private String initial;

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

    @Column(name = "target")
    private Date target;

    @Column(name = "done")
    private Date done;

    @Column(name = "result")
    private Date result;

    @Column
    private java.util.Date updatedOn;

    @Column
    private String updatedBy;

    @Column(name = "report")
    private boolean report;
}
