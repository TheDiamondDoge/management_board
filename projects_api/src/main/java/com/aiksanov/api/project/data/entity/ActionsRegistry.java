package com.aiksanov.api.project.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "prj_actions_registry")
public class ActionsRegistry {
    @Id
    @Column(name = "registry_id")
    private int registryId;

    @Column(name = "registry_label")
    private String registryLabel;

    public ActionsRegistry() {
    }

    public ActionsRegistry(int registryId, String registryLabel) {
        this.registryId = registryId;
        this.registryLabel = registryLabel;
    }

    public int getRegistryId() {
        return registryId;
    }

    public void setRegistryId(int registryId) {
        this.registryId = registryId;
    }

    public String getRegistryLabel() {
        return registryLabel;
    }

    public void setRegistryLabel(String registryLabel) {
        this.registryLabel = registryLabel;
    }
}
