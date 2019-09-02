package com.aiksanov.api.project.web.DTO;

import com.aiksanov.api.project.data.domain.HealthStatus;
import com.aiksanov.api.project.data.entity.HealthIndicators;
import com.aiksanov.api.project.data.entity.HealthIndicatorsComments;

import java.util.*;

public class HealthIndicatorsDTO {
    private Map<String, HealthIndicators> statuses;
    private Map<HealthStatus, HealthIndicatorsComments> comments;
    private Date currentStatusSet;
    private Date prevStatusSet;

    public HealthIndicatorsDTO() {
    }

    public HealthIndicatorsDTO(List<HealthIndicators> twoLastModifiedHealths, Map<HealthStatus, HealthIndicatorsComments> comments) {
        statuses = new HashMap<>();
        if (Objects.nonNull(twoLastModifiedHealths)) {
            statuses.put("current", twoLastModifiedHealths.get(0));
            statuses.put("prev", twoLastModifiedHealths.get(1));

            currentStatusSet = twoLastModifiedHealths.get(0).getHealthIndicatorsPK().getModificationDate();
            prevStatusSet = twoLastModifiedHealths.get(1).getHealthIndicatorsPK().getModificationDate();
        }

        if (Objects.nonNull(comments)){
            this.comments = comments;
        }
    }

    public Map<String, HealthIndicators> getStatuses() {
        return statuses;
    }

    public Map<HealthStatus, HealthIndicatorsComments> getComments() {
        return comments;
    }

    public Date getCurrentStatusSet() {
        return currentStatusSet;
    }

    public Date getPrevStatusSet() {
        return prevStatusSet;
    }
}
