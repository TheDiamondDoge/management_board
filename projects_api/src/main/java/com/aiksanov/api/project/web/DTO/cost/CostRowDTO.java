package com.aiksanov.api.project.web.DTO.cost;

import com.aiksanov.api.project.data.entity.Cost;

public class CostRowDTO {
    private int state;
    private String milestone;
    private double value;
    private String comment;

    public CostRowDTO() {
    }

    public CostRowDTO(Cost cost) {
        this.state = cost.getState();
        this.milestone = cost.getMilestone();
        this.value = cost.getValue();
        this.comment = cost.getComment();
    }

    public CostRowDTO(int state, String milestone, double value, String comment) {
        this.state = state;
        this.milestone = milestone;
        this.value = value;
        this.comment = comment;
    }

    public Cost getCostWithoutTypeAndPrjId() {
        Cost cost = new Cost();
        cost.setState(state);
        cost.setMilestone(milestone);
        cost.setValue(value);
        cost.setComment(comment);
        return cost;
    }

    public String getMilestone() {
        return milestone;
    }

    public void setMilestone(String milestone) {
        this.milestone = milestone;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
