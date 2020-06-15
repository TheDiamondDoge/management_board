package com.aiksanov.api.project.business.service;

import com.aiksanov.api.project.util.Utils;
import com.aiksanov.api.project.util.enums.HealthStatus;
import com.aiksanov.api.project.data.entity.HealthIndicators;
import com.aiksanov.api.project.data.entity.HealthIndicatorsComments;
import com.aiksanov.api.project.data.entity.pk.HealthIndicatorsCommentsPK;
import com.aiksanov.api.project.data.entity.pk.HealthIndicatorsPK;
import com.aiksanov.api.project.data.repository.HealthCommentsRepository;
import com.aiksanov.api.project.data.repository.HealthRepository;
import com.aiksanov.api.project.web.DTO.healthIndicators.HealthIndicatorsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.rmi.CORBA.Util;
import java.time.LocalDate;
import java.util.Date;
import java.util.*;
import java.util.stream.Collectors;

import static com.aiksanov.api.project.util.enums.HealthStatus.*;

@RequiredArgsConstructor
@Service
public class HealthService {
    private final HealthRepository healthRepository;
    private final HealthCommentsRepository commentsRepository;
    private final ProjectGeneralService generalService;

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
            HealthIndicators current = indicators.get(CURRENT.getLabel());
            current.setHealthIndicatorsPK(new HealthIndicatorsPK(projectID, Utils.getSqlDateNow()));
            this.healthRepository.save(current);
            this.generalService.modifyWorkspaceUpdatedBy(projectID, "TestUpdHealth");
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
}
