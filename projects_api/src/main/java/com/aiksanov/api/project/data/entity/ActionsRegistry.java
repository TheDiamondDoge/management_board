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
@Table(name = "prj_actions_registry")
public class ActionsRegistry {
    @Id
    @Column(name = "registry_id")
    private int registryId;

    @Column(name = "registry_label")
    private String registryLabel;
}
