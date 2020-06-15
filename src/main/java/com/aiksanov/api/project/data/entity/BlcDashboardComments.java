package com.aiksanov.api.project.data.entity;

import com.aiksanov.api.project.data.entity.pk.BlcDashboardPK;
import com.aiksanov.api.project.util.enums.BlcRoles;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Data
@NoArgsConstructor
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

    public BlcDashboardComments(BlcDashboardPK pk, String comment) {
        if (Objects.nonNull(pk)) {
            this.projectID = pk.getProjectID();
            this.role = pk.getRole();
        }
        this.comment = comment;
    }
}
