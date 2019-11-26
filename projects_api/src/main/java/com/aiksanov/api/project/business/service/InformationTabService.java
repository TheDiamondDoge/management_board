package com.aiksanov.api.project.business.service;

import com.aiksanov.api.project.data.entity.*;
import com.aiksanov.api.project.data.repository.*;
import com.aiksanov.api.project.util.ServiceUtils;
import com.aiksanov.api.project.util.decompositor.InformationDtoDecompositor;
import com.aiksanov.api.project.web.DTO.ContributingDTO;
import com.aiksanov.api.project.web.DTO.InformationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class InformationTabService {
    private GeneralRepository generalRepository;
    private ProjectURLsRepository urlsRepository;
    private JiraParamsRepository jiraParamsRepository;
    private EcmaBacklogTargetRepo backlogTargetRepo;
    private ContributingProjectsRepository contribProjectsRepo;
    private FieldCommentsRepository commentsRepository;
    private ServiceUtils utils;


    @Autowired
    public InformationTabService(GeneralRepository generalRepository, ProjectURLsRepository urlsRepository,
                                 JiraParamsRepository jiraParamsRepository, EcmaBacklogTargetRepo backlogTargetRepo,
                                 ContributingProjectsRepository contribProjectsRepo, FieldCommentsRepository comments,
                                 ServiceUtils utils) {
        this.generalRepository = generalRepository;
        this.urlsRepository = urlsRepository;
        this.jiraParamsRepository = jiraParamsRepository;
        this.backlogTargetRepo = backlogTargetRepo;
        this.contribProjectsRepo = contribProjectsRepo;
        this.commentsRepository = comments;
        this.utils = utils;
    }

    @Transactional
    public InformationDTO getInfoTabData(Integer id) {
        if (Objects.isNull(id) || !utils.isProjectExist(id)) {
            throw new RuntimeException("Project not found");
        }
        Project project = this.generalRepository.findById(id).orElseThrow(() -> new RuntimeException("Project not found"));
        ProjectURLs urls = this.urlsRepository.findById(id).orElseGet(ProjectURLs::new);
        JiraParams jiraParams = this.jiraParamsRepository.findById(id).orElseGet(JiraParams::new);
        List<EcmaBacklogTarget> target = this.backlogTargetRepo.findAllByProjectId(id);
        List<ContributingDTO> contribProjects = getContribProjectDtosList(id);
        List<FieldComments> comments = this.commentsRepository.findAllByPk_ProjectID(id);
        return new InformationDTO(project, urls, jiraParams, target, contribProjects, comments);
    }

    private List<ContributingDTO> getContribProjectDtosList(int projectID) {
        List<Project> contribProjects = this.generalRepository.findAllContribProjectsByProjectID(projectID);
        return contribProjects.stream()
                .map((prj) -> (new ContributingDTO(prj.getProjectID(), prj.getName())))
                .collect(Collectors.toList());
    }

    @Transactional
    public void saveInformationData(Integer id, InformationDTO dto) {
        if (Objects.isNull(id) || !utils.isProjectExist(id)) {
            throw new RuntimeException("Project not found");
        }

        if (Objects.isNull(dto)) {
            throw new RuntimeException("No data");
        }

        InformationDtoDecompositor decompositor = new InformationDtoDecompositor(dto, id);
        Project project = decompositor.getProject();
        ProjectURLs urLs = decompositor.getProjectUrlsObj();
        JiraParams params = decompositor.getJiraParams();
        List<EcmaBacklogTarget> target = decompositor.getEcmaBacklogTargetList();
        List<ContributingProjects> contributingProjects = decompositor.getListOfContribProjects();
        List<FieldComments> comments = decompositor.getListOfFieldComments();

        this.generalRepository.save(buildProjectToSave(id, project));
        this.urlsRepository.save(buildUrlsToSave(id, urLs));
        this.jiraParamsRepository.save(buildParamsToSave(id, params));

        this.backlogTargetRepo.deleteAllByProjectId(id);
        this.backlogTargetRepo.saveAll(target);

        this.contribProjectsRepo.deleteAllByPk_ProjectID(id);
        this.contribProjectsRepo.saveAll(contributingProjects);

        this.commentsRepository.deleteAllByPk_ProjectID(id);
        this.commentsRepository.saveAll(comments);
    }

    private Project buildProjectToSave(int projectID, Project fromInfoDTO) {
        Project existing = this.generalRepository.findById(projectID).orElseThrow(() -> new RuntimeException("Project not found"));
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
