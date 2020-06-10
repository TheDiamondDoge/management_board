package com.aiksanov.api.project.data.entity;

import com.aiksanov.api.project.data.entity.pk.EcmaBacklogTargetPK;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "prj_ecma_backlog_target")
@IdClass(EcmaBacklogTargetPK.class)
public class EcmaBacklogTarget {
    @Id
    @Column(name = "project_id")
    private int projectId;

    @Id
    @Column(name = "milestone_label")
    private String label;

    @Column(name = "value")
    private int value;
}
