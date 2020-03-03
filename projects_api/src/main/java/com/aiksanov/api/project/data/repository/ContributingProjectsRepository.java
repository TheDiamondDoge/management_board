package com.aiksanov.api.project.data.repository;

import com.aiksanov.api.project.data.entity.ContributingProjects;
import com.aiksanov.api.project.data.entity.pk.ContributingProjectsPK;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ContributingProjectsRepository extends CrudRepository<ContributingProjects, ContributingProjectsPK> {
    List<ContributingProjects> getContributingProjectsByPk_ProjectID(int projectID);
    ContributingProjects findByPk_ContribID(int projectID);
    ContributingProjects findByPk_ProjectID(int projectID);
    List<ContributingProjects> findAllByPk_ProjectID(int projectID);
    void deleteAllByPk_ProjectID(int projectID);
}
