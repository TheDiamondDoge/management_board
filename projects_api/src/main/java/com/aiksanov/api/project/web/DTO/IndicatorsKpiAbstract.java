package com.aiksanov.api.project.web.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public abstract class IndicatorsKpiAbstract {
    private Date synctime;
    private String week;
    private String crId;
    private String productName;
    private String releaseName;
    private String component;
    private String item;
    private String inState;
    private String created;
    private String assignee;
    private String reporter;
    private String outState;
    private String resolution;
    private String isDuplicate;
    private String priority;
    private String questStatus;
    private String state;
    private String proposedAction;
    private String tsRefBridge;
    private String tsRef;
    private String summary;
    private String howFound;
    private String rejected;
    private String regression;
    private String tsAccount;
    private String tsAssociatedAccount;
    private Date recomputedOn;
}
