package com.aiksanov.api.project.business.service;

import com.aiksanov.api.project.data.entity.Project;
import com.aiksanov.api.project.data.entity.ProjectURLs;
import com.aiksanov.api.project.data.repository.GeneralRepository;
import com.aiksanov.api.project.data.repository.ProjectURLsRepository;
import com.aiksanov.api.project.web.DTO.SummaryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SummaryTabService {
    private GeneralRepository generalRepo;
    private ProjectURLsRepository urlsRepo;

    @Autowired
    public SummaryTabService(GeneralRepository generalRepo, ProjectURLsRepository urlsRepo) {
        this.generalRepo = generalRepo;
        this.urlsRepo = urlsRepo;
    }

    public SummaryDTO getSummaryDTO(Integer projectID){
        if (!this.generalRepo.existsById(projectID)){
            throw new RuntimeException("Project id doesn't exist");
        }
        Project projectInfo = this.generalRepo.findById(projectID).get();
        ProjectURLs urls = this.urlsRepo.findById(projectID).orElseGet(ProjectURLs::new);

        return new SummaryDTO(projectInfo, urls);
    }
}
