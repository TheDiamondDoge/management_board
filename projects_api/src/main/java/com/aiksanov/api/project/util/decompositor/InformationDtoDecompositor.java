package com.aiksanov.api.project.util.decompositor;

import com.aiksanov.api.project.data.entity.*;
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
        return info;
    }

    public ProjectURLs getProjectUrlsObj() {
        ProjectURLs urls = new ProjectURLs();
        urls.setProjectID(this.projectId);
        urls.setCharter(this.dto.getCharter());
        urls.setOrBusinessPlan(this.dto.getOrBusinessPlan());
        urls.setUpdatedBusinessPlan(this.dto.getUpdatedBusinessPlan());
        urls.setTailoredChecklist(this.dto.getDrChecklist());
        urls.setLessonsLearned(this.dto.getLessonsLearned());
        urls.setCollabUrl(this.dto.getProjectCollabUrl());
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
}
