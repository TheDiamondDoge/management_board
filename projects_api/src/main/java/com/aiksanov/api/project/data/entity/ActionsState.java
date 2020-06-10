package com.aiksanov.api.project.data.entity;

import com.aiksanov.api.project.util.enums.actions.ActionsStateVals;
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
@Table(name = "prj_actions_state")
public class ActionsState {
    @Id
    @Column(name = "state_id")
    private ActionsStateVals stateId;

    @Column(name = "state_label")
    private String stateLabel;
}
