package com.aiksanov.api.project.web.DTO;

import java.util.Date;

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

    public IndicatorsKpiAbstract() {
    }

    public Date getSynctime() {
        return synctime;
    }

    public void setSynctime(Date synctime) {
        this.synctime = synctime;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getCrId() {
        return crId;
    }

    public void setCrId(String crId) {
        this.crId = crId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getReleaseName() {
        return releaseName;
    }

    public void setReleaseName(String releaseName) {
        this.releaseName = releaseName;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getInState() {
        return inState;
    }

    public void setInState(String inState) {
        this.inState = inState;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getReporter() {
        return reporter;
    }

    public void setReporter(String reporter) {
        this.reporter = reporter;
    }

    public String getOutState() {
        return outState;
    }

    public void setOutState(String outState) {
        this.outState = outState;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getIsDuplicate() {
        return isDuplicate;
    }

    public void setIsDuplicate(String isDuplicate) {
        this.isDuplicate = isDuplicate;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getQuestStatus() {
        return questStatus;
    }

    public void setQuestStatus(String questStatus) {
        this.questStatus = questStatus;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getProposedAction() {
        return proposedAction;
    }

    public void setProposedAction(String proposedAction) {
        this.proposedAction = proposedAction;
    }

    public String getTsRefBridge() {
        return tsRefBridge;
    }

    public void setTsRefBridge(String tsRefBridge) {
        this.tsRefBridge = tsRefBridge;
    }

    public String getTsRef() {
        return tsRef;
    }

    public void setTsRef(String tsRef) {
        this.tsRef = tsRef;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getHowFound() {
        return howFound;
    }

    public void setHowFound(String howFound) {
        this.howFound = howFound;
    }

    public String getRejected() {
        return rejected;
    }

    public void setRejected(String rejected) {
        this.rejected = rejected;
    }

    public String getRegression() {
        return regression;
    }

    public void setRegression(String regression) {
        this.regression = regression;
    }

    public String getTsAccount() {
        return tsAccount;
    }

    public void setTsAccount(String tsAccount) {
        this.tsAccount = tsAccount;
    }

    public String getTsAssociatedAccount() {
        return tsAssociatedAccount;
    }

    public void setTsAssociatedAccount(String tsAssociatedAccount) {
        this.tsAssociatedAccount = tsAssociatedAccount;
    }

    public Date getRecomputedOn() {
        return recomputedOn;
    }

    public void setRecomputedOn(Date recomputedOn) {
        this.recomputedOn = recomputedOn;
    }
}
