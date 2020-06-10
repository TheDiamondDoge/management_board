package com.aiksanov.api.project.business.service;

import com.aiksanov.api.project.data.entity.HealthIndicators;
import com.aiksanov.api.project.data.entity.Milestone;
import com.aiksanov.api.project.data.entity.Project;
import com.aiksanov.api.project.data.repository.GeneralRepository;
import com.aiksanov.api.project.data.repository.HealthRepository;
import com.aiksanov.api.project.data.repository.MilestoneRepository;
import com.aiksanov.api.project.util.enums.WorkspaceStatus;
import com.aiksanov.api.project.web.DTO.PWSTableViewDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProjectTableViewService {
    private final GeneralRepository generalRepository;
    private final MilestoneRepository milestoneRepository;
    private final HealthRepository healthRepository;


    public List<Project> getProjectsData(Boolean isEpm, WorkspaceStatus status){
        return getProjectsByParams(isEpm, status);
    }

    public List<PWSTableViewDTO> getProjectsListView(Boolean isEPM, WorkspaceStatus status) {
        List<Project> projects = getProjectsByParams(isEPM, status);
        List<PWSTableViewDTO> dtoList = projects.stream().map(p -> {
            int projectId = p.getProjectID();
            List<Milestone> milestones = this.milestoneRepository.findAllByMilestonePK_ProjectIDOrderByActualDateAsc(projectId);
            HealthIndicators healthIndicators = this.healthRepository.lastHealthState(projectId);
            return new PWSTableViewDTO(p, healthIndicators, milestones);
        }).collect(Collectors.toList());

        return dtoList;
    }

    private List<Project> getProjectsByParams(Boolean isEPM, WorkspaceStatus status){
        if (isEPM == null && status == null) {
            return this.generalRepository.findAll();
        } else if (isEPM != null && status == null) {
            return this.generalRepository.findAllByEpm(isEPM);
        } else if (isEPM == null){
            return this.generalRepository.findAllByStatus(status.name());
        } else {
            return this.generalRepository.findAllByEpmAndStatus(isEPM, status.name());
        }
    }
}