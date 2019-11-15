package com.aiksanov.api.project.web.DTO;

import com.aiksanov.api.project.data.entity.*;

import java.util.*;
import java.util.stream.Collectors;

public class InformationDTO {
    private String projectDescription;
    private String oemPartner;
    private String keyCustomers;
    private String productRelease;
    private String projectType;
    private String projectRigor;
    private String projectState;
    private String businessDivision;
    private String businessUnit;
    private String productLine;
    private String productName;
    private String sponsor;
    private String businessLineManager;
    private String productLineManager;
    private String projectManager;
    private String charter;
    private String orBusinessPlan;
    private String updatedBusinessPlan;
    private String drChecklist;
    private String lessonsLearned;
    private String projectPlan;
    private String metricsScope;
    private String rqRelease;
    private List<EcmaBacklogTargetDTO> ecmaBacklogTarget;
    private boolean composite;
    private String projectCollabUrl;
    private String projectPWASiteUrl;
    private String docRepositoryUrl;
    private String defectsUrl;
    private String requirementsUrl;
    private String cisUrl;

    public InformationDTO(Project projectInfo, ProjectURLs urls, JiraParams jiraParams, List<EcmaBacklogTarget> target) {
        if (Objects.nonNull(projectInfo)) {
            projectInfoMapping(projectInfo);
        }

        if (Objects.nonNull(urls)) {
            urlsMapping(urls);
        }

        if (Objects.nonNull(jiraParams)){
            jiraMapping(jiraParams);
        }

        if (Objects.nonNull(target) && target.size() > 0) {
            setEcmaBacklogTarget(target);
        }

    }

    private void projectInfoMapping(Project projectInfo) {
        this.projectManager = projectInfo.getManager();
        this.projectRigor = projectInfo.getRigor();
        this.projectState = projectInfo.getState();
        this.projectType = projectInfo.getType();

        Product product = projectInfo.getProduct();
        if (Objects.nonNull(product)) {
            productMapping(product);
        }

        ProjectAdditionalInfo additionalInfo = projectInfo.getAdditionalInfo();
        if (Objects.nonNull(additionalInfo)) {
            additionalInfoMapping(additionalInfo);
        }
    }

    private void productMapping(Product product){
        this.productName = product.getName();
        this.productRelease = product.getRelease();
        this.productLineManager = product.getManager();
        this.businessDivision = product.getDivision();
        this.businessUnit = product.getBusinessUnit();
        this.productLine = product.getProductLine();
    }

    private void additionalInfoMapping(ProjectAdditionalInfo additionalInfo) {
        this.projectDescription = additionalInfo.getDescription();
        this.businessLineManager = additionalInfo.getBusinessLineManager();
        this.sponsor = additionalInfo.getSponsor();
        this.oemPartner = additionalInfo.getOemPartner();
        this.keyCustomers = additionalInfo.getKeyCustomers();
        this.composite = additionalInfo.isComposite();
    }

    private void urlsMapping(ProjectURLs urls) {
        this.charter = urls.getCharter();
        this.orBusinessPlan = urls.getOrBusinessPlan();
        this.updatedBusinessPlan = urls.getUpdatedBusinessPlan();
        this.drChecklist = urls.getTailoredChecklist();
        this.lessonsLearned = urls.getLessonsLearned();
        this.projectPlan = urls.getProjectPlan();
        this.projectCollabUrl = urls.getCollabUrl();
        this.projectPWASiteUrl = urls.getPwaUrl();
        this.docRepositoryUrl = urls.getDocumentsRepoUrl();
        this.defectsUrl = urls.getDefectsUrl();
        this.requirementsUrl = urls.getRequirementsUrl();
        this.cisUrl = urls.getCisUrl();
    }

    private void jiraMapping(JiraParams jiraParams) {
        this.metricsScope = jiraParams.getMetricsScope();
        this.rqRelease = jiraParams.getRqRelease();
    }

    private void setEcmaBacklogTarget(List<EcmaBacklogTarget> target) {
        if (Objects.isNull(ecmaBacklogTarget)) {
            this.ecmaBacklogTarget = new ArrayList<>();
        }

        this.ecmaBacklogTarget = target.stream()
                .map(EcmaBacklogTargetDTO::new)
                .collect(Collectors.toList());
    }

    public Project buildProjectObjWithId(int id) {
        Project prj = new Project();
        prj.setProjectID(id);
        prj.setType(projectType);
        prj.setRigor(projectRigor);
        prj.setState(projectState);
        prj.setManager(projectManager);

        Product product = buildProduct();
        product.setProjectID(id);

        ProjectAdditionalInfo info = buildProjectAdditionalInfo();
        info.setProjectID(id);

        prj.setProduct(product);
        prj.setAdditionalInfo(info);
        return prj;
    }

    private Product buildProduct() {
        Product product = new Product();
        product.setName(productName);
        product.setRelease(productRelease);
        product.setManager(productLineManager);
        product.setDivision(businessDivision);
        product.setBusinessUnit(businessUnit);
        product.setProductLine(productLine);
        return product;
    }

    private ProjectAdditionalInfo buildProjectAdditionalInfo() {
        ProjectAdditionalInfo info = new ProjectAdditionalInfo();
        info.setDescription(projectDescription);
        info.setBusinessLineManager(businessLineManager);
        info.setSponsor(sponsor);
        info.setOemPartner(oemPartner);
        info.setKeyCustomers(keyCustomers);
        info.setComposite(composite);
        return info;
    }

    public ProjectURLs getProjectUrlsObj() {
        ProjectURLs urls = new ProjectURLs();
        urls.setCharter(charter);
        urls.setOrBusinessPlan(orBusinessPlan);
        urls.setUpdatedBusinessPlan(updatedBusinessPlan);
        urls.setTailoredChecklist(drChecklist);
        urls.setLessonsLearned(lessonsLearned);
        urls.setCollabUrl(projectCollabUrl);
        urls.setPwaUrl(projectPWASiteUrl);
        urls.setDocumentsRepoUrl(docRepositoryUrl);
        urls.setDefectsUrl(defectsUrl);
        urls.setRequirementsUrl(requirementsUrl);
        urls.setCisUrl(cisUrl);
        return urls;
    }

    public JiraParams getJiraParams() {
        JiraParams params = new JiraParams();
        params.setMetricsScope(this.metricsScope);
        params.setRqRelease(this.rqRelease);
        return params;
    }

    public List<EcmaBacklogTarget> getEcmaBacklogTargetList(int projectId) {
        List<EcmaBacklogTarget> list = new ArrayList<>();
        if (Objects.isNull(this.ecmaBacklogTarget)) {
            return list;
        }

        list = this.ecmaBacklogTarget.stream()
                .map(dto -> dto.getEcmaBacklogTargetObj(projectId))
                .collect(Collectors.toList());

        return list;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public String getOemPartner() {
        return oemPartner;
    }

    public String getKeyCustomers() {
        return keyCustomers;
    }

    public String getProductRelease() {
        return productRelease;
    }

    public String getProjectType() {
        return projectType;
    }

    public String getProjectRigor() {
        return projectRigor;
    }

    public String getProjectState() {
        return projectState;
    }

    public String getBusinessDivision() {
        return businessDivision;
    }

    public String getBusinessUnit() {
        return businessUnit;
    }

    public String getProductLine() {
        return productLine;
    }

    public String getProductName() {
        return productName;
    }

    public String getSponsor() {
        return sponsor;
    }

    public String getBusinessLineManager() {
        return businessLineManager;
    }

    public String getProductLineManager() {
        return productLineManager;
    }

    public String getProjectManager() {
        return projectManager;
    }

    public String getCharter() {
        return charter;
    }

    public String getOrBusinessPlan() {
        return orBusinessPlan;
    }

    public String getUpdatedBusinessPlan() {
        return updatedBusinessPlan;
    }

    public String getDrChecklist() {
        return drChecklist;
    }

    public String getLessonsLearned() {
        return lessonsLearned;
    }

    public String getProjectPlan() {
        return projectPlan;
    }

    public String getMetricsScope() {
        return metricsScope;
    }

    public String getRqRelease() {
        return rqRelease;
    }

    public List<EcmaBacklogTargetDTO> getEcmaBacklogTarget() {
        return ecmaBacklogTarget;
    }

    public boolean isComposite() {
        return composite;
    }

    public String getProjectCollabUrl() {
        return projectCollabUrl;
    }

    public String getProjectPWASiteUrl() {
        return projectPWASiteUrl;
    }

    public String getDocRepositoryUrl() {
        return docRepositoryUrl;
    }

    public String getDefectsUrl() {
        return defectsUrl;
    }

    public String getRequirementsUrl() {
        return requirementsUrl;
    }

    public String getCisUrl() {
        return cisUrl;
    }
}
