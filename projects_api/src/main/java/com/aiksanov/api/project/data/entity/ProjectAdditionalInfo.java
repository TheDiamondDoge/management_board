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

    @Column(name = "key_customer")
    private String keyCustomers;

    @Column(name = "composite")
    private boolean composite;

    @Column(name = "maintenance")
    private boolean maintenance;


    public ProjectAdditionalInfo(int projectID) {
        this.projectID = projectID;
    }

    //TODO it is no AllArgsConstr
    public ProjectAdditionalInfo(int projectID, String description, String businessLineManager, String sponsor,
                                 String oemPartner, boolean composite, boolean maintenance) {
        this.projectID = projectID;
        this.description = description;
        this.businessLineManager = businessLineManager;
        this.sponsor = sponsor;
        this.oemPartner = oemPartner;
        this.composite = composite;
        this.maintenance = maintenance;
    }
}
