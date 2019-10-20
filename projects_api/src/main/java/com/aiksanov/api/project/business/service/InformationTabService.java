package com.aiksanov.api.project.business.service;

import com.aiksanov.api.project.data.entity.Project;
import com.aiksanov.api.project.data.entity.ProjectURLs;
import com.aiksanov.api.project.data.repository.GeneralRepository;
import com.aiksanov.api.project.data.repository.ProjectURLsRepository;
import com.aiksanov.api.project.web.DTO.InformationDTO;
import com.aiksanov.api.project.web.DTO.MilestoneDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class InformationTabService {
    private GeneralRepository generalRepository;
    private ProjectURLsRepository urlsRepository;
    private MilestoneService milestoneService;

    @Value("${project.error.projectNotFound}")
    private String projectNotFoundMessage;

    @Autowired
    public InformationTabService(GeneralRepository generalRepository, ProjectURLsRepository urlsRepository,
                                 MilestoneService milestoneService) {
        this.generalRepository = generalRepository;
        this.urlsRepository = urlsRepository;
        this.milestoneService = milestoneService;
    }

    public InformationDTO getInfoTabData(Integer id){
        if (Objects.isNull(id)){
            throw new RuntimeException("Parameter 'id' not found");
        }
        Project project = this.generalRepository.findById(id).orElseThrow(() -> new RuntimeException(projectNotFoundMessage));
        ProjectURLs urls = this.urlsRepository.findById(id).orElseGet(ProjectURLs::new);

        return new InformationDTO(project, urls);
    }
}
