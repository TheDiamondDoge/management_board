package com.aiksanov.api.project.business.service;

import com.aiksanov.api.project.data.entity.Project;
import com.aiksanov.api.project.data.repository.GeneralRepository;
import com.aiksanov.api.project.web.DTO.ContributingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectGeneralService {
    private GeneralRepository generalRepository;

    @Autowired
    public ProjectGeneralService(GeneralRepository generalRepository) {
        this.generalRepository = generalRepository;
    }

    public Project getProjectGeneralInfo(Integer projectID) {
        if (!this.generalRepository.existsById(projectID)) {
            throw new RuntimeException("Project doesnt exist");
        }
        return this.generalRepository.findById(projectID).get();
    }

    public Iterable<Project> getAll() {
        return this.generalRepository.findAll();
    }

    public List<ContributingDTO> getContributableProjects() {
        List<Project> projects = this.generalRepository.findAllByEpmAndStatus(false, "ENABLED");
        return projects.stream()
                .map((prj) -> (new ContributingDTO(prj.getProjectID(), prj.getName())))
                .collect(Collectors.toList());
    }
}
