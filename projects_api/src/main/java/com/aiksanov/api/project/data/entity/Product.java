package com.aiksanov.api.project.data.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
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
}

