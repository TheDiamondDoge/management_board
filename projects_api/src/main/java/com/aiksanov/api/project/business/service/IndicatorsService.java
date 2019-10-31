package com.aiksanov.api.project.business.service;

import com.aiksanov.api.project.data.entity.IndicatorsReqs;
import com.aiksanov.api.project.data.entity.Milestone;
import com.aiksanov.api.project.data.entity.QualityIndicators;
import com.aiksanov.api.project.data.entity.QualityIndicatorsComments;
import com.aiksanov.api.project.data.entity.pk.MilestonePK;
import com.aiksanov.api.project.data.entity.pk.QualityIndicatorsCommentsPK;
import com.aiksanov.api.project.data.repository.*;
import com.aiksanov.api.project.web.DTO.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
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
    private QualityIndicatorsCommentsRepository commentsRepository;
    private MilestoneRepository milestoneRepository;
    private GeneralRepository generalRepository;

    public IndicatorsService() {
    }

    @Autowired
    public IndicatorsService(MilestoneService milestoneService,
                             HealthService healthService,
                             IndicatorsReqsRepository indicatorsReqsRepository,
                             QualityIndicatorsRepository qualityRepository,
                             QualityIndicatorsCommentsRepository commentsRepository,
                             MilestoneRepository milestoneRepository,
                             GeneralRepository generalRepository
    ) {
        this.milestoneService = milestoneService;
        this.healthService = healthService;
        this.indicatorsReqsRepository = indicatorsReqsRepository;
        this.qualityRepository = qualityRepository;
        this.commentsRepository = commentsRepository;
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
        } catch (NullPointerException e) {
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
        return new QualityIndicatorsTableDTO(
                getQualityRow(projectID, "quality"),
                getQualityRow(projectID, "defects"),
                getQualityRow(projectID, "backlog"),
                getQualityRow(projectID, "execution"),
                getQualityRow(projectID, "rate"),
                "2019-10-11"
        );
    }

    public Iterable<QualityIndicatorsComments> getAll() {
        return this.commentsRepository.findAll();
    }

    private List<QualityIndicatorDTO> getQualityRow(int projectID, String kpiID) {
        List<QualityIndicators> list = this.qualityRepository.getAllByProjectIDAndKpiID(projectID, kpiID);
        QualityIndicatorsComments comment = this.commentsRepository
                .findById(new QualityIndicatorsCommentsPK(projectID, kpiID))
                .orElseGet(QualityIndicatorsComments::new);
        return list.stream().map(indicator -> new QualityIndicatorDTO(indicator, comment)).collect(Collectors.toList());
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
    //TODO map comments to indicators as one to many using project id and kpiID only
    @Transactional
    public void saveQuality(QualityIndicatorsTableDTO dto, int projectID) {
        List<QualityIndicators> quality = buildQualityIndicators(dto.getQuality(), projectID, "quality");
        List<QualityIndicators> defects = buildQualityIndicators(dto.getDefects(), projectID, "defects");
        List<QualityIndicators> backlog = buildQualityIndicators(dto.getBacklog(), projectID, "backlog");
        List<QualityIndicators> testExecution = buildQualityIndicators(dto.getTestExecution(), projectID, "execution");
        List<QualityIndicators> testRate = buildQualityIndicators(dto.getQuality(), projectID, "rate");

        QualityIndicatorsComments qualityComment = buildQualityComment(dto.getQuality(), projectID, "quality");
        QualityIndicatorsComments defectComment = buildQualityComment(dto.getDefects(), projectID, "defects");
        QualityIndicatorsComments backlogComment = buildQualityComment(dto.getBacklog(), projectID, "backlog");
        QualityIndicatorsComments testExecutionComment = buildQualityComment(dto.getTestExecution(), projectID, "execution");
        QualityIndicatorsComments testRateComment = buildQualityComment(dto.getTestRate(), projectID, "rate");

        List<QualityIndicators> indicatorsToSave = new ArrayList<>();
        indicatorsToSave.addAll(quality);
        indicatorsToSave.addAll(defects);
        indicatorsToSave.addAll(backlog);
        indicatorsToSave.addAll(testExecution);
        indicatorsToSave.addAll(testRate);

        List<QualityIndicatorsComments> commentsToSave = new ArrayList<>();
        commentsToSave.add(qualityComment);
        commentsToSave.add(defectComment);
        commentsToSave.add(backlogComment);
        commentsToSave.add(testExecutionComment);
        commentsToSave.add(testRateComment);

        this.qualityRepository.deleteAllByProjectID(projectID);
        this.commentsRepository.deleteAllByProjectID(projectID);

        this.qualityRepository.saveAll(indicatorsToSave);
        this.commentsRepository.saveAll(commentsToSave);
    }

    private List<QualityIndicators> buildQualityIndicators(List<QualityIndicatorDTO> dtos, int projectID, String kpiId) {
        List<QualityIndicators> result = new ArrayList<>();
        for (int i = 0; i < dtos.size(); i++) {
            result.add(
                    new QualityIndicators(projectID, kpiId, i, dtos.get(i).getObjective(), dtos.get(i).getActual()));
        }

        return result;
    }

    private QualityIndicatorsComments buildQualityComment(List<QualityIndicatorDTO> dtos, int projectID, String kpiID) {
        String comment = "";
        if (Objects.nonNull(dtos) && dtos.size() > 0) {
            comment = dtos.get(0).getComment();
        }

        return new QualityIndicatorsComments(projectID, kpiID, comment);
    }

    private boolean isProjectNotExist(int projectID) {
        return !this.generalRepository.existsById(projectID);
    }

    private boolean isDr1Exists(int projectID) {
        return this.milestoneRepository.existsById(new MilestonePK(projectID, "DR1"));
    }
}

