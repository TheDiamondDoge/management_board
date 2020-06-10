package com.aiksanov.api.project.data.entity;

import com.aiksanov.api.project.data.entity.pk.CostPK;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "prj_cost")
@IdClass(CostPK.class)
public class Cost {
    @Id
    @Column(name = "project_id")
    private int projectId;

    @Id
    @Column(name = "type")
    private int type;

    @Id
    @Column(name = "state")
    private int state;

    @Column(name = "milestone")
    private String milestone;

    @Column(name = "value")
    private double value;

    @Column(name = "comment")
    private String comment;
}
