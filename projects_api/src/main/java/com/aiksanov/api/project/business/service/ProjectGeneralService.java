package com.aiksanov.api.project.business.service;

import com.aiksanov.api.project.data.entity.Milestone;
import com.aiksanov.api.project.data.entity.Project;
import com.aiksanov.api.project.data.repository.GeneralRepository;
import com.aiksanov.api.project.web.DTO.ContributingDTO;
import com.aiksanov.api.project.web.DTO.information.MilestoneDTO;
import com.aiksanov.api.project.web.DTO.summary.ContribProjectsDataDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProjectGeneralService {
    private GeneralRepository generalRepository;
    private MilestoneService milestoneService;

    @Autowired
    public ProjectGeneralService(GeneralRepository generalRepository, MilestoneService milestoneService) {
        this.generalRepository = generalRepository;
        this.milestoneService = milestoneService;
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

    public List<ContributingDTO> getContribProjectDtosList(int projectID) {
        List<Project> contribProjects = this.generalRepository.findAllContribProjectsByProjectID(projectID);
        return contribProjects.stream()
                .map((prj) -> (new ContributingDTO(prj.getProjectID(), prj.getName())))
                .collect(Collectors.toList());
    }

    public ContribProjectsDataDTO getContibData(int projectId) {
        Project project = this.generalRepository.findById(projectId).orElseThrow(() -> new RuntimeException("Err"));
        String projectName = project.getName();
        List<MilestoneDTO> milestones = this.milestoneService.getMilestonesByProjectID(projectId);
        List<ContributingDTO> contibProjects = this.getContribProjectDtosList(projectId);
        Map<String, List<MilestoneDTO>> contribMilestones = getContribMilestones(contibProjects);

        List<Integer> projectIds = contibProjects.stream().map(ContributingDTO::getProjectID).collect(Collectors.toList());
        projectIds.add(projectId);

        Milestone max = this.milestoneService.getMilestoneWithHighestActDate(projectIds);
        Milestone min = this.milestoneService.getMilestoneWithLowestActDate(projectIds);
        return new ContribProjectsDataDTO(projectName, milestones, contribMilestones, max.getActualDate(), min.getActualDate());
    }

    private Map<String, List<MilestoneDTO>> getContribMilestones(List<ContributingDTO> contribProjects) {
        Map<String, List<MilestoneDTO>> contribMilestones = new HashMap<>();
        contribProjects.forEach((project) -> {
            String projectName = project.getProjectName();
            List<MilestoneDTO> milestones = this.milestoneService.getMilestonesByProjectID(project.getProjectID());
            contribMilestones.put(projectName, milestones);
        });

        return contribMilestones;
    }
}
