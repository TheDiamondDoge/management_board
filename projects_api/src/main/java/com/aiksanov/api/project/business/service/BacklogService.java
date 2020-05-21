package com.aiksanov.api.project.business.service;

import com.aiksanov.api.project.data.entity.EcmaBacklogTarget;
import com.aiksanov.api.project.data.repository.EcmaBacklogTargetRepo;
import com.aiksanov.api.project.web.DTO.BacklogDefectsChartDTO;
import com.aiksanov.api.project.web.DTO.information.EcmaBacklogTargetDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;


@Service
public class BacklogService {
    private final EcmaBacklogTargetRepo ecmaBacklogTargetRepo;

    private String CHART_URL = "http://localhost:8100/general/chart";

    @Autowired
    public BacklogService(EcmaBacklogTargetRepo ecmaBacklogTargetRepo) {
        this.ecmaBacklogTargetRepo = ecmaBacklogTargetRepo;
    }

    public BacklogDefectsChartDTO getChartData(int projectId) {
        String finalUrl = CHART_URL + "/" + "backlog/" + projectId;
        RestTemplate restTemplate = new RestTemplate();
        BacklogDefectsChartDTO dto = restTemplate.getForObject(finalUrl, BacklogDefectsChartDTO.class);
        if (Objects.nonNull(dto)) {
            dto.setTarget(getBacklogTarget(projectId));
        }

        return dto;
    }

    public EcmaBacklogTargetDTO getBacklogTarget(int projectId) {
        String[] milestones = {"DR5", "DR4", "TR", "CI"};
        List<EcmaBacklogTarget> targets = this.ecmaBacklogTargetRepo.findAllByProjectId(projectId);
        for (String milestone : milestones) {
            for (EcmaBacklogTarget target : targets) {
                if (Objects.nonNull(target) && target.getLabel().equalsIgnoreCase(milestone)) {
                    return new EcmaBacklogTargetDTO(target);
                }
            }
        }

        return null;
    }
}