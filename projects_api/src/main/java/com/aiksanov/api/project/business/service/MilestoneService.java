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
    private final MilestoneRepository milestoneRepo;
    private final String[] mandatoryMilestones = {"OR", "DR0", "DR1", "DR2", "DR3", "TR", "DR4", "DR5", "OBR", "CI"};

    @Autowired
    public MilestoneService(MilestoneRepository milestoneRepo) {
        this.milestoneRepo = milestoneRepo;
    }

    public List<MilestoneDTO> getMilestoneDTOsForInfoTab(Integer projectID) {
        List<Milestone> milestones = this.milestoneRepo.findAllByMilestonePK_ProjectIDOrderByActualDateAsc(projectID);
        List<Milestone> orderedMilestones = this.getOrderedByMandatory(milestones);
        return mapMilestonesToDTO(orderedMilestones);
    }

    private List<Milestone> getOrderedByMandatory(List<Milestone> milestones) {
        List<Milestone> orderedMilestones = new ArrayList<>();
        List<Integer> pickedMilestonesPositions = new ArrayList<>();
        nextLabel:
        for(String label: mandatoryMilestones) {
           for(Milestone current: milestones) {
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
