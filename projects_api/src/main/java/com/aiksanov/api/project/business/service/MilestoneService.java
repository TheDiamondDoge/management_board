package com.aiksanov.api.project.business.service;

import com.aiksanov.api.project.data.entity.Milestone;
import com.aiksanov.api.project.data.entity.pk.MilestonePK;
import com.aiksanov.api.project.data.repository.MilestoneRepository;
import com.aiksanov.api.project.web.DTO.MilestoneDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MilestoneService {
    private MilestoneRepository milestoneRepo;

    @Autowired
    public MilestoneService(MilestoneRepository milestoneRepo) {
        this.milestoneRepo = milestoneRepo;
    }

    public List<MilestoneDTO> getMilestoneDTOsByProjectID(Integer projectID) {
        List<Milestone> milestones = this.milestoneRepo.findAllByMilestonePK_ProjectIDOrderByActualDateAsc(projectID);
        Collections.sort(milestones);
        return mapMilestonesToDTO(milestones);
    }

    public List<Milestone> getMilestonesByProjectID(Integer projectID) {
        return this.milestoneRepo.findAllByMilestonePK_ProjectIDOrderByActualDateAsc(projectID);
    }

    public List<MilestoneDTO> getShownMilestonesByProjectID(int projectID) {
        List<Milestone> milestones = this.milestoneRepo.findAllByMilestonePK_ProjectIDAndShown(projectID, true);
        milestones = milestones.stream().filter(milestone -> Objects.nonNull(milestone.getActualDate())).collect(Collectors.toList());
        Collections.sort(milestones);
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
        addMilestonesData(projectID, dtos);
    }

    @Transactional
    public void addMilestonesData(Integer projectID, List<MilestoneDTO> milestoneDTOs) {
        if (Objects.nonNull(projectID)) {
            List<Milestone> milestones = milestoneDTOs.stream()
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

            this.milestoneRepo.saveAll(milestones);
        }
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
}
