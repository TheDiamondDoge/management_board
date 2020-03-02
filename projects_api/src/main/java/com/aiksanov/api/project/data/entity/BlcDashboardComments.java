package com.aiksanov.api.project.data.entity;

import com.aiksanov.api.project.data.entity.pk.BlcDashboardPK;
import com.aiksanov.api.project.util.enums.BlcRoles;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "prj_blc_dashboard_comments")
@IdClass(BlcDashboardPK.class)
public class BlcDashboardComments {
    @Id
    @Column(name = "project_id")
    private int projectID;

    @Id
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private BlcRoles role;

    @Column(name = "comment")
    private String comment;

    public BlcDashboardComments() {
    }

    public BlcDashboardComments(BlcDashboardPK pk, String comment) {
        if (Objects.nonNull(pk)) {
            this.projectID = pk.getProjectID();
            this.role = pk.getRole();
        }
        this.comment = comment;
    }

    public BlcDashboardComments(int projectID, BlcRoles role, String comment) {
        this.projectID = projectID;
        this.role = role;
        this.comment = comment;
    }

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public BlcRoles getRole() {
        return role;
    }

    public void setRole(BlcRoles role) {
        this.role = role;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
