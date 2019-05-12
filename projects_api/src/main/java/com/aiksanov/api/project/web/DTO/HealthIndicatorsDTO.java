package com.aiksanov.api.project.web.DTO;

import com.aiksanov.api.project.data.domain.HealthStatus;
import com.aiksanov.api.project.data.entity.HealthIndicators;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class HealthIndicatorsDTO {
    private Map<String, Map<String, Integer>> statuses;

    public HealthIndicatorsDTO() {
    }

    public HealthIndicatorsDTO(List<HealthIndicators> overallStatus, List<HealthIndicators> scheduleStatus,
                               List<HealthIndicators> scopeStatus, List<HealthIndicators> qualityStatus,
                               List<HealthIndicators> costStatus) {
        statuses = new HashMap<>();
        this.statuses.put(HealthStatus.OVERALL.getLabel(), createStatusMap(overallStatus));
        this.statuses.put(HealthStatus.SCHEDULE.getLabel(), createStatusMap(scheduleStatus));
        this.statuses.put(HealthStatus.SCOPE.getLabel(), createStatusMap(scopeStatus));
        this.statuses.put(HealthStatus.QUALITY.getLabel(), createStatusMap(qualityStatus));
        this.statuses.put(HealthStatus.COST.getLabel(), createStatusMap(costStatus));
    }

    private Map<String, Integer> createStatusMap(List<HealthIndicators> indicators){
        Map<String, Integer> map = new HashMap<>();
        if (Objects.nonNull(indicators)) {
            for (HealthIndicators indicator : indicators) {
                map.put(indicator.getHealthIndicatorsPK().getModificationDate().toString(), indicator.getStatus());
            }
        }
        return map;
    }

    public Map<String, Map<String, Integer>> getStatuses() {
        return statuses;
    }
}
