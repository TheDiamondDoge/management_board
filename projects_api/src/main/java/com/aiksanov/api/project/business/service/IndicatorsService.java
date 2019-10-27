package com.aiksanov.api.project.business.service;

import com.aiksanov.api.project.data.entity.IndicatorsReqs;
import com.aiksanov.api.project.data.entity.Milestone;
import com.aiksanov.api.project.data.entity.QualityIndicators;
import com.aiksanov.api.project.data.entity.pk.MilestonePK;
import com.aiksanov.api.project.data.entity.pk.QualityIndicatorsPK;
import com.aiksanov.api.project.data.repository.GeneralRepository;
import com.aiksanov.api.project.data.repository.IndicatorsReqsRepository;
import com.aiksanov.api.project.data.repository.MilestoneRepository;
import com.aiksanov.api.project.data.repository.QualityIndicatorsRepository;
import com.aiksanov.api.project.web.DTO.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

//TODO: projectID exists??? check!
@Service
public class IndicatorsService {
    private MilestoneService milestoneService;
    private HealthService healthService;
    private IndicatorsReqsRepository indicatorsReqsRepository;
    private QualityIndicatorsRepository qualityRepository;
    private MilestoneRepository milestoneRepository;
    private GeneralRepository generalRepository;

    public IndicatorsService() {
    }

    @Autowired
    public IndicatorsService(MilestoneService milestoneService,
                             HealthService healthService,
                             IndicatorsReqsRepository indicatorsReqsRepository,
                             QualityIndicatorsRepository qualityRepository,
                             MilestoneRepository milestoneRepository,
                             GeneralRepository generalRepository
    ) {
        this.milestoneService = milestoneService;
        this.healthService = healthService;
        this.indicatorsReqsRepository = indicatorsReqsRepository;
        this.qualityRepository = qualityRepository;
        this.milestoneRepository = milestoneRepository;
        this.generalRepository = generalRepository;
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
        return Arrays.stream(milestonesToShow)
                .filter((label) -> this.milestoneRepository.existsById(new MilestonePK(projectID, label)))
                .map((label) -> getMilestoneKpiDTO(projectID, label))
                .collect(Collectors.toList());
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
        return (float) (Duration.between(currentActualDate, dr1ActualDate)).toDays() /
                (Duration.between(currentBaselineDate, dr1ActualDate)).toDays();
    }

    private long getDelay(LocalDateTime currentBaselineDate, LocalDateTime currentActualDate) {
        return Duration.between(currentBaselineDate, currentActualDate).toDays();
    }

    private long getDuration(LocalDateTime dr1ActualDate, LocalDateTime currentActualDate) {
        return Duration.between(dr1ActualDate, currentActualDate).toDays();
    }

    public IndicatorsDr4KpiDTO getDr4Kpi(int projectID) {
        if (isProjectNotExist(projectID)) {
            return new IndicatorsDr4KpiDTO();
        }

        Milestone dr1 = this.milestoneRepository.findById(new MilestonePK(1, "DR1")).orElse(new Milestone());
        Milestone dr4 = this.milestoneRepository.findById(new MilestonePK(1, "DR4")).orElse(new Milestone());

        IndicatorsDr4KpiDTO dto = new IndicatorsDr4KpiDTO();
        dto.setYear(Calendar.getInstance().get(Calendar.YEAR));

        try {
            LocalDateTime dr1ActualDate = dr1.getActualDate().toLocalDate().atStartOfDay();
            LocalDateTime dr4ActualDate = dr4.getActualDate().toLocalDate().atStartOfDay();
            LocalDateTime dr4BaselineDate = dr4.getActualDate().toLocalDate().atStartOfDay();

            dto.setScheduleAdherence(getScheduleAdherence(dr4ActualDate, dr1ActualDate, dr4BaselineDate));
        } catch (NullPointerException e){
            dto.setScheduleAdherence(null);
        }
        dto.setContentAdherence(getContentAdherence(projectID));
        dto.setRqsChange(getRqsChange(projectID));
        dto.setCostAdherence(1f);
        return dto;
    }

    private float getRqsChange(int projectID) {
        IndicatorsReqs rqs = this.indicatorsReqsRepository.findById(projectID).orElseGet(IndicatorsReqs::new);
        int added = rqs.getAddedAfterDr1();
        int removed = rqs.getRemovedAfterDr1();
        int modified = rqs.getModifiedAfterDr1();
        int committed = rqs.getCommittedAtDr1();

        return (float) (added + removed + modified) / committed;
    }

    private float getContentAdherence(int projectID) {
        IndicatorsReqs rqs = this.indicatorsReqsRepository.findById(projectID).orElseGet(IndicatorsReqs::new);
        int committed = rqs.getCommittedAtDr1();
        int removed = rqs.getRemovedAfterDr1();
        int modified = rqs.getModifiedAfterDr1();

        return (float) (committed - removed - modified) / committed;
    }

    public QualityIndicatorsTableDTO getQuality(int projectID) {
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
        dto.setSyncDate("2019-10-11");

        return dto;
    }

    @Transactional
    public void saveIndicatorsRqs(IndicatorsReqDTO dto, int projectID) {
        IndicatorsReqs rqs = new IndicatorsReqs();
        rqs.setProjectID(projectID);
        rqs.setCommittedAtDr1(dto.getCommittedAtDr1());
        rqs.setAddedAfterDr1(dto.getAddedAfterDr1());
        rqs.setRemovedAfterDr1(dto.getRemovedAfterDr1());
        rqs.setModifiedAfterDr1(dto.getModifiedAfterDr1());

        this.indicatorsReqsRepository.save(rqs);
    }

    public void saveQuality(QualityIndicatorsTableDTO dto, int projectID) {
        //TODO extract comment from list; testExecution: {arr []; comment: ""}
        System.out.println(dto);
    }

    private boolean isProjectNotExist(int projectID) {
        return !this.generalRepository.existsById(projectID);
    }

    private boolean isDr1Exists(int projectID) {
        return this.milestoneRepository.existsById(new MilestonePK(projectID, "DR1"));
    }
}

