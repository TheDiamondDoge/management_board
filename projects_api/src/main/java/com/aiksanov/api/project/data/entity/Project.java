package com.aiksanov.api.project.data.entity;

import javax.persistence.*;
import java.util.List;

//TODO: PWS status - separate entity (???)
//TODO: milestones -> to remove due to indicators page saving (???)
@Entity
@Table(name = "PRJ_WORKSPACE_GENERAL")
public class Project {
    @Id
    @Column(name = "project_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int projectID;

    @Column(name = "project_uid")
    private String uid;

    @Column(name = "project_name")
    private String name;

    @Column(name = "project_type")
    private String type;

    @Column(name = "project_rigor")
    private String rigor;

    @Column(name = "project_state")
    private String state;

    @Column(name = "project_manager")
    private String manager;

    @Column(name = "project_completion")
    private int percentOfCompletion;

    @Column(name = "EPM_project")
    private boolean epm;

    @Column(name = "project_template")
    private String template;

    @OneToOne
    @JoinColumn(name = "project_id", referencedColumnName = "project_id")
    private Product product;

    @OneToMany
    @JoinColumn(name = "project_id", referencedColumnName = "project_id")
    private List<Milestone> milestones;

    @OneToOne
    @JoinColumn(name = "project_id", referencedColumnName = "project_id")
    private WorkspaceInfo workspaceInfo;

    @OneToOne
    @JoinColumn(name = "project_id", referencedColumnName = "project_id")
    private ProjectAdditionalInfo additionalInfo;

//    @OneToMany
//    @JoinColumn(name = "project_id", referencedColumnName = "project_id")
//    private List<HealthIndicators> healthIndicators;

    public Project() {
    }

    public Project(String uid, String name, String type, String rigor, String state, String manager,
                   int percentOfComplition, boolean epm, String template, Product product, List<Milestone> milestones) {
        this.uid = uid;
        this.name = name;
        this.type = type;
        this.rigor = rigor;
        this.state = state;
        this.manager = manager;
        this.percentOfCompletion = percentOfComplition;
        this.epm = epm;
        this.template = template;
        this.product = product;
        this.milestones = milestones;
    }

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRigor() {
        return rigor;
    }

    public void setRigor(String rigor) {
        this.rigor = rigor;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public int getPercentOfCompletion() {
        return percentOfCompletion;
    }

    public void setPercentOfCompletion(int percentOfCompletion) {
        this.percentOfCompletion = percentOfCompletion;
    }

    public boolean isEpm() {
        return epm;
    }

    public void setEpm(boolean epm) {
        this.epm = epm;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Milestone> getMilestones() {
        return milestones;
    }

    public void setMilestones(List<Milestone> milestones) {
        this.milestones = milestones;
    }

    public WorkspaceInfo getWorkspaceInfo() {
        return workspaceInfo;
    }

    public void setWorkspaceInfo(WorkspaceInfo workspaceInfo) {
        this.workspaceInfo = workspaceInfo;
    }

    public ProjectAdditionalInfo getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(ProjectAdditionalInfo additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

//    public Set<HealthIndicators> getHealthIndicators() {
//        return healthIndicators;
//    }
//
//    public void setHealthIndicators(Set<HealthIndicators> healthIndicators) {
//        this.healthIndicators = healthIndicators;
//    }
}
