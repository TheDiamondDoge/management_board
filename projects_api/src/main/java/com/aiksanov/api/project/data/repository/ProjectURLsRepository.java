package com.aiksanov.api.project.data.repository;

import com.aiksanov.api.project.data.entity.ProjectURLs;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectURLsRepository extends CrudRepository<ProjectURLs, Integer> {
}
