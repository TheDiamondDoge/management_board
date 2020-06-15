package com.aiksanov.api.project.data.entity;

import com.aiksanov.api.project.data.entity.pk.QualityIndicatorsPK;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "prj_indicators_quality")
@IdClass(QualityIndicatorsPK.class)
public class QualityIndicators {
    @Id
    @Column(name = "project_id")
    private int projectID;

    @Id
    @Column(name = "kpi_id")
    private String kpiID;

    @Id
    @Column(name = "row_num")
    private int rowNumber;

    @Column(name = "objective")
    private String objective;

    @Column(name = "actual")
    private String actual;
}