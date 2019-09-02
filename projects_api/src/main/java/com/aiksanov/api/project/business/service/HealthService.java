package com.aiksanov.api.project.business.service;

import com.aiksanov.api.project.data.domain.HealthStatus;
import com.aiksanov.api.project.data.entity.HealthIndicators;
import com.aiksanov.api.project.data.entity.HealthIndicatorsComments;
import com.aiksanov.api.project.data.entity.HealthIndicatorsCommentsPK;
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
        Map<HealthStatus, HealthIndicatorsComments> comments = new HashMap<>();

        comments.put(OVERALL, this.commentsRepository.findByPk(new HealthIndicatorsCommentsPK(projectID, OVERALL.getLabel())));
        comments.put(SCHEDULE, this.commentsRepository.findByPk(new HealthIndicatorsCommentsPK(projectID, SCHEDULE.getLabel())));
        comments.put(SCOPE, this.commentsRepository.findByPk(new HealthIndicatorsCommentsPK(projectID, SCOPE.getLabel())));
        comments.put(QUALITY, this.commentsRepository.findByPk(new HealthIndicatorsCommentsPK(projectID, QUALITY.getLabel())));
        comments.put(COST, this.commentsRepository.findByPk(new HealthIndicatorsCommentsPK(projectID, COST.getLabel())));

        return new HealthIndicatorsDTO(lastTwoHealthStates, comments);
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
