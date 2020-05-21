package com.aiksanov.api.project.business.service;

import com.aiksanov.api.project.data.entity.ContributingProjects;
import com.aiksanov.api.project.data.entity.Milestone;
import com.aiksanov.api.project.data.entity.Project;
import com.aiksanov.api.project.data.entity.WorkspaceInfo;
import com.aiksanov.api.project.data.repository.ContributingProjectsRepository;
import com.aiksanov.api.project.data.repository.GeneralRepository;
import com.aiksanov.api.project.data.repository.WorkspaceInfoRepo;
import com.aiksanov.api.project.exceptions.ProjectDoesNotExistException;
import com.aiksanov.api.project.util.enums.MilestoneLabels;
import com.aiksanov.api.project.util.enums.ProjectStates;
import com.aiksanov.api.project.util.enums.ProjectTypes;
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
    private WorkspaceInfoRepo workspaceInfoRepo;

    @Autowired
    public ProjectGeneralService(GeneralRepository generalRepository, MilestoneService milestoneService,
                                 ContributingProjectsRepository contribRepository, WorkspaceInfoRepo workspaceInfoRepo) {
        this.generalRepository = generalRepository;
        this.milestoneService = milestoneService;
        this.contribRepository = contribRepository;
        this.workspaceInfoRepo = workspaceInfoRepo;
    }

    public Project getProjectGeneralInfo(Integer projectID) {
        return this.generalRepository.findById(projectID).orElseThrow(ProjectDoesNotExistException::new);
    }

    public Iterable<Project> getAll() {
        return this.generalRepository.findAll();
    }

    public List<ContributingDTO> getContributableProjects(int projectId) {
        List<Project> projects = this.generalRepository.findAllByEpmAndStatus(false, WorkspaceStatus.ENABLED.name());
        return projects.stream()
                .filter((prj) -> (prj.getProjectID() != projectId && prj.getType() == ProjectTypes.PRODUCT))
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
        ProjectTypes projectType = project.getType();

        List<ContributingProjectDTO> offer = new ArrayList<>();
        List<ContributingProjectDTO> products = new ArrayList<>();
        List<Integer> projectIds = new ArrayList<>();

        if (projectType == ProjectTypes.OFFER) {
            ContributingProjectDTO offerDto = this.getContribProjectDto(projectId);
            offer.add(offerDto);
            products = this.getContribProjectsToOffer(projectId);

            List<ContributingDTO> contibProjects = this.getContribProjectDtosList(projectId);
            projectIds = contibProjects.stream().map(ContributingDTO::getProjectID).collect(Collectors.toList());
            projectIds.add(projectId);
        } else {
            List<ContributingProjects> offersProductPair = this.getOfferByContribId(projectId);
            List<Integer> offersIds = offersProductPair.stream()
                    .map(p -> p.getPk().getProjectID())
                    .collect(Collectors.toList());

            if (offersProductPair.size() == 0) return new ContribProjectsDataDTO();

            for (ContributingProjects contrib: offersProductPair) {
                int offerId = contrib.getPk().getProjectID();
                ContributingProjectDTO contributedOffer = this.getContribProjectDto(offerId);
                offer.add(contributedOffer);
                ContributingProjectDTO product = this.getContribProjectDto(projectId);
                products.add(product);
                projectIds.add(projectId);
                projectIds.add(offerId);
                projectIds.addAll(offersIds);
            }
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

    private List<ContributingProjects> getOfferByContribId(int projectId) {
        return this.contribRepository.findByPk_ContribID(projectId);
    }

    private List<ContributingProjectDTO> getContribProjectsToOffer(int offerId) {
        List<ContributingProjects> products = this.contribRepository.findAllByPk_ProjectID(offerId);
        List<ContributingProjectDTO> result = new ArrayList<>();
        products.forEach((product) -> {
            ContributingProjectDTO dto = this.getContribProjectDto(product.getPk().getContribID());
            result.add(dto);
        });

        return result;
    }

    private ContributingProjectDTO getContribProjectDto(int projectId) {
        Project project = this.generalRepository.findById(projectId).orElseThrow(ProjectDoesNotExistException::new);
        String projectName = project.getName();
        ProjectStates projectState = project.getState();
        ProjectTypes projectType = project.getType();
        List<Milestone> milestones = this.milestoneService.getMilestonesByProjectID(projectId);
        List<Milestone> onlyWithActualDate = milestones.stream()
                .filter(mil -> Objects.nonNull(mil) && Objects.nonNull(mil.getActualDate()))
                .collect(Collectors.toList());
        MilestoneDTO lastApprovedDto = this.getLastApprovedMilestoneDto(projectId);
        List<MilestoneDTO> milestoneDTOS = onlyWithActualDate.stream().map(MilestoneDTO::new)
                .collect(Collectors.toList());

        return new ContributingProjectDTO(projectId, projectName, projectState, projectType, lastApprovedDto, milestoneDTOS);
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
        Milestone dr1 = this.milestoneService.getProjectMilestoneById(projectId, MilestoneLabels.DR1.getLabel());
        return new ProjectDefaultDataDTO(project, dr1);
    }

    public void setProjectStateByMilestones(int projectId) {
        ProjectStates state = this.milestoneService.getCurrentProjectState(projectId);
        Project project = this.generalRepository.getOne(projectId);
        project.setState(state);

        this.generalRepository.save(project);
    }

    public void modifyWorkspaceUpdatedBy(int projectId, String updatedBy) {
        WorkspaceInfo status = this.workspaceInfoRepo.findById(projectId).orElseThrow(ProjectDoesNotExistException::new);
        status.setModifiedBy(updatedBy);
        status.setModified(new Date());
        this.workspaceInfoRepo.save(status);
    }
}
