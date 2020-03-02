package com.aiksanov.api.project.data.repository;

import com.aiksanov.api.project.data.entity.ActionsState;
import com.aiksanov.api.project.util.enums.actions.ActionsStateVals;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActionsStateRepo extends CrudRepository<ActionsState, ActionsStateVals> {
    ActionsState findByStateLabel(String label);
}
