package com.aiksanov.api.project.business.service;

import com.aiksanov.api.project.data.entity.IndicatorsReqs;
import com.aiksanov.api.project.data.entity.Milestone;
import com.aiksanov.api.project.data.repository.IndicatorsReqsRepository;
import com.aiksanov.api.project.web.DTO.HealthIndicatorsDTO;
import com.aiksanov.api.project.web.DTO.IndicatorsDTO;
import com.aiksanov.api.project.web.DTO.IndicatorsReqDTO;
import com.aiksanov.api.project.web.DTO.MilestoneDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class IndicatorsService {
    private MilestoneService milestoneService;
    private HealthService healthService;
    private IndicatorsReqsRepository indicatorsReqsRepository;

    public IndicatorsService() {
    }

    @Autowired
    public IndicatorsService(MilestoneService milestoneService, HealthService healthService,
                             IndicatorsReqsRepository indicatorsReqsRepository)
    {
        this.milestoneService = milestoneService;
        this.healthService = healthService;
        this.indicatorsReqsRepository = indicatorsReqsRepository;
    }

    public IndicatorsDTO getIndicatorsDTO(int projectID){
        List<MilestoneDTO> milestones = this.milestoneService.getMilestonesByProjectID(projectID);
        HealthIndicatorsDTO indicators = this.healthService.getHealthIndicators(projectID);
        IndicatorsReqDTO reqDTO = getRqDTO(projectID);
        return new IndicatorsDTO(milestones, indicators, reqDTO);
    }

    private IndicatorsReqDTO getRqDTO(int projectID) {
        IndicatorsReqs rqs = this.indicatorsReqsRepository.findById(projectID).get();
        Milestone dr1 = this.milestoneService.getProjectMilestoneById(projectID, "DR1");
        if (Objects.isNull(dr1)) {
            dr1 = new Milestone();
        }
        return new IndicatorsReqDTO(rqs, dr1.getActualDate());
    }
}
