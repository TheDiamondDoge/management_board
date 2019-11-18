package com.aiksanov.api.project.data.entity;

import com.aiksanov.api.project.data.entity.pk.ContributingProjectsPK;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "prj_contrib")
public class ContributingProjects {
    @EmbeddedId
    private ContributingProjectsPK pk;

    public ContributingProjects() {
    }

    public ContributingProjects(ContributingProjectsPK pk) {
        this.pk = pk;
    }

    public ContributingProjectsPK getPk() {
        return pk;
    }

    public void setPk(ContributingProjectsPK pk) {
        this.pk = pk;
    }
}
