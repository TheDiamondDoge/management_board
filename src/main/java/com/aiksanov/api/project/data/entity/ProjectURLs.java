package com.aiksanov.api.project.data.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
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

    @Column(name = "launching_plan")
    private String launchingPlan;

    @Column(name = "project_collab_url")
    private String collabUrl;

    @Column(name = "sales_force")
    private String salesForce;

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
}
