package com.aiksanov.api.project.web.DTO;

import com.aiksanov.api.project.data.entity.*;
import com.aiksanov.api.project.util.enums.CommentsFieldNames;

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
    private FieldWithCommentDTO updatedBusinessPlan;
    private FieldWithCommentDTO drChecklist;
    private FieldWithCommentDTO lessonsLearned;
    private FieldWithCommentDTO projectPlan;
    private FieldWithCommentDTO launchingPlan;
    private String metricsScope;
    private String rqRelease;
    private List<EcmaBacklogTargetDTO> ecmaBacklogTarget;
    private boolean composite;
    private boolean maintenance;
    private List<ContributingDTO> contributingProjects;
    private String projectCollabUrl;
    private String salesForce;
    private String projectPWASiteUrl;
    private String docRepositoryUrl;
    private String defectsUrl;
    private String requirementsUrl;
    private String cisUrl;
    private boolean epm;

    public InformationDTO(Project projectInfo, ProjectURLs urls, JiraParams jiraParams, List<EcmaBacklogTarget> target,
                          List<ContributingDTO> contributingProjects, List<FieldComments> comments) {
        if (Objects.nonNull(projectInfo)) {
            projectInfoMapping(projectInfo);
        }

        if (Objects.nonNull(urls)) {
            urlsMapping(urls);
        }

        if (Objects.nonNull(jiraParams)) {
            jiraMapping(jiraParams);
        }

        if (Objects.nonNull(target) && target.size() > 0) {
            backlogMapping(target);
        }

        if (Objects.nonNull(comments) && comments.size() > 0) {
            commentsMapping(comments);
        }

        this.contributingProjects = contributingProjects;
    }

    private void projectInfoMapping(Project projectInfo) {
        this.projectManager = projectInfo.getManager();
        this.projectRigor = projectInfo.getRigor();
        this.projectState = projectInfo.getState();
        this.projectType = projectInfo.getType();
        this.epm = projectInfo.isEpm();

        Product product = projectInfo.getProduct();
        if (Objects.nonNull(product)) {
            productMapping(product);
        }

        ProjectAdditionalInfo additionalInfo = projectInfo.getAdditionalInfo();
        if (Objects.nonNull(additionalInfo)) {
            additionalInfoMapping(additionalInfo);
        }
    }

    private void productMapping(Product product) {
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
        this.maintenance = additionalInfo.isMaintenance();
    }

    private void urlsMapping(ProjectURLs urls) {
        this.updatedBusinessPlan = new FieldWithCommentDTO();
        this.drChecklist = new FieldWithCommentDTO();
        this.lessonsLearned = new FieldWithCommentDTO();
        this.projectPlan = new FieldWithCommentDTO();
        this.launchingPlan = new FieldWithCommentDTO();

        this.charter = urls.getCharter();
        this.orBusinessPlan = urls.getOrBusinessPlan();
        this.updatedBusinessPlan.setValue(urls.getUpdatedBusinessPlan());
        this.drChecklist.setValue(urls.getTailoredChecklist());
        this.lessonsLearned.setValue(urls.getLessonsLearned());
        this.projectPlan.setValue(urls.getProjectPlan());
        this.launchingPlan.setValue(urls.getLaunchingPlan());
        this.projectCollabUrl = urls.getCollabUrl();
        this.salesForce = urls.getSalesForce();
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

    private void backlogMapping(List<EcmaBacklogTarget> target) {
        if (Objects.isNull(ecmaBacklogTarget)) {
            this.ecmaBacklogTarget = new ArrayList<>();
        }

        this.ecmaBacklogTarget = target.stream()
                .map(EcmaBacklogTargetDTO::new)
                .collect(Collectors.toList());
    }

    private void commentsMapping(List<FieldComments> comments) {
        String updatedBpComment = getCommentByFieldName(comments, CommentsFieldNames.UPDATED_BP);
        String drChecklistComment = getCommentByFieldName(comments, CommentsFieldNames.DR_CHECKLIST);
        String lessonsLearnedComment = getCommentByFieldName(comments, CommentsFieldNames.LESSONS_LEARNED);
        String projectPlanComment = getCommentByFieldName(comments, CommentsFieldNames.PROJECT_PLAN);
        String launchingPlanComment = getCommentByFieldName(comments, CommentsFieldNames.LAUNCHING_PLAN);

        this.updatedBusinessPlan.setComment(updatedBpComment);
        this.drChecklist.setComment(drChecklistComment);
        this.lessonsLearned.setComment(lessonsLearnedComment);
        this.projectPlan.setComment(projectPlanComment);
        this.launchingPlan.setComment(launchingPlanComment);
    }

    private String getCommentByFieldName(List<FieldComments> comments, CommentsFieldNames fieldName) {
        return comments.stream()
                .filter(obj -> obj.getPk().getFieldName().equals(fieldName.getTitle()))
                .findFirst().orElseGet(FieldComments::new)
                .getComment();
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

    public FieldWithCommentDTO getUpdatedBusinessPlan() {
        return updatedBusinessPlan;
    }

    public FieldWithCommentDTO getDrChecklist() {
        return drChecklist;
    }

    public FieldWithCommentDTO getLessonsLearned() {
        return lessonsLearned;
    }

    public FieldWithCommentDTO getProjectPlan() {
        return projectPlan;
    }

    public FieldWithCommentDTO getLaunchingPlan() {
        return launchingPlan;
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

    public boolean isMaintenance() {
        return maintenance;
    }

    public boolean isComposite() {
        return composite;
    }

    public List<ContributingDTO> getContributingProjects() {
        return contributingProjects;
    }

    public String getProjectCollabUrl() {
        return projectCollabUrl;
    }

    public String getSalesForce() {
        return salesForce;
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

    public boolean isEpm() {
        return epm;
    }
}
