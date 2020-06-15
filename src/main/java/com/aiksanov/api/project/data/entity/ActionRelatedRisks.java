package com.aiksanov.api.project.data.entity;

import com.aiksanov.api.project.data.entity.pk.ActionRelatedRisksPK;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "prj_actions_related_risks")
@IdClass(ActionRelatedRisksPK.class)
public class ActionRelatedRisks {
    @Id
    @Column(name = "action_id")
    private int actionId;

    @Id
    @Column(name = "risk_id")
    private String risksId;

    @Id
    @Column(name = "project_id")
    private int projectId;
}
