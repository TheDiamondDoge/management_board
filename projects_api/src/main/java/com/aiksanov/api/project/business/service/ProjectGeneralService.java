package com.aiksanov.api.project.business.service;

import com.aiksanov.api.project.data.entity.ContributingProjects;
import com.aiksanov.api.project.data.entity.Milestone;
import com.aiksanov.api.project.data.entity.Project;
import com.aiksanov.api.project.data.repository.ContributingProjectsRepository;
import com.aiksanov.api.project.data.repository.GeneralRepository;
import com.aiksanov.api.project.web.DTO.contrib.ContributingDTO;
import com.aiksanov.api.project.web.DTO.contrib.ContributingProjectDTO;
import com.aiksanov.api.project.web.DTO.information.MilestoneDTO;
import com.aiksanov.api.project.web.DTO.contrib.ContribProjectsDataDTO;
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

    //TODO: refactor
    public ContribProjectsDataDTO getContibData(int projectId) {
        Project project = this.generalRepository.findById(projectId).orElseThrow(() -> new RuntimeException("Err"));
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
        Project project = this.generalRepository.findById(projectId).orElseThrow(() -> new RuntimeException("Err"));
        String projectName = project.getName();
        String projectState = project.getState();
        List<Milestone> milestones = this.milestoneService.getMilestonesByProjectID(projectId);
        Milestone lastApproved = this.milestoneService.getLastApprovedMilestone(milestones);
        MilestoneDTO lastApprovedDto;
        if (Objects.isNull(lastApproved)) {
            lastApprovedDto = null;
        } else {
            lastApprovedDto = new MilestoneDTO(lastApproved);
        }
        List<MilestoneDTO> milestoneDTOS = milestones.stream().map(MilestoneDTO::new).collect(Collectors.toList());

        return new ContributingProjectDTO(projectName, projectState, lastApprovedDto, milestoneDTOS);
    }
}
