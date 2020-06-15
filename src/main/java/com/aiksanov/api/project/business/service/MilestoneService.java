package com.aiksanov.api.project.business.service;

import com.aiksanov.api.project.data.entity.Milestone;
import com.aiksanov.api.project.data.entity.Project;
import com.aiksanov.api.project.data.entity.pk.MilestonePK;
import com.aiksanov.api.project.data.repository.GeneralRepository;
import com.aiksanov.api.project.data.repository.MilestoneRepository;
import com.aiksanov.api.project.util.enums.MilestoneLabels;
import com.aiksanov.api.project.util.enums.ProjectStates;
import com.aiksanov.api.project.util.enums.ProjectTypes;
import com.aiksanov.api.project.web.DTO.MilestoneDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MilestoneService {
    private static final String[] mandatoryMilestones = {"OR", "DR0", "DR1", "DR2", "DR3", "TR", "DR4", "DR5", "OBR", "CI"};
    private static final List<String> alwaysShown = Arrays.asList("DR1", "DR4");
    private final MilestoneRepository milestoneRepo;
    private final GeneralRepository projectRepo;


    public List<MilestoneDTO> getMilestoneDTOsForInfoTab(Integer projectID) {
        List<Milestone> milestones = this.milestoneRepo.findAllByMilestonePK_ProjectIDOrderByActualDateAsc(projectID);
        List<Milestone> orderedMilestones = this.getOrderedByMandatory(milestones);
        orderedMilestones.forEach(milestone -> {
            String label = milestone.getMilestonePK().getLabel();
            if (alwaysShown.contains(label)) {
                milestone.setShown(true);
            }
        });
        return mapMilestonesToDTO(orderedMilestones);
    }

    private List<Milestone> getOrderedByMandatory(List<Milestone> milestones) {
        List<Milestone> orderedMilestones = new ArrayList<>();
        List<Integer> pickedMilestonesPositions = new ArrayList<>();
        nextLabel:
        for (String label : mandatoryMilestones) {
            for (Milestone current : milestones) {
                if (current.getMilestonePK().getLabel().toUpperCase().equals(label)) {
                    orderedMilestones.add(current);
                    pickedMilestonesPositions.add(milestones.indexOf(current));
                    continue nextLabel;
                }
            }

            Milestone empty = new Milestone();
            empty.setMilestonePK(new MilestonePK(-1, label));
            orderedMilestones.add(empty);
        }

        if (pickedMilestonesPositions.size() != 0) {
            for (int i = 0; i < milestones.size(); i++) {
                if (pickedMilestonesPositions.contains(i)) continue;

                orderedMilestones.add(milestones.get(i));
            }
        }

        return orderedMilestones;
    }

    public List<Milestone> getMilestonesByProjectID(Integer projectID) {
        return this.milestoneRepo.findAllByMilestonePK_ProjectIDOrderByActualDateAsc(projectID);
    }

    public List<MilestoneDTO> getTimelineMilestones(int projectID) {
        List<Milestone> milestones = this.milestoneRepo.findAllByMilestonePK_ProjectIDAndShown(projectID, true);
        milestones = milestones.stream().filter(milestone -> Objects.nonNull(milestone.getActualDate())).sorted()
                .collect(Collectors.toList());
        return mapMilestonesToDTO(milestones);
    }

    private List<MilestoneDTO> mapMilestonesToDTO(List<Milestone> milestones) {
        if (Objects.nonNull(milestones)) {
            return milestones.stream()
                    .map(MilestoneDTO::new)
                    .collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }

    public Milestone getProjectMilestoneById(Integer projectID, String label) {
        return this.milestoneRepo.findById(new MilestonePK(projectID, label)).orElseGet(Milestone::new);
    }

    @Transactional
    public void saveMilestones(Integer projectID, List<MilestoneDTO> dtos) {
        this.milestoneRepo.deleteAllByMilestonePK_ProjectID(projectID);
        List<Milestone> milestonesToSave = addMilestonesData(projectID, dtos);
        if (Objects.nonNull(milestonesToSave)) {
            this.milestoneRepo.saveAll(milestonesToSave);
        }
    }

    public List<Milestone> addMilestonesData(Integer projectID, List<MilestoneDTO> milestoneDTOs) {
        if (Objects.nonNull(projectID)) {
            return milestoneDTOs.stream()
                    .map(dto ->
                            new Milestone(
                                    new MilestonePK(projectID, dto.getLabel()),
                                    dto.getBaselineDate(),
                                    dto.getActualDate(),
                                    dto.getCompletion(),
                                    dto.getMeetingMinutes(),
                                    dto.isShown()
                            )
                    ).collect(Collectors.toList());
        }

        return null;
    }

    public ProjectStates getCurrentProjectState(int projectId) {
        Project project = this.projectRepo.getOne(projectId);
        ProjectTypes projectType = project.getType();
        Milestone dr0 = this.milestoneRepo.findById(new MilestonePK(projectId, "DR0")).orElseGet(Milestone::new);
        Milestone dr1 = this.milestoneRepo.findById(new MilestonePK(projectId, "DR1")).orElseGet(Milestone::new);
        Milestone dr4 = this.milestoneRepo.findById(new MilestonePK(projectId, "DR4")).orElseGet(Milestone::new);
        Milestone dr5 = this.milestoneRepo.findById(new MilestonePK(projectId, "DR5")).orElseGet(Milestone::new);

        if (dr5.getCompletion() == 100 && (projectType != ProjectTypes.OFFER && projectType != ProjectTypes.OFFER_PRODUCT)) {
            return ProjectStates.CLOSED;
        }
        if (dr4.getCompletion() == 100 && (projectType == ProjectTypes.OFFER || projectType == ProjectTypes.OFFER_PRODUCT)) {
            return ProjectStates.CLOSED;
        }
        if (dr1.getCompletion() == 100) return ProjectStates.COMMITTED;
        if (dr0.getCompletion() == 100) return ProjectStates.PLANNING;

        return ProjectStates.FORECAST;
    }

    @Transactional
    public void deleteMilestonesData(List<MilestonePK> milestonesPKs) {
        if (Objects.nonNull(milestonesPKs)) {
            this.milestoneRepo.deleteAllByMilestonePK(milestonesPKs);
        }
    }

    public Milestone getMilestoneWithHighestActDate(List<Integer> projectIds) {
        return this.milestoneRepo.highestActualDate(projectIds).orElseGet(Milestone::new);
    }

    public Milestone getMilestoneWithLowestActDate(List<Integer> projectIds) {
        return this.milestoneRepo.lowestActualDate(projectIds).orElseGet(Milestone::new);
    }

    public Milestone getLastApprovedMilestone(List<Milestone> milestones) {
        Milestone lastApproved = new Milestone();
        for (Milestone current : milestones) {
            if (Objects.isNull(current)) continue;
            if (Objects.nonNull(current.getActualDate()) && current.getCompletion() == 100) {
                lastApproved = current;
            }
        }

        return lastApproved;
    }

    public boolean isDr1Exists(int projectID) {
        return milestoneRepo.existsById(new MilestonePK(projectID, MilestoneLabels.DR1.getLabel()));
    }
}