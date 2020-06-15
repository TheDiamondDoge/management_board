package com.aiksanov.api.project.data.repository;

import com.aiksanov.api.project.data.entity.ProjectAdditionalInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdditionalInfoRepository extends CrudRepository<ProjectAdditionalInfo, Integer> {
}
