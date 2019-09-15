package com.aiksanov.api.project.business.service;

import com.aiksanov.api.project.data.domain.HealthStatus;
import com.aiksanov.api.project.data.entity.HealthIndicators;
import com.aiksanov.api.project.data.entity.pk.HealthIndicatorsCommentsPK;
import com.aiksanov.api.project.data.repository.HealthCommentsRepository;
import com.aiksanov.api.project.data.repository.HealthRepository;
import com.aiksanov.api.project.web.DTO.HealthIndicatorsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.aiksanov.api.project.data.domain.HealthStatus.*;

@Service
public class HealthService {
    private HealthRepository healthRepository;
    private HealthCommentsRepository commentsRepository;

    @Autowired
    public HealthService(HealthRepository healthRepository,  HealthCommentsRepository commentsRepository) {
        this.healthRepository = healthRepository;
        this.commentsRepository = commentsRepository;
    }

    public HealthIndicatorsDTO getHealthIndicators(Integer projectID){
        List<HealthIndicators> lastTwoHealthStates = this.healthRepository.lastTwoHealthStates(projectID);
        Map<String, String> comments = new HashMap<>();

        comments.put(OVERALL.getLabel(), getCommentString(OVERALL, projectID));
        comments.put(SCHEDULE.getLabel(), getCommentString(SCHEDULE, projectID));
        comments.put(SCOPE.getLabel(), getCommentString(SCOPE, projectID));
        comments.put(QUALITY.getLabel(), getCommentString(QUALITY, projectID));
        comments.put(COST.getLabel(), getCommentString(COST, projectID));

        return new HealthIndicatorsDTO(lastTwoHealthStates, comments);
    }

    private String getCommentString(HealthStatus statusName, int projectID){
        return this.commentsRepository.findByPk(new HealthIndicatorsCommentsPK(projectID, statusName.getLabel())).getComment();
    }

    public void saveHealthIndicators(Integer projectID, HealthIndicatorsDTO indicatorsDTOs){
//        List<HealthIndicators> indicators = new ArrayList<>();
//        Map<String, Map<String, Integer>> statuses = indicatorsDTOs.getStatuses();
//        for (String statusLabel : statuses.keySet()){
//            Map<String, Integer> status = statuses.get(statusLabel);
//            if (status.isEmpty()){
//                continue;
//            }
//
//            Set<String> dateStrings = status.keySet();
//            if (dateStrings.isEmpty()){
//                continue;
//            }
//            String dateString = dateStrings.iterator().next();
//            Date date = Date.valueOf(dateString);
//            HealthIndicatorsPK pk = new HealthIndicatorsPK(projectID, statusLabel, date);
//            indicators.add(new HealthIndicators(pk, status.get(dateString)));
//        }
//
//        this.healthRepository.saveAll(indicators);
    }
}
