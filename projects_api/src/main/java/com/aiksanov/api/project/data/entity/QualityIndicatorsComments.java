package com.aiksanov.api.project.data.entity;

import com.aiksanov.api.project.data.entity.pk.QualityIndicatorsCommentsPK;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "prj_indicators_quality_comments")
@IdClass(QualityIndicatorsCommentsPK.class)
public class QualityIndicatorsComments {
    @Id
    @Column(name = "project_id")
    private int projectID;

    @Id
    @Column(name = "kpi_id")
    private String kpiID;

    @Column(name = "comment")
    private String comment;
}
