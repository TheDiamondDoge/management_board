package com.aiksanov.api.project.business.service;

import com.aiksanov.api.project.data.entity.*;
import com.aiksanov.api.project.data.repository.*;
import com.aiksanov.api.project.util.decompositor.InformationDtoDecompositor;
import com.aiksanov.api.project.web.DTO.ContributingDTO;
import com.aiksanov.api.project.web.DTO.InformationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class InformationTabService {
    private GeneralRepository generalRepository;
    private ProjectURLsRepository urlsRepository;
    private JiraParamsRepository jiraParamsRepository;
    private EcmaBacklogTargetRepo backlogTargetRepo;
    private ContributingProjectsRepository contribProjectsRepo;

    @Value("${project.error.projectNotFound}")
    private String projectNotFoundMessage;

    @Autowired
    public InformationTabService(GeneralRepository generalRepository, ProjectURLsRepository urlsRepository,
                                 JiraParamsRepository jiraParamsRepository, EcmaBacklogTargetRepo backlogTargetRepo,
                                 ContributingProjectsRepository contribProjectsRepo) {
        this.generalRepository = generalRepository;
        this.urlsRepository = urlsRepository;
        this.jiraParamsRepository = jiraParamsRepository;
        this.backlogTargetRepo = backlogTargetRepo;
        this.contribProjectsRepo = contribProjectsRepo;
    }

    public InformationDTO getInfoTabData(Integer id) {
        if (Objects.isNull(id)) {
            throw new RuntimeException("Parameter 'id' not found");
        }
        Project project = this.generalRepository.findById(id).orElseThrow(() -> new RuntimeException(projectNotFoundMessage));
        ProjectURLs urls = this.urlsRepository.findById(id).orElseGet(ProjectURLs::new);
        JiraParams jiraParams = this.jiraParamsRepository.findById(id).orElseGet(JiraParams::new);
        List<EcmaBacklogTarget> target = this.backlogTargetRepo.findAllByProjectId(id);
        List<ContributingDTO> contribProjects = getContribProjectDtosList(id);
        return new InformationDTO(project, urls, jiraParams, target, contribProjects);
    }

    private List<ContributingDTO> getContribProjectDtosList(int projectID) {
        List<Project> contribProjects = this.generalRepository.findAllContribProjectsByProjectID(projectID);
        return contribProjects.stream()
                .map((prj) -> (new ContributingDTO(prj.getProjectID(), prj.getName())))
                .collect(Collectors.toList());
    }

    @Transactional
    public void saveInformationData(Integer id, InformationDTO dto) {
        InformationDtoDecompositor decompositor = new InformationDtoDecompositor(dto, id);
        Project project = decompositor.getProject();
        ProjectURLs urLs = decompositor.getProjectUrlsObj();
        JiraParams params = decompositor.getJiraParams();
        List<EcmaBacklogTarget> target = decompositor.getEcmaBacklogTargetList();
        this.generalRepository.save(buildProjectToSave(id, project));
        this.urlsRepository.save(buildUrlsToSave(id, urLs));
        this.jiraParamsRepository.save(buildParamsToSave(id, params));

        this.backlogTargetRepo.deleteAllByProjectId(id);
        this.backlogTargetRepo.saveAll(target);
    }

    private Project buildProjectToSave(int projectID, Project fromInfoDTO) {
        Project existing = this.generalRepository.findById(projectID).orElseGet(Project::new);
        existing.setProjectID(projectID);
        existing.setType(fromInfoDTO.getType());
        existing.setRigor(fromInfoDTO.getRigor());
        existing.setState(fromInfoDTO.getState());
        existing.setManager(fromInfoDTO.getManager());

        Product productfromDto = fromInfoDTO.getProduct();
        existing.getProduct().setProductLine(productfromDto.getProductLine());
        existing.getProduct().setName(productfromDto.getName());
        existing.getProduct().setManager(productfromDto.getManager());
        existing.getProduct().setDivision(productfromDto.getDivision());
        existing.getProduct().setBusinessUnit(productfromDto.getBusinessUnit());
        existing.getProduct().setRelease(productfromDto.getRelease());

        ProjectAdditionalInfo additionalFromDto = fromInfoDTO.getAdditionalInfo();
        existing.getAdditionalInfo().setDescription(additionalFromDto.getDescription());
        existing.getAdditionalInfo().setBusinessLineManager(additionalFromDto.getBusinessLineManager());
        existing.getAdditionalInfo().setSponsor(additionalFromDto.getSponsor());
        existing.getAdditionalInfo().setOemPartner(additionalFromDto.getOemPartner());
        existing.getAdditionalInfo().setKeyCustomers(additionalFromDto.getKeyCustomers());
        existing.getAdditionalInfo().setComposite(additionalFromDto.isComposite());

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
        existing.setCollabUrl(fromInfoDTO.getCollabUrl());
        existing.setPwaUrl(fromInfoDTO.getPwaUrl());
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
