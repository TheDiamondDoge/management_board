package com.aiksanov.api.project.data.repository;

import com.aiksanov.api.project.data.entity.Actions;
import com.aiksanov.api.project.util.enums.actions.ActionsStateVals;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActionsRepository extends CrudRepository<Actions, Integer> {
    List<Actions> findActionsByProjectId(int projectId);
    void deleteAllByProjectId(int projectId);
    int countActionsByProjectIdAndState(int projectId, ActionsStateVals state);
}
