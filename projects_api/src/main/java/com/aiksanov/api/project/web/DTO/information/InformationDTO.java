package com.aiksanov.api.project.web.DTO.information;

import com.aiksanov.api.project.data.entity.*;
import com.aiksanov.api.project.data.entity.pk.ContributingProjectsPK;
import com.aiksanov.api.project.data.entity.pk.FieldCommentsPK;
import com.aiksanov.api.project.util.enums.CommentsFieldNames;
import com.aiksanov.api.project.util.enums.ProjectRigors;
import com.aiksanov.api.project.util.enums.ProjectStates;
import com.aiksanov.api.project.util.enums.ProjectTypes;
import com.aiksanov.api.project.web.DTO.MilestoneDTO;
import com.aiksanov.api.project.web.DTO.contrib.ContributingDTO;
import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

@Getter
public class InformationDTO {
    private String projectDescription;
    private String oemPartner;
    private String keyCustomers;
    private String productRelease;
    private String projectType;
    private String projectRigor;
    private ProjectStates projectState;
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
    private final List<ContributingDTO> contributingProjects;
    private String projectCollabUrl;
    private String salesForce;
    private String projectPWASiteUrl;
    private String docRepositoryUrl;
    private String defectsUrl;
    private String requirementsUrl;
    private String cisUrl;
    private boolean epm;
    private List<MilestoneDTO> milestones;

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
        this.projectRigor = projectInfo.getRigor().getLabel();
        this.projectState = projectInfo.getState();
        this.projectType = projectInfo.getType().getValue();
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

    public Project getProject(int projectId) {
        Project prj = new Project();
        prj.setProjectID(projectId);
        prj.setType(ProjectTypes.getTypeIgnoreCase(projectType));

        ProjectRigors rigor = ProjectRigors.getTypeIgnoreCase(projectRigor);
        prj.setRigor(rigor);

        prj.setState(projectState);
        prj.setManager(projectManager);

        Product product = buildProduct(projectId);
        ProjectAdditionalInfo info = buildProjectAdditionalInfo(projectId);

        prj.setProduct(product);
        prj.setAdditionalInfo(info);
        return prj;
    }

    private Product buildProduct(int projectId) {
        Product product = new Product();
        product.setProjectID(projectId);
        product.setName(productName);
        product.setRelease(productRelease);
        product.setManager(productLineManager);
        product.setDivision(businessDivision);
        product.setBusinessUnit(businessUnit);
        product.setProductLine(productLine);
        return product;
    }

    private ProjectAdditionalInfo buildProjectAdditionalInfo(int projectId) {
        ProjectAdditionalInfo info = new ProjectAdditionalInfo();
        info.setProjectID(projectId);
        info.setDescription(projectDescription);
        info.setBusinessLineManager(businessLineManager);
        info.setSponsor(sponsor);
        info.setOemPartner(oemPartner);
        info.setKeyCustomers(keyCustomers);
        info.setComposite(composite);
        info.setMaintenance(maintenance);
        return info;
    }

    public ProjectURLs getProjectUrlsObj(int projectId) {
        ProjectURLs urls = new ProjectURLs();
        urls.setProjectID(projectId);
        urls.setCharter(charter);
        urls.setOrBusinessPlan(orBusinessPlan);

        if (Objects.nonNull(updatedBusinessPlan)) {
            urls.setUpdatedBusinessPlan(updatedBusinessPlan.getValue());
        }

        if (Objects.nonNull(drChecklist)) {
            urls.setTailoredChecklist(drChecklist.getValue());
        }

        if (Objects.nonNull(lessonsLearned)) {
            urls.setLessonsLearned(lessonsLearned.getValue());
        }

        if (Objects.nonNull(projectPlan)) {
            urls.setProjectPlan(projectPlan.getValue());
        }

        if (Objects.nonNull(launchingPlan)) {
            urls.setLaunchingPlan(launchingPlan.getValue());
        }

        urls.setCollabUrl(projectCollabUrl);
        urls.setSalesForce(salesForce);
        urls.setPwaUrl(projectPWASiteUrl);
        urls.setDocumentsRepoUrl(docRepositoryUrl);
        urls.setDefectsUrl(defectsUrl);
        urls.setRequirementsUrl(requirementsUrl);
        urls.setCisUrl(cisUrl);
        return urls;
    }

    public JiraParams getJiraParams(int projectId) {
        JiraParams params = new JiraParams();
        params.setProjectID(projectId);
        params.setMetricsScope(metricsScope);
        params.setRqRelease(rqRelease);
        return params;
    }

    public List<EcmaBacklogTarget> getEcmaBacklogTargetList(int projectId) {
        List<EcmaBacklogTarget> list = new ArrayList<>();
        List<EcmaBacklogTargetDTO> ecmaBacklogTarget = getEcmaBacklogTarget();

        if (Objects.isNull(ecmaBacklogTarget)) {
            return list;
        }

        list = ecmaBacklogTarget.stream()
                .filter(dto -> Objects.nonNull(dto.getMilestone()))
                .map(dto -> dto.getEcmaBacklogTargetObj(projectId))
                .collect(Collectors.toList());

        return list;
    }

    public List<ContributingProjects> getListOfContribProjects(int projectId) {
        List<ContributingProjects> contributingProjects = new ArrayList<>();
        List<ContributingDTO> contribDto = getContributingProjects();

        if (Objects.isNull(contribDto)) {
            return contributingProjects;
        }

        contributingProjects = contribDto.stream()
                .map(project -> new ContributingProjects(new ContributingProjectsPK(projectId, project.getProjectID())))
                .collect(Collectors.toList());

        return contributingProjects;
    }

    public List<FieldComments> getListOfFieldComments(int projectId) {
        List<FieldComments> result = new ArrayList<>();
        result.add(buildFieldComment(CommentsFieldNames.UPDATED_BP, updatedBusinessPlan.getComment(), projectId));
        result.add(buildFieldComment(CommentsFieldNames.DR_CHECKLIST, drChecklist.getComment(), projectId));
        result.add(buildFieldComment(CommentsFieldNames.LESSONS_LEARNED, lessonsLearned.getComment(), projectId));
        result.add(buildFieldComment(CommentsFieldNames.PROJECT_PLAN, projectPlan.getComment(), projectId));
        result.add(buildFieldComment(CommentsFieldNames.LAUNCHING_PLAN, launchingPlan.getComment(), projectId));

        return result;
    }

    private FieldComments buildFieldComment(CommentsFieldNames field, String comment, int projectId) {
        return new FieldComments(
                new FieldCommentsPK(projectId, field.getTitle()), comment
        );
    }
}
