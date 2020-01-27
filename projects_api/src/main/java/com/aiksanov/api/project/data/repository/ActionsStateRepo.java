package com.aiksanov.api.project.data.repository;

import com.aiksanov.api.project.data.entity.ActionsState;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActionsStateRepo extends CrudRepository<ActionsState, Integer> {
    ActionsState findByStateLabel(String label);
}
