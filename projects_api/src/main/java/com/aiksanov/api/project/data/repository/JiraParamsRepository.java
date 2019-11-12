package com.aiksanov.api.project.data.repository;

import com.aiksanov.api.project.data.entity.JiraParams;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JiraParamsRepository extends CrudRepository<JiraParams, Integer> {
}
