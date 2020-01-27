package com.aiksanov.api.project.data.repository;

import com.aiksanov.api.project.data.entity.ActionsRegistry;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ActionsRegistryRepo extends CrudRepository<ActionsRegistry, Integer> {
    ActionsRegistry findByRegistryLabel(String label);
}
