package com.aiksanov.api.project.business.service;

import com.aiksanov.api.project.data.domain.HealthStatus;
import com.aiksanov.api.project.data.entity.HealthIndicators;
import com.aiksanov.api.project.data.entity.HealthIndicatorsComments;
import com.aiksanov.api.project.data.entity.pk.HealthIndicatorsCommentsPK;
import com.aiksanov.api.project.data.entity.pk.HealthIndicatorsPK;
import com.aiksanov.api.project.data.repository.HealthCommentsRepository;
import com.aiksanov.api.project.data.repository.HealthRepository;
import com.aiksanov.api.project.web.DTO.HealthIndicatorsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static com.aiksanov.api.project.data.domain.HealthStatus.*;

@Service
public class HealthService {
    private HealthRepository healthRepository;
    private HealthCommentsRepository commentsRepository;

    @Autowired
    public HealthService(HealthRepository healthRepository, HealthCommentsRepository commentsRepository) {
        this.healthRepository = healthRepository;
        this.commentsRepository = commentsRepository;
    }

    public HealthIndicatorsDTO getHealthIndicators(Integer projectID) {
        List<HealthIndicators> lastTwoHealthStates = this.healthRepository.lastTwoHealthStates(projectID);
        if (Objects.isNull(lastTwoHealthStates)) {
            lastTwoHealthStates = new ArrayList<>();
        }
        Map<String, String> comments = new HashMap<>();

        comments.put(OVERALL.getLabel(), getCommentString(OVERALL, projectID));
        comments.put(SCHEDULE.getLabel(), getCommentString(SCHEDULE, projectID));
        comments.put(SCOPE.getLabel(), getCommentString(SCOPE, projectID));
        comments.put(QUALITY.getLabel(), getCommentString(QUALITY, projectID));
        comments.put(COST.getLabel(), getCommentString(COST, projectID));

        return new HealthIndicatorsDTO(lastTwoHealthStates, comments);
    }

    private String getCommentString(HealthStatus statusName, int projectID) {
        String comment = this.commentsRepository.findByPk(new HealthIndicatorsCommentsPK(projectID, statusName.getLabel()))
                .orElseGet(HealthIndicatorsComments::new).getComment();
        return Objects.nonNull(comment) ? comment : "";
    }

    public void saveHealthIndicators(HealthIndicatorsDTO indicatorsDTOs, Integer projectID) {
        Map<String, HealthIndicators> indicators = indicatorsDTOs.getStatuses();
        if (Objects.nonNull(indicators)) {
            HealthIndicators current = indicators.get("current");
            current.setHealthIndicatorsPK(new HealthIndicatorsPK(projectID, getSqlDateNow()));
            this.healthRepository.save(current);
        }
    }

    public void saveHealthComments(HealthIndicatorsDTO indicatorsDTOs, Integer projectID) {
        Map<String, String> commentsMap = indicatorsDTOs.getComments();
        if (Objects.nonNull(commentsMap)) {
            Set<String> keys = commentsMap.keySet();
            ArrayList<HealthIndicatorsComments> comments = (ArrayList<HealthIndicatorsComments>) keys.stream()
                    .map((label) -> {
                        HealthIndicatorsComments comment = new HealthIndicatorsComments();
                        comment.setPk(new HealthIndicatorsCommentsPK(projectID, label));
                        comment.setComment(commentsMap.get(label));
                        return comment;
                    }).collect(Collectors.toList());
            this.commentsRepository.saveAll(comments);
        }
    }

    private Date getSqlDateNow() {
        LocalDate localDate = LocalDate.now();
        return Date.valueOf(localDate.toString());
    }

//    public void saveHealthIndicators(Integer projectID, HealthIndicatorsDTO indicatorsDTOs){
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
//    }
}
