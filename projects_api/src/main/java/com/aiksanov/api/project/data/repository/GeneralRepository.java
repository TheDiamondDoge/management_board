package com.aiksanov.api.project.data.repository;

import com.aiksanov.api.project.data.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GeneralRepository extends JpaRepository<Project, Integer> {
    String findByEpmAndStatusQuery = "SELECT * FROM PRJ_WORKSPACE_GENERAL a, prj_workspace_info b " +
            "WHERE a.project_id = b.project_id AND UPPER(b.workspace_status) like (?2) AND a.EPM_project = ?1 " +
            "ORDER BY a.project_name ASC";

    String findByStatusQuery = "SELECT * FROM PRJ_WORKSPACE_GENERAL a, prj_workspace_info b " +
                        "WHERE a.project_id = b.project_id AND UPPER(b.workspace_status) = ?1";

    String findContribProjectsQuery = "SELECT * FROM PRJ_WORKSPACE_GENERAL a, prj_contrib b " +
                        "WHERE b.project_id = ?1 AND a.project_id = b.contrib_id";

    @Query(value = findByEpmAndStatusQuery, nativeQuery = true)
    List<Project> findAllByEpmAndStatus(Boolean isEpm, String workspaceStatus);

    @Query(value = findByStatusQuery, nativeQuery = true)
    List<Project> findAllByStatus(String status);

    @Query(value = findContribProjectsQuery, nativeQuery = true)
    List<Project> findAllContribProjectsByProjectID(int projectID);

    List<Project> findAllByEpm(Boolean epm);
    List<Project> findAll();
}
