package com.aiksanov.api.project.business.service;

import com.aiksanov.api.project.data.entity.ContributingProjects;
import com.aiksanov.api.project.data.entity.Milestone;
import com.aiksanov.api.project.data.entity.Project;
import com.aiksanov.api.project.data.entity.WorkspaceInfo;
import com.aiksanov.api.project.data.repository.ContributingProjectsRepository;
import com.aiksanov.api.project.data.repository.GeneralRepository;
import com.aiksanov.api.project.exceptions.ProjectDoesNotExistException;
import com.aiksanov.api.project.util.enums.WorkspaceStatus;
import com.aiksanov.api.project.web.DTO.contrib.ContributingDTO;
import com.aiksanov.api.project.web.DTO.contrib.ContributingProjectDTO;
import com.aiksanov.api.project.web.DTO.MilestoneDTO;
import com.aiksanov.api.project.web.DTO.contrib.ContribProjectsDataDTO;
import com.aiksanov.api.project.web.DTO.summary.ProjectDefaultDataDTO;
import com.aiksanov.api.project.web.DTO.summary.ProjectGeneral;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProjectGeneralService {
    private GeneralRepository generalRepository;
    private MilestoneService milestoneService;
    private ContributingProjectsRepository contribRepository;

    @Autowired
    public ProjectGeneralService(GeneralRepository generalRepository, MilestoneService milestoneService,
                                 ContributingProjectsRepository contribRepository) {
        this.generalRepository = generalRepository;
        this.milestoneService = milestoneService;
        this.contribRepository = contribRepository;
    }

    public Project getProjectGeneralInfo(Integer projectID) {
        return this.generalRepository.findById(projectID).orElseThrow(ProjectDoesNotExistException::new);
    }

    public Iterable<Project> getAll() {
        return this.generalRepository.findAll();
    }

    public List<ContributingDTO> getContributableProjects(int projectId) {
        List<Project> projects = this.generalRepository.findAllByEpmAndStatus(false, WorkspaceStatus.ENABLED.getValue());
        return projects.stream()
                .filter((prj) -> (prj.getProjectID() != projectId))
                .map((prj) -> (new ContributingDTO(prj.getProjectID(), prj.getName())))
                .collect(Collectors.toList());
    }

    public List<ContributingDTO> getContribProjectDtosList(int projectID) {
        List<Project> contribProjects = this.generalRepository.findAllContribProjectsByProjectID(projectID);
        return contribProjects.stream()
                .map((prj) -> (new ContributingDTO(prj.getProjectID(), prj.getName())))
                .collect(Collectors.toList());
    }

    //TODO: refactor
    public ContribProjectsDataDTO getContibData(int projectId) {
        Project project = this.generalRepository.findById(projectId).orElseThrow(ProjectDoesNotExistException::new);
        String projectType = project.getType();

        ContributingProjectDTO offer;
        List<ContributingProjectDTO> products = new ArrayList<>();
        List<Integer> projectIds = new ArrayList<>();

        if (projectType.equals("Offer")) {
            offer = this.getContribProject(projectId);
            products = this.getContribProjectsToOffer(projectId);

            List<ContributingDTO> contibProjects = this.getContribProjectDtosList(projectId);
            projectIds = contibProjects.stream().map(ContributingDTO::getProjectID).collect(Collectors.toList());
            projectIds.add(projectId);
        } else {
            ContributingProjects offerProductPair = this.getOfferByContribId(projectId);

            if (Objects.isNull(offerProductPair)) return new ContribProjectsDataDTO();

            int offerId = offerProductPair.getPk().getProjectID();
            offer = this.getContribProject(offerId);
            ContributingProjectDTO product = this.getContribProject(projectId);
            products.add(product);
            projectIds.add(projectId);
            projectIds.add(offerId);
        }

        Milestone max = this.milestoneService.getMilestoneWithHighestActDate(projectIds);
        Milestone min = this.milestoneService.getMilestoneWithLowestActDate(projectIds);

        return new ContribProjectsDataDTO(offer, products, max.getActualDate(), min.getActualDate());
    }

    public ProjectGeneral getProjectGeneralObj(int projectId) {
        Project project = this.getProjectGeneralInfo(projectId);
        WorkspaceInfo workspaceInfo = project.getWorkspaceInfo();
        Date updated = null;
        if (Objects.nonNull(workspaceInfo)) {
            updated = workspaceInfo.getModified();
        }
        return new ProjectGeneral(project.getName(), project.getManager(), "http://www.google.com", updated);
    }

    private ContributingProjects getOfferByContribId(int projectId) {
        return this.contribRepository.findByPk_ContribID(projectId);
    }

    private List<ContributingProjectDTO> getContribProjectsToOffer(int offerId) {
        List<ContributingProjects> products = this.contribRepository.findAllByPk_ProjectID(offerId);
        List<ContributingProjectDTO> result = new ArrayList<>();
        products.forEach((product) -> {
            ContributingProjectDTO dto = this.getContribProject(product.getPk().getContribID());
            result.add(dto);
        });

        return result;
    }

    private ContributingProjectDTO getContribProject(int projectId) {
        Project project = this.generalRepository.findById(projectId).orElseThrow(ProjectDoesNotExistException::new);
        String projectName = project.getName();
        String projectState = project.getState();
        List<Milestone> milestones = this.milestoneService.getMilestonesByProjectID(projectId);
        List<Milestone> onlyWithActualDate = milestones.stream()
                .filter(mil -> Objects.nonNull(mil) && Objects.nonNull(mil.getActualDate()))
                .collect(Collectors.toList());
        MilestoneDTO lastApprovedDto = this.getLastApprovedMilestoneDto(projectId);
        List<MilestoneDTO> milestoneDTOS = onlyWithActualDate.stream().map(MilestoneDTO::new)
                .collect(Collectors.toList());

        return new ContributingProjectDTO(projectName, projectState, lastApprovedDto, milestoneDTOS);
    }

    private MilestoneDTO getLastApprovedMilestoneDto(int projectId) {
        List<Milestone> milestones = this.milestoneService.getMilestonesByProjectID(projectId);
        Milestone lastApproved = this.milestoneService.getLastApprovedMilestone(milestones);
        if (Objects.isNull(lastApproved)) {
            return null;
        } else {
            return new MilestoneDTO(lastApproved);
        }
    }

    public ProjectDefaultDataDTO getProjectDefaults(int projectId) {
        Project project = this.generalRepository.findById(projectId).orElseThrow(ProjectDoesNotExistException::new);
        ProjectDefaultDataDTO dto = new ProjectDefaultDataDTO();
        dto.setProjectId(project.getProjectID());
        dto.setProjectName(project.getName());
        return dto;
    }
}
