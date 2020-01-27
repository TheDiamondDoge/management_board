package com.aiksanov.api.project.data.repository;

import com.aiksanov.api.project.data.entity.ActionsPriority;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActionsPriorityRepo extends CrudRepository<ActionsPriority, Integer> {
    ActionsPriority findByPriorityLabel(String label);
}
