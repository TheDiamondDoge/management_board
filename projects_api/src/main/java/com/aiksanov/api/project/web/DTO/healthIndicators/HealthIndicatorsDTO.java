package com.aiksanov.api.project.web.DTO.healthIndicators;

import com.aiksanov.api.project.data.entity.HealthIndicators;
import com.aiksanov.api.project.util.enums.HealthStatus;

import java.util.*;

public class HealthIndicatorsDTO {
    private Map<String, HealthIndicators> statuses;
    private Map<String, String> comments;
    private Date currentStatusSet;
    private Date prevStatusSet;

    public HealthIndicatorsDTO() {
    }

    public HealthIndicatorsDTO(List<HealthIndicators> twoLastModifiedHealths, Map<String, String> comments) {
        statuses = this.getEmptyStatuses();
        if (Objects.nonNull(twoLastModifiedHealths)) {
            if (twoLastModifiedHealths.size() > 0) {
                statuses.put(HealthStatus.CURRENT.getLabel(), twoLastModifiedHealths.get(0));
                currentStatusSet = twoLastModifiedHealths.get(0).getHealthIndicatorsPK().getModificationDate();
            }

            if (twoLastModifiedHealths.size() >= 2) {
                statuses.put(HealthStatus.PREVIOUS.getLabel(), twoLastModifiedHealths.get(1));
                prevStatusSet = twoLastModifiedHealths.get(1).getHealthIndicatorsPK().getModificationDate();
            }
        }

        if (Objects.nonNull(comments)) {
            this.comments = comments;
        }
    }

    public Map<String, HealthIndicators> getStatuses() {
        return statuses;
    }

    public Map<String, String> getComments() {
        return comments;
    }

    public Date getCurrentStatusSet() {
        return currentStatusSet;
    }

    public Date getPrevStatusSet() {
        return prevStatusSet;
    }

    private Map<String, HealthIndicators> getEmptyStatuses() {
        Map<String, HealthIndicators> result = new HashMap<>();
        HealthIndicators indicator = new HealthIndicators(0,0,0,0,0);
        result.put(HealthStatus.CURRENT.getLabel(), indicator);

        return result;
    }

    @Override
    public String toString() {
        return "HealthIndicatorsDTO{" +
                "statuses=" + statuses +
                ", comments=" + comments +
                ", currentStatusSet=" + currentStatusSet +
                ", prevStatusSet=" + prevStatusSet +
                '}';
    }
}
