package com.aiksanov.api.project.data.entity;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;

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

    //TODO: To remove I guess
    @Column(name = "project_completion")
    private int percentOfCompletion;

    @Column(name = "EPM_project")
    private boolean epm;

    @Column(name = "project_template")
    private String template;

    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    @OneToOne
    @JoinColumn(name = "project_id", referencedColumnName = "project_id")
    private Product product;

    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    @OneToOne
    @JoinColumn(name = "project_id", referencedColumnName = "project_id")
    private WorkspaceInfo workspaceInfo;

    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    @OneToOne
    @JoinColumn(name = "project_id", referencedColumnName = "project_id")
    private ProjectAdditionalInfo additionalInfo;


    public Project() {
    }

    public Project(int projectID) {
        this.projectID = projectID;
    }

    public Project(String name, String type, String rigor, String state, String manager, int percentOfCompletion,
                   boolean epm, String template, Product product, WorkspaceInfo workspaceInfo,
                   ProjectAdditionalInfo additionalInfo)
    {
        this.name = name;
        this.type = type;
        this.rigor = rigor;
        this.state = state;
        this.manager = manager;
        this.percentOfCompletion = percentOfCompletion;
        this.epm = epm;
        this.template = template;
        this.product = product;
        this.workspaceInfo = workspaceInfo;
        this.additionalInfo = additionalInfo;
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
}
