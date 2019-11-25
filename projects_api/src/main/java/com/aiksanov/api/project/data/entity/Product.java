package com.aiksanov.api.project.data.entity;

import javax.persistence.*;

@Entity
@Table(name = "prj_products")
public class Product {
    @Id
    @Column(name = "project_id")
    private int projectID;

    @Column(name = "product_line")
    private String productLine;

    @Column(name = "product_name")
    private String name;

    @Column(name = "product_manager")
    private String manager;

    @Column(name = "product_group")
    private String group;

    @Column(name = "product_division")
    private String division;

    @Column(name = "product_bu")
    private String businessUnit;
    //TODO: ???
    @Column(name = "product_unit")
    private String productUnit;

    @Column(name = "product_team")
    private String team;

    @Column(name = "product_release")
    private String release;

    public Product() {
    }

    public Product(int projectID) {
        this.projectID = projectID;
    }

    public Product(String productLine, String name, String manager, String group, String division, String businessUnit, String productUnit, String team, String release, int projectID) {
        this.productLine = productLine;
        this.name = name;
        this.manager = manager;
        this.group = group;
        this.division = division;
        this.businessUnit = businessUnit;
        this.productUnit = productUnit;
        this.team = team;
        this.release = release;
        this.projectID = projectID;
    }

    public String getProductLine() {
        return productLine;
    }

    public void setProductLine(String productLine) {
        this.productLine = productLine;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getBusinessUnit() {
        return businessUnit;
    }

    public void setBusinessUnit(String businessUnit) {
        this.businessUnit = businessUnit;
    }

    public String getProductUnit() {
        return productUnit;
    }

    public void setProductUnit(String productUnit) {
        this.productUnit = productUnit;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }
}

