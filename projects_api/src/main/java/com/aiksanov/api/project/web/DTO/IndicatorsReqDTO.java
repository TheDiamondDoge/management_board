package com.aiksanov.api.project.web.DTO;

import com.aiksanov.api.project.data.entity.IndicatorsReqs;

import java.util.Date;
import java.util.Objects;

public class IndicatorsReqDTO {
    private Date dr1Actual;
    private int committedAtDr1;
    private int addedAfterDr1;
    private int removedAfterDr1;
    private int modifiedAfterDr1;
    private int sum;

    public IndicatorsReqDTO() {
    }

    public IndicatorsReqDTO(IndicatorsReqs reqs, Date dr1Actual) {
        this.dr1Actual = dr1Actual;
        if (Objects.nonNull(reqs)){
            mapIndicatorsReqs(reqs);
        } else {
            mapIndicatorsReqs(new IndicatorsReqs());
        }
        calculateSum();
    }

    private void mapIndicatorsReqs(IndicatorsReqs reqs) {
        this.committedAtDr1 = reqs.getCommittedAtDr1();
        this.addedAfterDr1 = reqs.getAddedAfterDr1();
        this.removedAfterDr1 = reqs.getRemovedAfterDr1();
        this.modifiedAfterDr1 = reqs.getModifiedAfterDr1();
    }

    private void calculateSum() {
        this.sum = committedAtDr1 + addedAfterDr1 + removedAfterDr1 + modifiedAfterDr1;
    }

    public Date getDr1Actual() {
        return dr1Actual;
    }

    public int getCommittedAtDr1() {
        return committedAtDr1;
    }

    public int getAddedAfterDr1() {
        return addedAfterDr1;
    }

    public int getRemovedAfterDr1() {
        return removedAfterDr1;
    }

    public int getModifiedAfterDr1() {
        return modifiedAfterDr1;
    }

    public int getSum() {
        return sum;
    }
}
