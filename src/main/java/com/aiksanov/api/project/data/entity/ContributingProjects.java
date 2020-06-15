package com.aiksanov.api.project.data.entity;

import com.aiksanov.api.project.data.entity.pk.ContributingProjectsPK;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "prj_contrib")
public class ContributingProjects {
    @EmbeddedId
    private ContributingProjectsPK pk;
}
