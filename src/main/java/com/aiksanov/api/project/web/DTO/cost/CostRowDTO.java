package com.aiksanov.api.project.web.DTO.cost;

import com.aiksanov.api.project.data.entity.Cost;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

    public Cost getCostWithoutTypeAndPrjId() {
        Cost cost = new Cost();
        cost.setState(state);
        cost.setMilestone(milestone);
        cost.setValue(value);
        cost.setComment(comment);
        return cost;
    }
}
