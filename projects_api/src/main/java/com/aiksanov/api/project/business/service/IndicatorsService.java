package com.aiksanov.api.project.business.service;

import com.aiksanov.api.project.data.entity.IndicatorsReqs;
import com.aiksanov.api.project.data.entity.Milestone;
import com.aiksanov.api.project.data.entity.QualityIndicators;
import com.aiksanov.api.project.data.entity.pk.QualityIndicatorsPK;
import com.aiksanov.api.project.data.repository.IndicatorsReqsRepository;
import com.aiksanov.api.project.data.repository.QualityIndicatorsCommentsRepository;
import com.aiksanov.api.project.data.repository.QualityIndicatorsRepository;
import com.aiksanov.api.project.web.DTO.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class IndicatorsService {
    private MilestoneService milestoneService;
    private HealthService healthService;
    private IndicatorsReqsRepository indicatorsReqsRepository;
    private QualityIndicatorsRepository qualityRepository;

    public IndicatorsService() {
    }

    @Autowired
    public IndicatorsService(MilestoneService milestoneService,
                             HealthService healthService,
                             IndicatorsReqsRepository indicatorsReqsRepository,
                             QualityIndicatorsRepository qualityRepository
    )
    {
        this.milestoneService = milestoneService;
        this.healthService = healthService;
        this.indicatorsReqsRepository = indicatorsReqsRepository;
        this.qualityRepository = qualityRepository;
    }
    //TODO: Enums to avoid strings as pk fields
    public IndicatorsDTO getIndicatorsDTO(int projectID){
        List<MilestoneDTO> milestones = this.milestoneService.getShownMilestonesByProjectID(projectID);
        HealthIndicatorsDTO indicators = this.healthService.getHealthIndicators(projectID);
        IndicatorsReqDTO reqDTO = getRqDTO(projectID);
        QualityIndicatorsTableDTO quality = getQuality(projectID);
        return new IndicatorsDTO(milestones, indicators, reqDTO, quality, new Date());
    }

    private IndicatorsReqDTO getRqDTO(int projectID) {
        IndicatorsReqs rqs = this.indicatorsReqsRepository.findById(projectID).get();
        Milestone dr1 = this.milestoneService.getProjectMilestoneById(projectID, "DR1");
        if (Objects.isNull(dr1)) {
            dr1 = new Milestone();
        }
        return new IndicatorsReqDTO(rqs, dr1.getActualDate());
    }

    private QualityIndicatorsTableDTO getQuality(int projectID){
        QualityIndicators quality = this.qualityRepository.findById(
                new QualityIndicatorsPK(1, "quality", 1)
        ).orElse(new QualityIndicators());

        QualityIndicators defects = this.qualityRepository.findById(
                new QualityIndicatorsPK(1, "defects", 1)
        ).orElse(new QualityIndicators());

        QualityIndicators backlog = this.qualityRepository.findById(
                new QualityIndicatorsPK(1, "backlog", 1)
        ).orElse(new QualityIndicators());

        List<QualityIndicators> testExecution = this.qualityRepository.getAllByProjectIDAndKpiID(projectID, "execution");
        List<QualityIndicators> testRate = this.qualityRepository.getAllByProjectIDAndKpiID(projectID, "rate");

        List<QualityIndicatorDTO> executionDTO = testExecution.stream()
                .map(QualityIndicatorDTO::new)
                .collect(Collectors.toList());

        List<QualityIndicatorDTO> rateDTO = testRate.stream()
                .map(QualityIndicatorDTO::new)
                .collect(Collectors.toList());

        QualityIndicatorsTableDTO dto = new QualityIndicatorsTableDTO();
        dto.setQuality(new QualityIndicatorDTO(quality));
        dto.setDefects(new QualityIndicatorDTO(defects));
        dto.setBacklog(new QualityIndicatorDTO(backlog));
        dto.setTestExecution(executionDTO);
        dto.setTestRate(rateDTO);

        return dto;
    }
}
