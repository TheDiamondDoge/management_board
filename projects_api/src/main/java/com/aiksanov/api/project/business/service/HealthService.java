package com.aiksanov.api.project.business.service;

import com.aiksanov.api.project.data.domain.HealthStatus;
import com.aiksanov.api.project.data.entity.HealthIndicators;
import com.aiksanov.api.project.data.entity.HealthIndicatorsPK;
import com.aiksanov.api.project.data.repository.HealthRepository;
import com.aiksanov.api.project.web.DTO.HealthIndicatorsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class HealthService {
    private HealthRepository healthRepository;

    @Autowired
    public HealthService(HealthRepository healthRepository) {
        this.healthRepository = healthRepository;
    }

    public HealthIndicatorsDTO getHealthIndicators(Integer projectID){
        List<HealthIndicators> overall = this.healthRepository.findAllByHealthIndicatorsPK(projectID, HealthStatus.OVERALL.getLabel());
        List<HealthIndicators> schedule = this.healthRepository.findAllByHealthIndicatorsPK(projectID, HealthStatus.SCHEDULE.getLabel());
        List<HealthIndicators> scope = this.healthRepository.findAllByHealthIndicatorsPK(projectID, HealthStatus.SCOPE.getLabel());
        List<HealthIndicators> quality = this.healthRepository.findAllByHealthIndicatorsPK(projectID, HealthStatus.QUALITY.getLabel());
        List<HealthIndicators> cost = this.healthRepository.findAllByHealthIndicatorsPK(projectID, HealthStatus.COST.getLabel());

        return new HealthIndicatorsDTO(overall, schedule, scope, quality, cost);
    }

    public void saveHealthIndicators(Integer projectID, HealthIndicatorsDTO indicatorsDTOs){
        List<HealthIndicators> indicators = new ArrayList<>();
        Map<String, Map<String, Integer>> statuses = indicatorsDTOs.getStatuses();
        for (String statusLabel : statuses.keySet()){
            Map<String, Integer> status = statuses.get(statusLabel);
            if (status.isEmpty()){
                continue;
            }

            Set<String> dateStrings = status.keySet();
            if (dateStrings.isEmpty()){
                continue;
            }
            String dateString = dateStrings.iterator().next();
            Date date = Date.valueOf(dateString);
            HealthIndicatorsPK pk = new HealthIndicatorsPK(projectID, statusLabel, date);
            indicators.add(new HealthIndicators(pk, status.get(dateString)));
        }

        this.healthRepository.saveAll(indicators);
    }
}
