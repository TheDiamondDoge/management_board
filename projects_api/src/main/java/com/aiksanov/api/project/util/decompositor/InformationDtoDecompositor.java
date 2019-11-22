package com.aiksanov.api.project.util.decompositor;

import com.aiksanov.api.project.data.entity.*;
import com.aiksanov.api.project.data.entity.pk.ContributingProjectsPK;
import com.aiksanov.api.project.data.entity.pk.FieldCommentsPK;
import com.aiksanov.api.project.web.DTO.ContributingDTO;
import com.aiksanov.api.project.web.DTO.EcmaBacklogTargetDTO;
import com.aiksanov.api.project.web.DTO.InformationDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class InformationDtoDecompositor {
    private InformationDTO dto;
    private int projectId;

    public InformationDtoDecompositor() {
    }

    public InformationDtoDecompositor(InformationDTO dto, int projectId) {
        this.dto = dto;
        this.projectId = projectId;
    }

    public Project getProject() {
        Project prj = new Project();
        prj.setProjectID(this.projectId);
        prj.setType(this.dto.getProjectType());
        prj.setRigor(this.dto.getProjectRigor());
        prj.setState(this.dto.getProjectState());
        prj.setManager(this.dto.getProjectManager());

        Product product = buildProduct();
        ProjectAdditionalInfo info = buildProjectAdditionalInfo();

        prj.setProduct(product);
        prj.setAdditionalInfo(info);
        return prj;
    }

    private Product buildProduct() {
        Product product = new Product();
        product.setProjectID(this.projectId);
        product.setName(this.dto.getProductName());
        product.setRelease(this.dto.getProductRelease());
        product.setManager(this.dto.getProductLineManager());
        product.setDivision(this.dto.getBusinessDivision());
        product.setBusinessUnit(this.dto.getBusinessUnit());
        product.setProductLine(this.dto.getProductLine());
        return product;
    }

    private ProjectAdditionalInfo buildProjectAdditionalInfo() {
        ProjectAdditionalInfo info = new ProjectAdditionalInfo();
        info.setProjectID(this.projectId);
        info.setDescription(this.dto.getProjectDescription());
        info.setBusinessLineManager(this.dto.getBusinessLineManager());
        info.setSponsor(this.dto.getSponsor());
        info.setOemPartner(this.dto.getOemPartner());
        info.setKeyCustomers(this.dto.getKeyCustomers());
        info.setComposite(this.dto.isComposite());
        info.setMaintenance(this.dto.isMaintenance());
        return info;
    }

    public ProjectURLs getProjectUrlsObj() {
        ProjectURLs urls = new ProjectURLs();
        urls.setProjectID(this.projectId);
        urls.setCharter(this.dto.getCharter());
        urls.setOrBusinessPlan(this.dto.getOrBusinessPlan());

        if (Objects.nonNull(this.dto.getUpdatedBusinessPlan())) {
            urls.setUpdatedBusinessPlan(this.dto.getUpdatedBusinessPlan().getValue());
        }

        if (Objects.nonNull(this.dto.getDrChecklist())) {
            urls.setTailoredChecklist(this.dto.getDrChecklist().getValue());
        }

        if (Objects.nonNull(this.dto.getLessonsLearned())) {
            urls.setLessonsLearned(this.dto.getLessonsLearned().getValue());
        }

        if (Objects.nonNull(this.dto.getProjectPlan())) {
            urls.setProjectPlan(this.dto.getProjectPlan().getValue());
        }

        if (Objects.nonNull(this.dto.getLaunchingPlan())) {
            urls.setLaunchingPlan(this.dto.getLaunchingPlan().getValue());
        }

        urls.setCollabUrl(this.dto.getProjectCollabUrl());
        urls.setSalesForce(this.dto.getSalesForce());
        urls.setPwaUrl(this.dto.getProjectPWASiteUrl());
        urls.setDocumentsRepoUrl(this.dto.getDocRepositoryUrl());
        urls.setDefectsUrl(this.dto.getDefectsUrl());
        urls.setRequirementsUrl(this.dto.getRequirementsUrl());
        urls.setCisUrl(this.dto.getCisUrl());
        return urls;
    }

    public JiraParams getJiraParams() {
        JiraParams params = new JiraParams();
        params.setProjectID(this.projectId);
        params.setMetricsScope(this.dto.getMetricsScope());
        params.setRqRelease(this.dto.getRqRelease());
        return params;
    }

    public List<EcmaBacklogTarget> getEcmaBacklogTargetList() {
        List<EcmaBacklogTarget> list = new ArrayList<>();
        List<EcmaBacklogTargetDTO> ecmaBacklogTarget = this.dto.getEcmaBacklogTarget();

        if (Objects.isNull(ecmaBacklogTarget)) {
            return list;
        }

        list = ecmaBacklogTarget.stream()
                .map(dto -> dto.getEcmaBacklogTargetObj(projectId))
                .collect(Collectors.toList());

        return list;
    }

    public List<ContributingProjects> getListOfContribProjects() {
        List<ContributingProjects> contributingProjects = new ArrayList<>();
        List<ContributingDTO> contribDto = this.dto.getContributingProjects();

        if (Objects.isNull(contribDto)) {
            return contributingProjects;
        }

        contributingProjects = contribDto.stream()
                .map(project -> new ContributingProjects(new ContributingProjectsPK(this.projectId, project.getProjectID())))
                .collect(Collectors.toList());

        return contributingProjects;
    }

    public List<FieldComments> getListOfFieldComments() {
        List<FieldComments> result = new ArrayList<>();
        result.add(buildFieldComment("updatedBusinessPlan", dto.getUpdatedBusinessPlan().getComment()));
        result.add(buildFieldComment("drChecklist", dto.getDrChecklist().getComment()));
        result.add(buildFieldComment("lessonsLearned", dto.getLessonsLearned().getComment()));
        result.add(buildFieldComment("projectPlan", dto.getProjectPlan().getComment()));
        result.add(buildFieldComment("launchingPlan", dto.getLaunchingPlan().getComment()));

        return result;
    }

    private FieldComments buildFieldComment(String field, String comment) {
        return new FieldComments(
                new FieldCommentsPK(this.projectId, field), comment
        );
    }
}
