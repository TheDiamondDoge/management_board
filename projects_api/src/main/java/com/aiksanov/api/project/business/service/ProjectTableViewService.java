package com.aiksanov.api.project.business.service;

import com.aiksanov.api.project.data.entity.Project;
import com.aiksanov.api.project.data.repository.GeneralRepository;
import com.aiksanov.api.project.util.enums.ProjectStates;
import com.aiksanov.api.project.util.enums.ProjectStatus;
import com.aiksanov.api.project.util.enums.WorkspaceStatus;
import com.aiksanov.api.project.web.DTO.PWSTableViewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectTableViewService {
    private GeneralRepository generalRepository;

    @Autowired
    public ProjectTableViewService(GeneralRepository generalRepository) {
        this.generalRepository = generalRepository;
    }

    public List<Project> getProjectsData(Boolean isEpm, WorkspaceStatus status){
        return getProjectsByParams(isEpm, status);
    }

    public List<PWSTableViewDTO> getProjectsListView(Boolean isEPM, WorkspaceStatus status) {
        List<Project> projects = getProjectsByParams(isEPM, status);
        List<PWSTableViewDTO> dtoList = new ArrayList<>();
        projects.forEach(p -> {
            PWSTableViewDTO dto = new PWSTableViewDTO(p);
            dtoList.add(dto);
        });
        return dtoList;
    }

    private List<Project> getProjectsByParams(Boolean isEPM, WorkspaceStatus status){
        if (isEPM == null && status == null) {
            return this.generalRepository.findAll();
        } else if (isEPM != null && status == null) {
            return this.generalRepository.findAllByEpm(isEPM);
        } else if (isEPM == null){
            return this.generalRepository.findAllByStatus(status);
        } else {
            return this.generalRepository.findAllByEpmAndStatus(isEPM, status.name());
        }
    }
}
