package com.aiksanov.api.project.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "prj_urls")
public class ProjectURLs {
    @Id
    @Column(name = "project_id")
    private int projectID;

    @Column(name = "charter")
    private String charter;

    @Column(name = "or_business_plan")
    private String orBusinessPlan;

    @Column(name = "updated_business_plan")
    private String updatedBusinessPlan;

    @Column(name = "tailored_checklist")
    private String tailoredChecklist;

    @Column(name = "lessons_learned")
    private String lessonsLearned;

    @Column(name = "project_plan")
    private String projectPlan;

    @Column(name = "project_collab_url")
    private String collabUrl;

    @Column(name = "project_pwa_url")
    private String pwaUrl;

    @Column(name = "document_repo_url")
    private String documentsRepoUrl;

    @Column(name = "defects_url")
    private String defectsUrl;

    @Column(name = "requirements_url")
    private String requirementsUrl;

    @Column(name = "cis_url")
    private String cisUrl;


    public ProjectURLs() {
    }

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public String getCharter() {
        return charter;
    }

    public void setCharter(String charter) {
        this.charter = charter;
    }

    public String getOrBusinessPlan() {
        return orBusinessPlan;
    }

    public void setOrBusinessPlan(String orBusinessPlan) {
        this.orBusinessPlan = orBusinessPlan;
    }

    public String getUpdatedBusinessPlan() {
        return updatedBusinessPlan;
    }

    public void setUpdatedBusinessPlan(String updatedBusinessPlan) {
        this.updatedBusinessPlan = updatedBusinessPlan;
    }

    public String getTailoredChecklist() {
        return tailoredChecklist;
    }

    public void setTailoredChecklist(String tailoredChecklist) {
        this.tailoredChecklist = tailoredChecklist;
    }

    public String getLessonsLearned() {
        return lessonsLearned;
    }

    public void setLessonsLearned(String lessonsLearned) {
        this.lessonsLearned = lessonsLearned;
    }

    public String getProjectPlan() {
        return projectPlan;
    }

    public void setProjectPlan(String projectPlan) {
        this.projectPlan = projectPlan;
    }

    public String getCollabUrl() {
        return collabUrl;
    }

    public void setCollabUrl(String collabUrl) {
        this.collabUrl = collabUrl;
    }

    public String getPwaUrl() {
        return pwaUrl;
    }

    public void setPwaUrl(String pwaUrl) {
        this.pwaUrl = pwaUrl;
    }

    public String getDocumentsRepoUrl() {
        return documentsRepoUrl;
    }

    public void setDocumentsRepoUrl(String documentsRepoUrl) {
        this.documentsRepoUrl = documentsRepoUrl;
    }

    public String getDefectsUrl() {
        return defectsUrl;
    }

    public void setDefectsUrl(String defectsUrl) {
        this.defectsUrl = defectsUrl;
    }

    public String getRequirementsUrl() {
        return requirementsUrl;
    }

    public void setRequirementsUrl(String requirementsUrl) {
        this.requirementsUrl = requirementsUrl;
    }

    public String getCisUrl() {
        return cisUrl;
    }

    public void setCisUrl(String cisUrl) {
        this.cisUrl = cisUrl;
    }
}
