package com.aiksanov.api.project.business.service;

import com.aiksanov.api.project.data.entity.*;
import com.aiksanov.api.project.data.repository.*;
import com.aiksanov.api.project.exceptions.NoDataFoundException;
import com.aiksanov.api.project.exceptions.ProjectDoesNotExistException;
import com.aiksanov.api.project.util.decompositor.InformationDtoDecomposer;
import com.aiksanov.api.project.web.DTO.contrib.ContributingDTO;
import com.aiksanov.api.project.web.DTO.information.InformationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
@Transactional
public class InformationTabService {
    private final ProjectGeneralService generalService;
    private final GeneralRepository generalRepository;
    private final ProjectURLsRepository urlsRepository;
    private final JiraParamsRepository jiraParamsRepository;
    private final EcmaBacklogTargetRepo backlogTargetRepo;
    private final ContributingProjectsRepository contribProjectsRepo;
    private final FieldCommentsRepository commentsRepository;


    @Transactional
    public InformationDTO getInfoTabData(Integer id) {
        Project project = this.generalRepository.findById(id).orElseThrow(ProjectDoesNotExistException::new);
        ProjectURLs urls = this.urlsRepository.findById(id).orElseGet(ProjectURLs::new);
        JiraParams jiraParams = this.jiraParamsRepository.findById(id).orElseGet(JiraParams::new);
        List<EcmaBacklogTarget> target = this.backlogTargetRepo.findAllByProjectId(id);
        List<ContributingDTO> contribProjects = this.generalService.getContribProjectDtosList(id);
        List<FieldComments> comments = this.commentsRepository.findAllByPk_ProjectID(id);

        return new InformationDTO(project, urls, jiraParams, target, contribProjects, comments);
    }



    @Transactional
    public void saveInformationData(Integer id, InformationDTO dto) {
        if (Objects.isNull(id) || !generalService.isProjectExist(id)) {
            throw new ProjectDoesNotExistException();
        }

        if (Objects.isNull(dto)) {
            throw new NoDataFoundException();
        }

        InformationDtoDecomposer decomposer = new InformationDtoDecomposer(dto, id);
        Project project = decomposer.getProject();
        ProjectURLs urLs = decomposer.getProjectUrlsObj();
        JiraParams params = decomposer.getJiraParams();
        List<EcmaBacklogTarget> target = decomposer.getEcmaBacklogTargetList();
        List<ContributingProjects> contributingProjects = new ArrayList<>();
        if (project.getAdditionalInfo().isComposite()) {
            contributingProjects = decomposer.getListOfContribProjects();
        }

        List<FieldComments> comments = decomposer.getListOfFieldComments();

        this.generalRepository.save(buildProjectToSave(id, project));
        this.urlsRepository.save(buildUrlsToSave(id, urLs));
        this.jiraParamsRepository.save(buildParamsToSave(id, params));

        this.backlogTargetRepo.deleteAllByProjectId(id);
        this.backlogTargetRepo.saveAll(target);

        this.contribProjectsRepo.deleteAllByPk_ProjectID(id);
        this.contribProjectsRepo.saveAll(contributingProjects);

        this.commentsRepository.deleteAllByPk_ProjectID(id);
        this.commentsRepository.saveAll(comments);

        this.generalService.modifyWorkspaceUpdatedBy(id, "TestInfoUpd");
    }

    private Project buildProjectToSave(int projectID, Project fromInfoDTO) {
        Project existing = this.generalRepository.findById(projectID).orElseThrow(ProjectDoesNotExistException::new);
        existing.setProjectID(projectID);
        existing.setType(fromInfoDTO.getType());
        existing.setRigor(fromInfoDTO.getRigor());
        existing.setState(fromInfoDTO.getState());
        existing.setManager(fromInfoDTO.getManager());

        Product productFromDto = fromInfoDTO.getProduct();
        if (Objects.isNull(productFromDto)) {
            productFromDto = new Product();
        }

        if (Objects.isNull(existing.getProduct())) {
            existing.setProduct(new Product(projectID));
        }

        existing.getProduct().setProductLine(productFromDto.getProductLine());
        existing.getProduct().setName(productFromDto.getName());
        existing.getProduct().setManager(productFromDto.getManager());
        existing.getProduct().setDivision(productFromDto.getDivision());
        existing.getProduct().setBusinessUnit(productFromDto.getBusinessUnit());
        existing.getProduct().setRelease(productFromDto.getRelease());

        ProjectAdditionalInfo additionalFromDto = fromInfoDTO.getAdditionalInfo();
        if (Objects.isNull(additionalFromDto)) {
            additionalFromDto = new ProjectAdditionalInfo();
        }

        if (Objects.isNull(existing.getAdditionalInfo())) {
            existing.setAdditionalInfo(new ProjectAdditionalInfo(projectID));
        }

        existing.getAdditionalInfo().setDescription(additionalFromDto.getDescription());
        existing.getAdditionalInfo().setBusinessLineManager(additionalFromDto.getBusinessLineManager());
        existing.getAdditionalInfo().setSponsor(additionalFromDto.getSponsor());
        existing.getAdditionalInfo().setOemPartner(additionalFromDto.getOemPartner());
        existing.getAdditionalInfo().setKeyCustomers(additionalFromDto.getKeyCustomers());
        existing.getAdditionalInfo().setComposite(additionalFromDto.isComposite());
        existing.getAdditionalInfo().setMaintenance(additionalFromDto.isMaintenance());

        return existing;
    }

    private ProjectURLs buildUrlsToSave(int projectID, ProjectURLs fromInfoDTO) {
        ProjectURLs existing = this.urlsRepository.findById(projectID).orElseGet(ProjectURLs::new);
        existing.setProjectID(projectID);
        existing.setCharter(fromInfoDTO.getCharter());
        existing.setOrBusinessPlan(fromInfoDTO.getOrBusinessPlan());
        existing.setUpdatedBusinessPlan(fromInfoDTO.getUpdatedBusinessPlan());
        existing.setTailoredChecklist(fromInfoDTO.getTailoredChecklist());
        existing.setLessonsLearned(fromInfoDTO.getLessonsLearned());
        existing.setProjectPlan(fromInfoDTO.getProjectPlan());
        existing.setLaunchingPlan(fromInfoDTO.getLaunchingPlan());
        existing.setCollabUrl(fromInfoDTO.getCollabUrl());
        existing.setPwaUrl(fromInfoDTO.getPwaUrl());
        existing.setSalesForce(fromInfoDTO.getSalesForce());
        existing.setDocumentsRepoUrl(fromInfoDTO.getDocumentsRepoUrl());
        existing.setDefectsUrl(fromInfoDTO.getDefectsUrl());
        existing.setRequirementsUrl(fromInfoDTO.getRequirementsUrl());
        existing.setCisUrl(fromInfoDTO.getCisUrl());

        return existing;
    }

    private JiraParams buildParamsToSave(int projectID, JiraParams fromInfoDTO) {
        JiraParams existing = this.jiraParamsRepository.findById(projectID).orElseGet(JiraParams::new);
        existing.setProjectID(projectID);
        existing.setMetricsScope(fromInfoDTO.getMetricsScope());
        existing.setRqRelease(fromInfoDTO.getRqRelease());

        return existing;
    }
}
