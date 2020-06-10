package com.aiksanov.api.project.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "prj_actions_priority")
public class ActionsPriority {
    @Id
    @Column(name = "priority_id")
    private int priorityId;

    @Column(name = "priority_label")
    private String priorityLabel;
}
