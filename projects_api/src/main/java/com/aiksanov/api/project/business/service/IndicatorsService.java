package com.aiksanov.api.project.business.service;

import com.aiksanov.api.project.data.entity.*;
import com.aiksanov.api.project.data.entity.pk.MilestonePK;
import com.aiksanov.api.project.data.entity.pk.QualityIndicatorsCommentsPK;
import com.aiksanov.api.project.data.repository.*;
import com.aiksanov.api.project.util.ServiceUtils;
import com.aiksanov.api.project.util.enums.MilestoneLabels;
import com.aiksanov.api.project.util.enums.QualityRowNames;
import com.aiksanov.api.project.util.enums.cost.CostStates;
import com.aiksanov.api.project.web.DTO.indicators.IndicatorsDr4KpiDTO;
import com.aiksanov.api.project.web.DTO.indicators.IndicatorsReqDTO;
import com.aiksanov.api.project.web.DTO.indicators.MilestoneIndKpiDTO;
import com.aiksanov.api.project.web.DTO.kpi.QualityIndicatorsAmountDTO;
import com.aiksanov.api.project.web.DTO.quality.QualityIndicatorDTO;
import com.aiksanov.api.project.web.DTO.quality.QualityIndicatorsTableDTO;
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
    private CostService costService;
    private KpiService kpiService;
    private IndicatorsReqsRepository indicatorsReqsRepository;
    private QualityIndicatorsRepository qualityRepository;
    private QualityIndicatorsCommentsRepository commentsRepository;
    private MilestoneRepository milestoneRepository;
    private ServiceUtils utils;

    public IndicatorsService() {
    }

    @Autowired
    public IndicatorsService(MilestoneService milestoneService, CostService costService, KpiService kpiService,
                             IndicatorsReqsRepository indicatorsReqsRepository, QualityIndicatorsRepository qualityRepository,
                             QualityIndicatorsCommentsRepository commentsRepository, MilestoneRepository milestoneRepository,
                             ServiceUtils utils)
    {
        this.milestoneService = milestoneService;
        this.costService = costService;
        this.kpiService = kpiService;
        this.indicatorsReqsRepository = indicatorsReqsRepository;
        this.qualityRepository = qualityRepository;
        this.commentsRepository = commentsRepository;
        this.milestoneRepository = milestoneRepository;
        this.utils = utils;
    }

    public IndicatorsReqDTO getRqDTO(int projectID) {
        IndicatorsReqs rqs = this.indicatorsReqsRepository.findById(projectID).orElseGet(IndicatorsReqs::new);
        Milestone dr1 = this.milestoneService.getProjectMilestoneById(projectID, MilestoneLabels.DR1.getLabel());
        if (Objects.isNull(dr1)) {
            dr1 = new Milestone();
        }
        return new IndicatorsReqDTO(rqs, dr1.getActualDate());
    }

    public List<MilestoneIndKpiDTO> getKpiMilestones(int projectID) {
        if (!utils.isDr1Exists(projectID)) {
            return new ArrayList<>();
        }

        String[] milestonesToShow = {"TR", "DR4", "DR5", "CI"};
        return Arrays.stream(milestonesToShow)
                .filter((label) -> this.milestoneRepository.existsById(new MilestonePK(projectID, label)))
                .map((label) -> getMilestoneKpiDTO(projectID, label))
                .collect(Collectors.toList());
    }

    private MilestoneIndKpiDTO getMilestoneKpiDTO(int projectID, String label) {
        Milestone dr1 = this.milestoneRepository.findById(new MilestonePK(projectID, MilestoneLabels.DR1.getLabel())).orElseGet(Milestone::new);
        Milestone current = this.milestoneRepository.findById(new MilestonePK(projectID, label)).orElseGet(Milestone::new);

        MilestoneIndKpiDTO dto = new MilestoneIndKpiDTO();

        try {
            LocalDateTime currentActualDate = current.getActualDate().toLocalDate().atStartOfDay();
            LocalDateTime currentBaselineDate = current.getBaselineDate().toLocalDate().atStartOfDay();
            LocalDateTime dr1ActualDate = dr1.getActualDate().toLocalDate().atStartOfDay();

            dto.setAdherence(getScheduleAdherence(currentActualDate, dr1ActualDate, currentBaselineDate));
            dto.setDelay(getDelay(currentBaselineDate, currentActualDate));
            dto.setDuration(getDuration(dr1ActualDate, currentActualDate));
        } catch (NullPointerException e) {
            //just catching NPE
        } finally {
            dto.setLabel(current.getMilestonePK().getLabel());
        }
        return dto;
    }

    private float getScheduleAdherence(LocalDateTime currentActualDate, LocalDateTime dr1ActualDate, LocalDateTime currentBaselineDate) {
        return (float) (Duration.between(currentActualDate, dr1ActualDate)).toDays() /
                (float) (Duration.between(currentBaselineDate, dr1ActualDate)).toDays();
    }

    private long getDelay(LocalDateTime currentBaselineDate, LocalDateTime currentActualDate) {
        return Duration.between(currentBaselineDate, currentActualDate).toDays();
    }

    private long getDuration(LocalDateTime dr1ActualDate, LocalDateTime currentActualDate) {
        return Duration.between(dr1ActualDate, currentActualDate).toDays();
    }

    public IndicatorsDr4KpiDTO getDr4Kpi(int projectID) {
        Milestone dr1 = this.milestoneRepository.findById(new MilestonePK(projectID, MilestoneLabels.DR1.getLabel()))
                .orElse(new Milestone());
        Milestone dr4 = this.milestoneRepository.findById(new MilestonePK(projectID, MilestoneLabels.DR4.getLabel()))
                .orElse(new Milestone());

        IndicatorsDr4KpiDTO dto = new IndicatorsDr4KpiDTO();
        dto.setYear(utils.getCurrentYear());

        try {
            LocalDateTime dr1ActualDate = dr1.getActualDate().toLocalDate().atStartOfDay();
            LocalDateTime dr4ActualDate = dr4.getActualDate().toLocalDate().atStartOfDay();
            LocalDateTime dr4BaselineDate = dr4.getBaselineDate().toLocalDate().atStartOfDay();

            dto.setScheduleAdherence(getScheduleAdherence(dr4ActualDate, dr1ActualDate, dr4BaselineDate));
        } catch (NullPointerException e) {
            dto.setScheduleAdherence(Float.NaN);
        }
        dto.setContentAdherence(getContentAdherence(projectID));
        dto.setRqsChange(getRqsChange(projectID));
        dto.setCostAdherence(getCostAdherence(projectID));
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

    private float getCostAdherence(int projectID) {
        List<Cost> costs = this.costService.getAllCostObjectsByPrjId(projectID);
        double committedSum = costs.stream()
                .filter(cost -> cost.getState() == CostStates.COMMITTED.getValue())
                .map(Cost::getValue)
                .reduce(0.0, Double::sum);
        double releasedSum = costs.stream()
                .filter(cost -> cost.getState() == CostStates.RELEASED.getValue())
                .map(Cost::getValue)
                .reduce(0.0, Double::sum);
        return (float) (releasedSum / committedSum);
    }

    public QualityIndicatorsTableDTO getQuality(int projectID) {
        QualityIndicatorsAmountDTO qualityIndicatorsAmountDTO = this.kpiService.getQualityIndicatorsValues(projectID);
        return new QualityIndicatorsTableDTO(
                getIndicatorWithKpi(projectID, QualityRowNames.QUALITY, qualityIndicatorsAmountDTO.getQuality()),
                getIndicatorWithKpi(projectID, QualityRowNames.DEFECTS, qualityIndicatorsAmountDTO.getDefects()),
                getIndicatorWithKpi(projectID, QualityRowNames.BACKLOG, qualityIndicatorsAmountDTO.getBacklog()),
                getQualityRow(projectID, QualityRowNames.EXECUTION),
                getQualityRow(projectID, QualityRowNames.RATE),
                "2000-12-12"
        );
    }

    private List<QualityIndicatorDTO> getIndicatorWithKpi(int projectID, QualityRowNames name, int value) {
        List<QualityIndicatorDTO> list = getQualityRow(projectID, name);
        if (Objects.nonNull(list) && list.size() > 0) {
            QualityIndicatorDTO indicator = list.get(0);
            indicator.setActual(String.valueOf(value));
        }

        return list;
    }

    public Iterable<QualityIndicatorsComments> getAll() {
        return this.commentsRepository.findAll();
    }

    private List<QualityIndicatorDTO> getQualityRow(int projectID, QualityRowNames rowName) {
        String kpiID = rowName.getTitle();
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

    @Transactional
    public void saveQuality(QualityIndicatorsTableDTO dto, int projectID) {
        List<QualityIndicators> quality = buildQualityIndicators(dto.getQuality(), projectID, QualityRowNames.QUALITY);
        List<QualityIndicators> defects = buildQualityIndicators(dto.getDefects(), projectID, QualityRowNames.DEFECTS);
        List<QualityIndicators> backlog = buildQualityIndicators(dto.getBacklog(), projectID, QualityRowNames.BACKLOG);
        List<QualityIndicators> testExecution = buildQualityIndicators(dto.getTestExecution(), projectID, QualityRowNames.EXECUTION);
        List<QualityIndicators> testRate = buildQualityIndicators(dto.getTestRate(), projectID, QualityRowNames.RATE);

        QualityIndicatorsComments qualityComment =
                buildQualityComment(dto.getQuality(), projectID, QualityRowNames.QUALITY);
        QualityIndicatorsComments defectComment =
                buildQualityComment(dto.getDefects(), projectID, QualityRowNames.DEFECTS);
        QualityIndicatorsComments backlogComment =
                buildQualityComment(dto.getBacklog(), projectID, QualityRowNames.BACKLOG);
        QualityIndicatorsComments testExecutionComment =
                buildQualityComment(dto.getTestExecution(), projectID, QualityRowNames.EXECUTION);
        QualityIndicatorsComments testRateComment =
                buildQualityComment(dto.getTestRate(), projectID, QualityRowNames.RATE);

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

    private List<QualityIndicators> buildQualityIndicators(List<QualityIndicatorDTO> dtos, int projectID, QualityRowNames rowName) {
        String kpiId = rowName.getTitle();
        List<QualityIndicators> result = new ArrayList<>();
        for (int i = 0; i < dtos.size(); i++) {
            result.add(
                    new QualityIndicators(projectID, kpiId, i, dtos.get(i).getObjective(), dtos.get(i).getActual()));
        }

        return result;
    }

    private QualityIndicatorsComments buildQualityComment(List<QualityIndicatorDTO> dtos, int projectID, QualityRowNames rowName) {
        String kpiID = rowName.getTitle();
        String comment = "";
        if (Objects.nonNull(dtos) && dtos.size() > 0) {
            comment = dtos.get(0).getComment();
        }

        return new QualityIndicatorsComments(projectID, kpiID, comment);
    }
}

