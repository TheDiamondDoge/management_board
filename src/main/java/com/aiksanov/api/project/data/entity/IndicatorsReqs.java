package com.aiksanov.api.project.data.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@Entity
@Table(name = "prj_indicators_reqs")
public class IndicatorsReqs {
    @Id
    @Column(name = "project_id")
    private int projectID;

    @Column(name = "committed_at_dr1")
    private int committedAtDr1;

    @Column(name = "added_after_dr1")
    private int addedAfterDr1;

    @Column(name = "removed_after_dr1")
    private int removedAfterDr1;

    @Column(name = "modified_after_dr1")
    private int modifiedAfterDr1;
}
