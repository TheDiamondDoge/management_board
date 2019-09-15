package com.aiksanov.api.project.data.repository;

import com.aiksanov.api.project.data.entity.BlcRoles;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlcRolesRepository extends CrudRepository<BlcRoles, System> {
}
