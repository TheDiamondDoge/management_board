package com.aiksanov.api.project.business.service;

import com.aiksanov.api.project.data.entity.Project;
import com.aiksanov.api.project.data.repository.GeneralRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
