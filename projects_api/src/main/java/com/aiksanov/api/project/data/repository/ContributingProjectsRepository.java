package com.aiksanov.api.project.data.repository;

import com.aiksanov.api.project.data.entity.ContributingProjects;
import com.aiksanov.api.project.data.entity.pk.ContributingProjectsPK;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ContributingProjectsRepository extends CrudRepository<ContributingProjects, ContributingProjectsPK> {
    List<ContributingProjects> getContributingProjectsByPk_ProjectID(int projectID);
}