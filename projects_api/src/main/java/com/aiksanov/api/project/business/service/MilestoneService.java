package com.aiksanov.api.project.business.service;

import com.aiksanov.api.project.data.entity.Milestone;
import com.aiksanov.api.project.data.entity.MilestonePK;
import com.aiksanov.api.project.data.repository.MilestoneRepository;
import com.aiksanov.api.project.web.DTO.MilestoneDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MilestoneService {
    private MilestoneRepository milestoneRepo;

    @Autowired
    public MilestoneService(MilestoneRepository milestoneRepo) {
        this.milestoneRepo = milestoneRepo;
    }

    public List<MilestoneDTO> getMilestonesByProjectID(Integer projectID){
        List<Milestone> milestones = this.milestoneRepo.findAllByMilestonePK_ProjectID(projectID);
        if (Objects.nonNull(milestones)){
            return milestones.stream()
                    .map(m -> new MilestoneDTO(m))
                    .collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }

    public Milestone getProjectMilestoneById(Integer projectID, String label){
        return this.milestoneRepo.findById(new MilestonePK(projectID, label)).orElseThrow(
                () -> new RuntimeException("Milestone not found"));
    }

    public void addMilestonesData(List<MilestoneDTO> milestoneDTOs){
        List<Milestone> milestones = milestoneDTOs.stream()
                .map(dto ->
                    new Milestone(
                        new MilestonePK(dto.getProjectID(), dto.getLabel()),
                        dto.getBaselineDate(),
                        dto.getActualDate(),
                        dto.getCompletion(),
                        dto.getUrl(),
                        dto.isShownInTimeline()
                    )
                ).collect(Collectors.toList());

        if (Objects.nonNull(milestones)) {
            this.milestoneRepo.saveAll(milestones);
        }
    }

    @Transactional
    public void deleteMilestonesData(List<MilestonePK> milestonesPKs){
        if (Objects.nonNull(milestonesPKs)) {
            this.milestoneRepo.deleteAllByMilestonePK(milestonesPKs);
        }
    }
}
