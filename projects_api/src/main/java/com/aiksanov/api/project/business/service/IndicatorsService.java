package com.aiksanov.api.project.business.service;

import com.aiksanov.api.project.data.entity.IndicatorsReqs;
import com.aiksanov.api.project.data.entity.Milestone;
import com.aiksanov.api.project.data.entity.QualityIndicators;
import com.aiksanov.api.project.data.entity.pk.MilestonePK;
import com.aiksanov.api.project.data.entity.pk.QualityIndicatorsPK;
import com.aiksanov.api.project.data.repository.IndicatorsReqsRepository;
import com.aiksanov.api.project.data.repository.MilestoneRepository;
import com.aiksanov.api.project.data.repository.QualityIndicatorsRepository;
import com.aiksanov.api.project.web.DTO.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class IndicatorsService {
    private MilestoneService milestoneService;
    private HealthService healthService;
    private IndicatorsReqsRepository indicatorsReqsRepository;
    private QualityIndicatorsRepository qualityRepository;
    private MilestoneRepository milestoneRepository;

    public IndicatorsService() {
    }

    @Autowired
    public IndicatorsService(MilestoneService milestoneService,
                             HealthService healthService,
                             IndicatorsReqsRepository indicatorsReqsRepository,
                             QualityIndicatorsRepository qualityRepository,
                             MilestoneRepository milestoneRepository
    ) {
        this.milestoneService = milestoneService;
        this.healthService = healthService;
        this.indicatorsReqsRepository = indicatorsReqsRepository;
        this.qualityRepository = qualityRepository;
        this.milestoneRepository = milestoneRepository;
    }

    //TODO: To remove
    public IndicatorsDTO getIndicatorsDTO(int projectID) {
        List<MilestoneDTO> milestones = this.milestoneService.getShownMilestonesByProjectID(projectID);
        HealthIndicatorsDTO indicators = this.healthService.getHealthIndicators(projectID);
        IndicatorsReqDTO reqDTO = getRqDTO(projectID);
        QualityIndicatorsTableDTO quality = getQuality(projectID);
        return new IndicatorsDTO(milestones, indicators, reqDTO, quality, new Date());
    }

    public IndicatorsReqDTO getRqDTO(int projectID) {
        IndicatorsReqs rqs = this.indicatorsReqsRepository.findById(projectID).orElseGet(IndicatorsReqs::new);
        Milestone dr1 = this.milestoneService.getProjectMilestoneById(projectID, "DR1");
        if (Objects.isNull(dr1)) {
            dr1 = new Milestone();
        }
        return new IndicatorsReqDTO(rqs, dr1.getActualDate());
    }

    public List<MilestoneIndKpiDTO> getKpiMilestones(int projectID) {
        if (isNoDr1(projectID)) {
            return new ArrayList<>();
        }

        String[] milestonesToShow = {"TR", "DR4", "DR5", "CI"};
        ArrayList<MilestoneIndKpiDTO> milestoneKpis = (ArrayList<MilestoneIndKpiDTO>) Arrays.stream(milestonesToShow)
                .filter((label) -> this.milestoneRepository.existsById(new MilestonePK(projectID, label)))
                .map((label) -> getMilestoneKpiDTO(projectID, label))
                .collect(Collectors.toList());

        return milestoneKpis;
    }

    private boolean isNoDr1(int projectID) {
        return !this.milestoneRepository.existsById(new MilestonePK(projectID, "DR1"));
    }

    private MilestoneIndKpiDTO getMilestoneKpiDTO(int projectID, String label) {
        Milestone dr1 = this.milestoneRepository.findById(new MilestonePK(projectID, "DR1")).get();
        Milestone current = this.milestoneRepository.findById(new MilestonePK(projectID, label)).get();

        MilestoneIndKpiDTO dto = new MilestoneIndKpiDTO();

        try {
            LocalDateTime currentActualDate = current.getActualDate().toLocalDate().atStartOfDay();
            LocalDateTime currentBaselineDate = current.getBaselineDate().toLocalDate().atStartOfDay();
            LocalDateTime dr1ActualDate = dr1.getActualDate().toLocalDate().atStartOfDay();

            dto.setAdherence(getScheduleAdherence(currentActualDate, dr1ActualDate, currentBaselineDate));
            dto.setDelay(getDelay(currentBaselineDate, currentActualDate));
            dto.setDuration(getDuration(dr1ActualDate, currentActualDate));
        } catch (NullPointerException e) {
            //TODO: think what to do (actually just need to suppress)
        } finally {
            dto.setLabel(current.getMilestonePK().getLabel());
        }
        return dto;
    }

    private float getScheduleAdherence(LocalDateTime currentActualDate, LocalDateTime dr1ActualDate, LocalDateTime currentBaselineDate) {
        return (float)(Duration.between(currentActualDate, dr1ActualDate)).toDays() /
                (Duration.between(currentBaselineDate, dr1ActualDate)).toDays();
    }

    private long getDelay(LocalDateTime currentBaselineDate, LocalDateTime currentActualDate) {
        return Duration.between(currentBaselineDate, currentActualDate).toDays();
    }

    private long getDuration(LocalDateTime dr1ActualDate, LocalDateTime currentActualDate) {
        return Duration.between(dr1ActualDate, currentActualDate).toDays();
    }

    private QualityIndicatorsTableDTO getQuality(int projectID) {
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
