package com.aiksanov.api.project.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "prj_additional_info")
public class ProjectAdditionalInfo {
    @Id
    @Column(name = "project_id")
    private int projectID;

    @Column(name = "description")
    private String description;

    @Column(name = "business_line_manager")
    private String businessLineManager;

    @Column(name = "sponsor")
    private String sponsor;

    @Column(name = "oem_partner")
    private String oemPartner;

    public ProjectAdditionalInfo() {
    }

    public ProjectAdditionalInfo(int projectID, String description, String businessLineManager, String sponsor, String oemPartner) {
        this.projectID = projectID;
        this.description = description;
        this.businessLineManager = businessLineManager;
        this.sponsor = sponsor;
        this.oemPartner = oemPartner;
    }

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBusinessLineManager() {
        return businessLineManager;
    }

    public void setBusinessLineManager(String businessLineManager) {
        this.businessLineManager = businessLineManager;
    }

    public String getSponsor() {
        return sponsor;
    }

    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }

    public String getOemPartner() {
        return oemPartner;
    }

    public void setOemPartner(String oemPartner) {
        this.oemPartner = oemPartner;
    }
}
