package com.aiksanov.api.project.web.DTO;

import com.aiksanov.api.project.data.entity.IndicatorsReqs;

import java.util.Date;
import java.util.Objects;

public class IndicatorsReqDTO {
    private Date dr1Actual;
    private Integer committedAtDr1;
    private Integer addedAfterDr1;
    private Integer removedAfterDr1;
    private Integer modifiedAfterDr1;
    private Integer sum;

    public IndicatorsReqDTO() {
    }

    public IndicatorsReqDTO(IndicatorsReqs reqs, Date dr1Actual) {
        this.dr1Actual = dr1Actual;
        if (Objects.nonNull(reqs)){
            mapIndicatorsReqs(reqs);
        } else {
            mapIndicatorsReqs(new IndicatorsReqs());
        }
        calculate();
    }

    private void mapIndicatorsReqs(IndicatorsReqs reqs) {
        this.committedAtDr1 = reqs.getCommittedAtDr1();
        this.addedAfterDr1 = reqs.getAddedAfterDr1();
        this.removedAfterDr1 = reqs.getRemovedAfterDr1();
        this.modifiedAfterDr1 = reqs.getModifiedAfterDr1();
    }

    private void calculate() {
        this.sum = committedAtDr1 + addedAfterDr1 - removedAfterDr1;
    }

    public Date getDr1Actual() {
        return dr1Actual;
    }

    public Integer getCommittedAtDr1() {
        return committedAtDr1;
    }

    public Integer getAddedAfterDr1() {
        return addedAfterDr1;
    }

    public Integer getRemovedAfterDr1() {
        return removedAfterDr1;
    }

    public Integer getModifiedAfterDr1() {
        return modifiedAfterDr1;
    }

    public Integer getSum() {
        return sum;
    }
}
